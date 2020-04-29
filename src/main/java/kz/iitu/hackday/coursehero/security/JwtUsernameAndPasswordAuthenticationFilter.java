package kz.iitu.hackday.coursehero.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kz.iitu.hackday.coursehero.dto.UserCredential;
import kz.iitu.hackday.coursehero.service.SessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authManager;
	private SessionService sessionService;

	public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authManager,
                                                      SessionService sessionService) {
		this.authManager = authManager;
		this.sessionService = sessionService;

		this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/auth/**", "POST"));
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			UserCredential creds = new ObjectMapper().readValue(request.getInputStream(), UserCredential.class);
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					creds.getEmail(), creds.getPassword(), Collections.emptyList());

			return authManager.authenticate(authToken);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

		log.info("JwtUsernameAndPasswordAuthenticationFilter.successfulAuthentication");
		Long now = System.currentTimeMillis();

		List<String> authorities = auth.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority).collect(Collectors.toList());

		String token = Jwts.builder()
			.setSubject(auth.getName())	
			.claim("authorities", authorities)
			.setIssuedAt(new Date(now))
//			.setExpiration(new Date(now + 1*60*60*1000))  // in milliseconds
			.signWith(SignatureAlgorithm.HS512, "2BAbWmb8fmqWq4m8")
			.compact();

		SecurityContextHolder.getContext().setAuthentication(auth);

		// Save session on db
		sessionService.create(auth.getName(), token, "WEB. Authorities: " + authorities);

		// Add token to header
		response.addHeader("Authorization", "Bearer " + token);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
		super.unsuccessfulAuthentication(request, response, failed);
//		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed");
	}
}

