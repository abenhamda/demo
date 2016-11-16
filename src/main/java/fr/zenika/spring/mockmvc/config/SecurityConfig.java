package fr.zenika.spring.mockmvc.config;

import fr.zenika.spring.mockmvc.security.AuthenticationFilter;
import fr.zenika.spring.mockmvc.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.StandardCharsets;


/**
 * Security configuration.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private AuthenticationFilter authenticationFilter;

    @Autowired
    private AuthenticationEntryPoint unauthorizedEntryPoint;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http.httpBasic()
                .realmName("Protected API");

        // --
        // Stateless session (RESTFul API).
        // --

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.httpBasic()
                .authenticationEntryPoint(unauthorizedEntryPoint);

        // --
        // Encoding filter.
        // --

        final CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding(StandardCharsets.UTF_8.displayName());
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);

        // --
        // Authentication filter.
        // --

        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // --
        // Permit same path.
        // --

        http.authorizeRequests()
                .antMatchers(
                        Constant.ROOT_PATH,
                        Constant.VERSION_PATH).
                permitAll()
                .anyRequest().fullyAuthenticated();

        // --
        // Cross Site Request Forgery.
        // --

        http.csrf()
                .disable();
    }

}
