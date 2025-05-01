package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.services;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.AppUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${security.jwt.access-token-ttl}")
    private long accessTokenTlt;
    @Value("${security.jwt.refresh-token-ttl}")
    private long refreshTokenTtl;
    @Value("${security.jwt.signing-key-secret}")
    private String signingKeySecret;


    @Override
    public String generateAccessToken(AppUser user) {
        //ttl ->time to live, el tiempo de vida de algo, cuando pasa su tiempo se descarta
        SecretKey key = Keys.hmacShaKeyFor(signingKeySecret.getBytes());

        return Jwts.builder()
                .claim("type",JwtTokenType.ACCESS)
                .subject(user.getEmail())
                .claim("firstName", user.getFirstName())
                .claim("lastName", user.getLastName())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + accessTokenTlt))
                .signWith(key)
                .compact();


    }
    @Override
    public String generateRefreshToken(AppUser user) {

        SecretKey key = Keys.hmacShaKeyFor(signingKeySecret.getBytes());
        return Jwts.builder()
                .claim("type",JwtTokenType.REFRESH)
                .subject(user.getEmail())
                .claim("firstName", user.getFirstName())
                .claim("lastName", user.getLastName())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + refreshTokenTtl))
                .signWith(key)
                .compact();


    }
    @Override
    public void validateAccessToken(String token) {
        Claims claims= getClaimsFromToken(token);
        JwtTokenType type = claims.get("type",JwtTokenType.class);

        if(type !=JwtTokenType.ACCESS){
            throw new JwtException("Invalid token");
        }


    }
    @Override
    public void validateRefreshToken(String token) {

        Claims claims= getClaimsFromToken(token);
        JwtTokenType type = JwtTokenType.valueOf(claims.get("type").toString());
        //Esta línea no funciona
        //JwtTokenType type = claims.get("type",JwtTokenType.class);

        if(type !=JwtTokenType.REFRESH){
            throw new JwtException("Invalid token");
        }

        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String extractUsername(String token) {

        Claims claims = getClaimsFromToken(token);
        return claims.getSubject();

    }

    private Claims getClaimsFromToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(signingKeySecret.getBytes());

        JwtParser parser= Jwts.parser().verifyWith(key).build();

        return  parser.parseSignedClaims(token).getPayload();
    }

}
