package fr.zenika.spring.mockmvc.config;


import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * Web application initializer.
 */
public class WebAppInitializer extends  AbstractSecurityWebApplicationInitializer{

    public WebAppInitializer() {
        super(RestConfig.class, SecurityConfig.class);
    }

    @Override
    protected void afterSpringSecurityFilterChain(final ServletContext servletContext) {

        final AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();

        final ServletRegistration.Dynamic dispatcher = servletContext.addServlet("rest", new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }

}
