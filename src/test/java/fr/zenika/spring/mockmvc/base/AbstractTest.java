package fr.zenika.spring.mockmvc.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.zenika.spring.mockmvc.config.BeanConfigTest;
import fr.zenika.spring.mockmvc.config.RestConfigTest;
import fr.zenika.spring.mockmvc.config.SecurityConfig;
import fr.zenika.spring.mockmvc.util.Cookies;
import org.junit.Before;
import org.junit.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * Abstract test layer providing utility methods to exchange with the REST API.
 */
@ContextConfiguration(classes = {SecurityConfig.class, BeanConfigTest.class, RestConfigTest.class })
@WebAppConfiguration
public abstract class AbstractTest {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTest.class);

    /**
     * Must be public.
     */
    @Rule
    public final AuthenticatedUserWatcher annotated = new AuthenticatedUserWatcher();

    @Autowired
    private WebApplicationContext ctx;

    public static MockMvc mockMvc;

    /**
     * Internal Jackson object mapper.
     */
    @Autowired
    private ObjectMapper jsonMapper;


    @Before
    public  void before() {
        LOGGER.info("Init Mock MVC Test .");
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .apply(springSecurity())
                .build();
    }


    /**
     * Perform URL request and return the ResultActions performed
     * @param method The Http request method
     * @param body   The Http request body
     * @return MVC ResultAction
     * @throws RestClientException
     */
    protected final ResultActions perform(final String url, final Method method,
                                    final Object body)  {
        LOGGER.debug("URL: '{}'.", url);

        final ResultActions result;
        final MockHttpServletRequestBuilder request;

        try {
        request=method.launch(url).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

        request.cookie(Cookies.create(Cookies.TOKEN_COOKIE_NAME, getTokenValue()));

        final String requestJson= jsonMapper.writeValueAsString(body);
        request.content(requestJson);

        result = this.mockMvc.perform(request);
        }
        catch (Exception e){
            throw new RestClientException("Client Rest Exception", e);
        }

        return result;

    }


    /**
     * Get token for authenticated user
     * @return token value
     */
    private String getTokenValue(){
        if(!annotated.getAuthenticatedUser()){
            return  null;
        }
        return annotated.getUser().getTokenValue();

    }

    /**
     *Method available in Rest Tests
     */
    protected static enum Method{
        GET ,
        POST,
        PUT,
        DELETE;

        public MockHttpServletRequestBuilder launch(String url) throws NoSuchMethodException {
            switch (this){
                case GET:
                    return get(url);
                case POST:
                    return post(url);
                case PUT:
                    return put(url);
                case DELETE:
                    return delete(url);
                default:
                    return null;
            }
        }
    }

}
