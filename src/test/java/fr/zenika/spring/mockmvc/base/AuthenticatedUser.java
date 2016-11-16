package fr.zenika.spring.mockmvc.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Authenticated Test with specific user.
 *
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface AuthenticatedUser {

    /**
     * The authenticated test user.
     *
     * @return The user.
     */
    AuthUser value();

}
