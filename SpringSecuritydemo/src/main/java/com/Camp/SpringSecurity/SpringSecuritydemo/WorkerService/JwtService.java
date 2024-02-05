package com.Camp.SpringSecurity.SpringSecuritydemo.WorkerService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtService {
    private String  secret = "QNaVsgr8HKYf0F0ZERvgxL9yjnQht37A";
    // Make a key
    public Key getSignKey( ){
        byte[ ] byteFromSecret = Decoders.BASE64.decode(secret);
        Key getKeyFromByte = Keys.hmacShaKeyFor(byteFromSecret);
       return getKeyFromByte;
    }
   // make a JWT token from userName along with the key

    public String generateJwtToken( String userName ){
        Map<String,Object> claims = new HashMap<>();
        String jwtToken = Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();

        return jwtToken;
    }
    // Now you can create a method to extract claims from the jwt token
    public Claims extractAllClaims(String token){

        Claims claim = Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return  claim;
    }
    // now a generic method to extract one info from the claim
    public <T>T extractClaimOne ( String token , Function<Claims,T> claimResolver){
        final Claims claim = extractAllClaims(token);
        return claimResolver.apply(claim);
    }
  // let use the above method to extract one piece info
    public String getUserName(String token){
        return extractClaimOne(token, Claims::getSubject);
    }

   public Date getExpirationDate( String token){
    return extractClaimOne(token,Claims::getExpiration);
    }
    // expiration check
    private Boolean IsTokenExpiration ( String token){
        return !getExpirationDate(token).before(new Date());
    }
 // validity check
    public boolean vlidateToken(String token , UserDetails userDetails){
        final  String userName = getUserName(token);

        return (userName.equals(userDetails.getUsername()) && IsTokenExpiration(token));

    }









}
