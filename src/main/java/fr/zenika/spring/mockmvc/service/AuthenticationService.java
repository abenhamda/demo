package fr.zenika.spring.mockmvc.service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * Authentication service interface
 */
public interface AuthenticationService {

    /**
     * Authenticate by token value
     * @param authToken the user authentication value
     * @return Authentication domain
     */
    UsernamePasswordAuthenticationToken authenticate(String authToken);
}
