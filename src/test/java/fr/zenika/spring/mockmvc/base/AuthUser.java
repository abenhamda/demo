package fr.zenika.spring.mockmvc.base;

/**
 * Users and authorities available for the tests
 */
public enum AuthUser {

    SUPER_USER("TOKEN_SUPER_USER_VALUE"),
    USER("TOKEN_USER_VALUE");

    private String tokenValue;

    AuthUser(String tokenValue){
        this.tokenValue= tokenValue;
    }

    public String getTokenValue() {
        return tokenValue;
    }

}
