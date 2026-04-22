package org.example.utils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

@Service()
public class JwtUtil {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(String userId) throws JOSEException {
        JWSSigner signer = new MACSigner(jwtSecret);

        //payload
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(userId)
                .issuer("Biswel")
                .expirationTime(new Date(new Date().getTime() + expiration))
                .build();

        //header + payload
        JWSObject jwsObject = new JWSObject(
                new JWSHeader(JWSAlgorithm.HS256),
                new Payload(claimsSet.toJSONObject())
        );

        //auto generate signature
        jwsObject.sign(signer);

        return jwsObject.serialize();
    }

    public boolean validateToken(String token) throws JOSEException, ParseException {
        JWSObject jwsObject = JWSObject.parse(token);
        JWSVerifier verifier = new MACVerifier(jwtSecret);
        if (!jwsObject.verify(verifier)) return false;

        JWTClaimsSet claims = JWTClaimsSet.parse(jwsObject.getPayload().toJSONObject());
        return !claims.getExpirationTime().before(new Date());
    }

    public String getSubject(String token) throws ParseException {
        JWSObject jwsObject = JWSObject.parse(token);
        JWTClaimsSet jwtClaimsSet = JWTClaimsSet.parse(jwsObject.getPayload().toJSONObject());
        return jwtClaimsSet.getSubject();
    }

    /** Returns true if the token expires within the next 30 minutes. */
    public boolean isExpiringSoon(String token) throws ParseException {
        JWSObject jwsObject = JWSObject.parse(token);
        JWTClaimsSet claims = JWTClaimsSet.parse(jwsObject.getPayload().toJSONObject());
        long remaining = claims.getExpirationTime().getTime() - new Date().getTime();
        return remaining < 30 * 60 * 1000L;
    }
}
