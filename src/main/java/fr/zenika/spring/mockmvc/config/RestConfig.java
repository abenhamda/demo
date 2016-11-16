package fr.zenika.spring.mockmvc.config;


import fr.zenika.spring.mockmvc.util.Constant;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * REST API configuration.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = Constant.PACKAGE + ".controller")
@Import({
        BeanConfig.class
})
public class RestConfig extends WebMvcConfigurerAdapter {


}
