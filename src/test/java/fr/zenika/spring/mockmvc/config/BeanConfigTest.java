package fr.zenika.spring.mockmvc.config;

import fr.zenika.spring.mockmvc.util.Constant;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * Bean Config for IT Tests
 */
@Configuration
@ComponentScan(basePackages = Constant.PACKAGE + ".*", excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = Constant.PACKAGE + ".config.*"))
public class BeanConfigTest extends BeanConfig {


}
