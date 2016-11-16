package fr.zenika.spring.mockmvc.base;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

/**
 * Test watcher for authenticated user in test.
 */
public class AuthenticatedUserWatcher extends TestWatcher {

    /**
     * Authenticated user annotation.
     */
    private AuthenticatedUser authenticatedUser;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void starting(final Description description) {
        authenticatedUser = description.getAnnotation(AuthenticatedUser.class);
    }

    /**
     * Returns if this is an authenticated test.
     *
     * @return {@code true} if this is an authenticated test, {@code false} otherwise.
     */
    public boolean getAuthenticatedUser() {
        return authenticatedUser != null;
    }

    /**
     * Returns the user attached to the authenticated test.
     *
     * @return The user attached to the authenticated test.
     */
    public AuthUser getUser() {
        if (authenticatedUser == null) {
            return null;
        }
        return authenticatedUser.value();
    }

}
