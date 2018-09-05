package com.codecool.shop.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.codecool.shop.model.User;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

public class AuthGuard {

    private static final String ISSUER = "Codeberg";
    private static final long EXPIRATION_TIME = 900000;  // That's 15 minutes in milliseconds.

    public String createToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(System.getenv("SECRET_KEY"));
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withIssuedAt(new Date())
                    .withClaim("user_id", user.getId())
                    .withClaim("email", user.getEmail())
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            System.out.println("ERROR occurred while getting encoding algorithm: UTF-8 encoding not supported");
        }
        return null;
    }

    public int processToken(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String token = req.getHeader("Authorization");
        if (token == null) {
            resp.sendError(401);
            return -1;
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(System.getenv("SECRET_KEY"));
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            if (jwt.getIssuedAt().getTime() + EXPIRATION_TIME > new Date().getTime()) {
                return jwt.getClaim("user_id").asInt();
            }
        } catch (UnsupportedEncodingException e) {
            System.out.println("ERROR occurred while getting encoding algorithm: UTF-8 encoding not supported");
        } catch (Exception e) {
            System.out.println("Received invalid token!");
        }
        resp.sendError(401);
        return -1;
    }
}
