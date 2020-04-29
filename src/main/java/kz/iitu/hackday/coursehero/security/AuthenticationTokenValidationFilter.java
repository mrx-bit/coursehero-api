package kz.iitu.hackday.coursehero.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import kz.iitu.hackday.coursehero.service.SessionService;
import kz.iitu.hackday.coursehero.utils.constants.ErrorMessageConstants.*;
import kz.iitu.hackday.coursehero.utils.exceptions.UnauthorisedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationTokenValidationFilter extends OncePerRequestFilter {

    private TokenProvider tokenProvider;
    private SessionService sessionService;

    public AuthenticationTokenValidationFilter(TokenProvider tokenProvider, SessionService sessionService) {
        this.tokenProvider = tokenProvider;
        this.sessionService = sessionService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("AuthenticationTokenValidationFilter.doFilterInternal");
        String header = req.getHeader("Authorization");
        String username = null;
        String authToken = null;
        if (header != null && header.startsWith("Bearer ")) {
            authToken = header.replace("Bearer ","");
            try {
                username = tokenProvider.getUsernameFromToken(authToken);
            } catch (IllegalArgumentException e) {
                logger.error("an error occured during getting username from token", e);
            } catch (ExpiredJwtException e) {
                logger.warn("the token is expired and not valid anymore", e);
            } catch(SignatureException e) {
                logger.error("Authentication Failed. Username or Password not valid.");
            } catch(Exception e) {
                logger.error("Authentication Failed. ", e);
            }
        } else {
            logger.warn("couldn't find bearer string, will ignore the header");
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            try {
                if (tokenProvider.validateToken(authToken, username)) {
                    UsernamePasswordAuthenticationToken authentication
                            = tokenProvider.getAuthentication(authToken,
                            SecurityContextHolder.getContext().getAuthentication(), username);

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                    logger.info("authenticated user " + username + ", setting security context");
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                // In case of failure. Make sure it's clear; so guarantee user won't be authenticated
                SecurityContextHolder.clearContext();
                throw new UnauthorisedException(UnauthorizedError.MESSAGE, UnauthorizedError.ERROR_CODE);
            }
        }

        filterChain.doFilter(req, res);
    }
}
