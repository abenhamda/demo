package fr.zenika.spring.mockmvc.service.impl;

import fr.zenika.spring.mockmvc.security.Right;
import fr.zenika.spring.mockmvc.service.AuthenticationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


/**
 * Authentication service impl
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService{


    /***
     * Token and authorities data for test.
     * @return Map of token and authorities.
     */
    private HashMap<String, List<GrantedAuthority>> getAuthoritiesByToken() {
        final HashMap<String, List<GrantedAuthority>> AUTHORITY_DATA = new HashMap<>();

        AUTHORITY_DATA.put("TOKEN_SUPER_USER_VALUE", Arrays.asList(Right.values()));
        AUTHORITY_DATA.put("TOKEN_USER_VALUE", Arrays.asList(Right.AUTHORITY_READ));

        return AUTHORITY_DATA;
    }

    @Override
    public UsernamePasswordAuthenticationToken authenticate(String authToken) {
        if(StringUtils.isBlank(authToken)) {
            return null;
        }
        List<GrantedAuthority> grantedAuthorities = getAuthoritiesByToken().get(authToken);
        return new UsernamePasswordAuthenticationToken(null , null, grantedAuthorities);
    }
}
