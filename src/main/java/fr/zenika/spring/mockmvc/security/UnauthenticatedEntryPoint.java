package fr.zenika.spring.mockmvc.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Authentication entry point implementation.
 */
@Component
public class UnauthenticatedEntryPoint implements AuthenticationEntryPoint {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UnauthenticatedEntryPoint.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException exception)
            throws IOException, ServletException {

        // At this point, user is considered not authenticated.

        LOGGER.debug("Unauthorized access for request '{}'.", request.getRequestURI());

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized access.");
    }

}
