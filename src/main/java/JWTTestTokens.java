import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

public class JWTTestTokens {
    public static void main(String[] args) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        System.out.println(System.currentTimeMillis() + 1000000);
        Date exp = new Date(System.currentTimeMillis() + 1000000);
        System.out.println(exp.toString());
        HashMap<String,Object> hm = new HashMap<>();
        hm.put("roles","admin");
        String jws = Jwts.builder()
                .setClaims(hm)
                .setIssuer("https://localhost")
                .setSubject("admin")
                .setExpiration(exp)
                .signWith(key).compact();
        System.out.println(jws);
        if(Jwts.parser().setSigningKey(key).parseClaimsJws(jws).getBody().getSubject().equals("test")) {
            System.out.println("test");
        }
        String encoded = Encoders.BASE64.encode(key.getEncoded());
        System.out.println(encoded);


    }
}