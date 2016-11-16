package fr.zenika.spring.mockmvc.security;

import fr.zenika.spring.mockmvc.service.AuthenticationService;
import fr.zenika.spring.mockmvc.util.Cookies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * Authentication filter .
 */
@Component
public class AuthenticationFilter extends GenericFilterBean {

    /**
     * Injected authentication service.
     */
    @Autowired
    private AuthenticationService authenticationService;


    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
                         final FilterChain chain) throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        final String authToken = Cookies.getValue(request);

        LocaleContextHolder.setLocale(Locale.FRENCH);
        UsernamePasswordAuthenticationToken auth = authenticationService.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        chain.doFilter(request, response);
    }

}
