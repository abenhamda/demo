package fr.zenika.spring.mockmvc.config;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.zenika.spring.mockmvc.util.Constant;
import org.springframework.context.annotation.*;

/**
 * Spring beans configuration
 */
@Configuration
@ComponentScan(basePackages = Constant.PACKAGE+".*",
                excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = Constant.PACKAGE+".config.*"))
public class BeanConfig {

    /**
     * Initializes the {@link ObjectMapper} instance.<br>
     * This mapper is used to map beans into JSON.
     *
     * @return The initialized {@link ObjectMapper} instance.
     */
    @Bean
    public ObjectMapper jsonMapper() {

        final ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        jsonMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

        return jsonMapper;
    }

}
