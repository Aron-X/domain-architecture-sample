package com.aron.sample.adapter.out.api.auth0;

import com.aron.sample.infrastructre.exception.BizError;
import com.aron.sample.infrastructre.exception.BizException;
import com.aron.sample.port.out.GetSSOInfoPort;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
@RequiredArgsConstructor
@Getter
@Slf4j
class GetSSOInfoImpl implements GetSSOInfoPort {

    private final Auth0Service auth0Service;

    @Override
    public AuthAccount retrieveAccountByCode(String code) {
        Claims claims = verifySignature(auth0Service.getIdToken(code));
        return AuthAccount.from(claims);
    }

    @Override
    public String getClientId() {
        return auth0Service.getClientId();
    }

    private Claims verifySignature(String idToken) {
        Claims claims = null;
        try {
            RSAKey rsaKey = RSAKey.parse(auth0Service.getPublicKey());
            claims = Jwts.parser().setSigningKey(rsaKey.toPublicKey())
                    .parseClaimsJws(idToken)
                    .getBody();
        } catch (ParseException | JOSEException e) {
            log.error("verify signature failed", e);
            throw new BizException(BizError.VERIFY_SIGNATURE_FAILED);
        }
        return claims;
    }
}
