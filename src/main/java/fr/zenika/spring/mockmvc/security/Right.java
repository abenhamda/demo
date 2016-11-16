package fr.zenika.spring.mockmvc.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Right values
 */
public enum Right implements GrantedAuthority {


    AUTHORITY_READ,


    AUTHORITY_WRITE;

    @Override
    public String getAuthority() {
        return toString();
    }
}
