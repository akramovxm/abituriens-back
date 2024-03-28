package uz.akramovxm.abituriensback.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTProvider {
    public String generateToken(Map<String, Object> claims, String subject) {
        Long duration = 3600_000L;
        return buildToken(claims, subject, duration);
    }

    public boolean tokenIsValid(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()));
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsTFunction) {
        Claims claims = getAllClaims(token);
        if (claims != null) {
            return claimsTFunction.apply(claims);
        }
        return null;
    }

    private String buildToken(Map<String, Object> claims, String subject, Long duration) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + duration))
                .signWith(getKey())
                .compact();
    }
    private SecretKey getKey() {
        String secretKey = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
        byte[] bytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(bytes);
    }
    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
