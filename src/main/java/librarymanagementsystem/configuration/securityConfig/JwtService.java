//package librarymanagementsystem.configuration.securityConfig;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//
//@Service
//public class JwtService {
//
//
//    @Value("${application.security.jwt.secret-key}")
//    private String secertKey;
//    @Value("${application.security.jwt.expiration}")
//    private long jwtExpiration;
//
//    public Claims extractAllClaims(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(getSignInKey())
//                .build()
//                .parseClaimsJwt(token)
//                .getBody();
//    }
//
//    public <R> R extractClaim(String token, Function<Claims, R> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//    public String extractUsername (String token){
//        return extractClaim(token, Claims::getSubject);
//    }
//    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
//        return Jwts.builder()
//                .setClaims(extraClaims)
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + expiration))
//                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//    public String generateToken(UserDetails userDetails) {
//        return generateToken(new HashMap<>(), userDetails);
//    }
//
//    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
//        return buildToken(extraClaims, userDetails, jwtExpiration);
//    }
//    private Key getSignInKey() {
//        byte[] keyBites = Decoders.BASE64.decode(secertKey);
//        return Keys.hmacShaKeyFor(keyBites);
//    }
//    public boolean isTokenValid(String token,UserDetails userDetails){
//        String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername()) && !isExpired(token));
//    }
//    private boolean isExpired (String token){
//        return extractExpiration(token).before(new Date());
//    }
//    private Date extractExpiration (String token){
//        return extractClaim(token,Claims::getExpiration);
//    }
//
//
//
//
//}
