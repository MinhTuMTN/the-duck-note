package com.theduck.note.api_gateway.util;

import java.text.ParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;

@Component
public class JwtUtil {

    @Value("${settings.jwt-secret}")
    private String jwtSecret;

    public boolean isValidToken(String token) {
        try {
            JWSVerifier verifier = new MACVerifier(jwtSecret.getBytes());

            SignedJWT signedJWT = SignedJWT.parse(token);

            Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

            boolean verified = signedJWT.verify(verifier);

            return verified && expiryTime.after(new Date());
        } catch (ParseException | JOSEException e) {
            return false;
        }
    }
}
