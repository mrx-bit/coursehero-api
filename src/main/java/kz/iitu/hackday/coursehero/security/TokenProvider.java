package kz.iitu.hackday.coursehero.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import kz.iitu.hackday.coursehero.entity.Session;
import kz.iitu.hackday.coursehero.service.SessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TokenProvider {

    @Value("${spring.jwt.token.secret.key}")
    private String signKey;
    @Value("${spring.jwt.token.expire.seconds}")
    private Integer expire;

    @Autowired
    private SessionService sessionService;

    public String getUsernameFromToken(String token) throws Exception {

        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) throws Exception {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) throws Exception {
        return Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public Boolean validateToken(String token, String username) throws Exception {
        final String inUsername = getUsernameFromToken(token);
        return (inUsername.equals(username)
                        && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) throws Exception {
        try {
            log.info("checking token expire date: " + token);
            //        final Date expiration = getExpirationDateFromToken(token);
            Session session = sessionService.getActiveSessionByToken(token);
            log.info("isTokenExpired token: " + token + " Session object {}: " + session);

            final Date expiration = new Date(session.getUpdated().getTime() + expire * 1000);
            Boolean expired = expiration.before(new Date());

//            log.info("token expire date is: " + expiration + ", isExpired: " + expired);

            if (!expired) {
                log.info("updating session last update date for sessionId: " + session.getId());
                // prolong session updated time
                session = sessionService.updateLastUpdatedDate(session.getId());
            } else {
                log.info("closing session as it is expired for sessionId: " + session.getId());
                session = sessionService.closeSession(session.getId());
            }
            log.info("isTokenExpired token: " + token + " Session object {}: " + session);

            return expired;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

//    public Date getExpirationDateFromToken(String token) {
//        return getClaimFromToken(token, Claims::getExpiration);
//    }

    public UsernamePasswordAuthenticationToken getAuthentication(final String token, final Authentication existingAuth, final String username) throws Exception {

        final JwtParser jwtParser = Jwts.parser().setSigningKey(signKey);
        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
        final Claims claims = claimsJws.getBody();

        final Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("authorities").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        // TODO temporarily put username instead principal (i.e. userDetails)
        return new UsernamePasswordAuthenticationToken(username, "", authorities);
    }
}
