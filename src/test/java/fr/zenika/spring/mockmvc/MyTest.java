package fr.zenika.spring.mockmvc;

import fr.zenika.spring.mockmvc.base.AbstractTest;
import fr.zenika.spring.mockmvc.base.AuthUser;
import fr.zenika.spring.mockmvc.base.AuthenticatedUser;
import fr.zenika.spring.mockmvc.controller.message.MyMessage;
import fr.zenika.spring.mockmvc.security.Right;
import fr.zenika.spring.mockmvc.util.Constant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.Cookie;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration Test with Mock Mvc
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class MyTest extends AbstractTest {


    @Test
    public void noAuthenticatedAccessTest() throws Exception {

        this.perform(Constant.VERSION_PATH, Method.GET, null)
            .andExpect(status().isOk())                                                 //Expect http response status
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //Expect http response media type
            .andExpect(jsonPath("$").exists())                                          //Expect exist json object in reponse
            .andExpect(jsonPath("$.java-version").exists())
            .andExpect(jsonPath("$.java-version").value("8.24"))                        //Expect json attribute value
            .andDo(print());                                                            //Print result
    }

    @AuthenticatedUser(AuthUser.SUPER_USER)
    @Test
    public void authorisedAccessPutTest() throws Exception {

        MyMessage request = new MyMessage("My request data");
        this.perform(Constant.DATA_PATH, Method.PUT, request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.value").isString())
                .andExpect(jsonPath("$.value").value(request.getValue()))
                .andExpect(jsonPath("$.created").isBoolean())
                .andExpect(jsonPath("$.created").value(true))
                .andDo(print());
    }


    @AuthenticatedUser(AuthUser.USER)
    @Test
    public void forbiddenAccessPutTest() throws Exception {

        this.perform(Constant.DATA_PATH, Method.PUT, new MyMessage("My request data"))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    public void authTest() throws Exception {
        mockMvc.perform(put("/data")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"value\": \"My message\"}")
                .cookie(new Cookie("AUTH_TOKEN", "TOKEN_SUPER_USER_VALUE")))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
                