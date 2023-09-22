package com.aron.sample.port.out;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public interface GetSSOInfoPort {

    /**
     * xxxxx
     * @param code
     * @return
     */
    AuthAccount retrieveAccountByCode(String code);

    /**
     * xxxx
     * @return
     */
    String getClientId();

    @Getter
    @Builder
    @AllArgsConstructor
    class AuthAccount {

        private String email;
        private String givenName;
        private String nonce;
        private String picture;
        private String networkId;
        private String firstname;
        private String nickname;
        private String name;
        private String lastname;
        private String familyName;

        public static AuthAccount from(Claims claims) {
            return AuthAccount.builder()
                    .email(claims.get(IdTokenKeys.EMAIL, String.class))
                    .givenName(claims.get(IdTokenKeys.GIVEN_NAME, String.class))
                    .nonce(claims.get(IdTokenKeys.NONCE, String.class))
                    .picture(claims.get(IdTokenKeys.PICTURE, String.class))
                    .networkId(claims.get(IdTokenKeys.NETWORK_ID, String.class))
                    .firstname(claims.get(IdTokenKeys.FIRSTNAME, String.class))
                    .nickname(claims.get(IdTokenKeys.NICKNAME, String.class))
                    .name(claims.get(IdTokenKeys.NAME, String.class))
                    .lastname(claims.get(IdTokenKeys.LASTNAME, String.class))
                    .familyName(claims.get(IdTokenKeys.FAMILY_NAME, String.class))
                    .build();
        }
    }

    class IdTokenKeys {

        private IdTokenKeys() {
        }

        public static final String GIVEN_NAME = "given_name";
        public static final String FAMILY_NAME = "family_name";
        public static final String NICKNAME = "nickname";
        public static final String NAME = "name";
        public static final String PICTURE = "picture";
        public static final String EMAIL = "email";
        public static final String NONCE = "nonce";
        public static final String FIRSTNAME = "https://accounts.ikea.com/Firstname";
        public static final String LASTNAME = "https://accounts.ikea.com/Lastname";
        public static final String NETWORK_ID = "https://accounts.ikea.com/networkid";
    }
}
