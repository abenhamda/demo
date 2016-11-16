package fr.zenika.spring.mockmvc.util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Utility class to manipulate HTTP cookies.
 */
public final class Cookies {

    //Authentication token name in cookie
    public static final String TOKEN_COOKIE_NAME= "AUTH_TOKEN";


    /**
     * Get token value from request.
     * @param request The http request.
     * @return token value.
     */
    public static String getValue(final HttpServletRequest request) {
        if (ArrayUtils.isEmpty(request.getCookies())) {
            return null;
        }

        for (final Cookie cookie : request.getCookies()) {
            if (StringUtils.equals(TOKEN_COOKIE_NAME, cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

    /**
     * Create a cookie with name and value.
     * @param name the cookie name.
     * @param value the cookie value.
     * @return The cookie object.
     */
    public static Cookie create(final String name, final String value) {

        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Cookie name must be provided");
        }

        final Cookie cookie = new Cookie(name, value);

        cookie.setPath("/");
        cookie.setSecure(false);
        cookie.setMaxAge(-1);

        return cookie;
    }


}
