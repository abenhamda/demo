package fr.zenika.spring.mockmvc.controller.impl;


import fr.zenika.spring.mockmvc.controller.MyController;
import fr.zenika.spring.mockmvc.controller.message.MyMessage;
import fr.zenika.spring.mockmvc.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * My Rest Controller impl.
 */
@RestController(value = Constant.ROOT_PATH)
public class MyControllerImpl implements MyController {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MyController.class);

    /**
     * {@inheritDoc}
     */
    @RequestMapping(
            value = Constant.VERSION_PATH,
            method = RequestMethod.GET
    )
    @PreAuthorize("permitAll")
    public Map<String, String> getVersionProperties() {

        final Map<String, String> versionProperties = new LinkedHashMap<>();
        versionProperties.put("java-version","8.24");
        versionProperties.put("spring-version","4.2");
        return versionProperties;
    }



    /**
     * {@inheritDoc}
     */
    @RequestMapping(
            value = Constant.DATA_PATH,
            method = RequestMethod.GET
    )
    @Override
    @PreAuthorize("hasRole('AUTHORITY_READ')")
    public String getData() {

        return "My DATA";
    }



    /**
     * {@inheritDoc}
     */
    @RequestMapping(
            value = Constant.DATA_PATH,
            method = RequestMethod.PUT
    )
    @Override
    @PreAuthorize("hasRole('AUTHORITY_WRITE')")
    public MyMessage putData(@RequestBody  MyMessage data) {

        LOGGER.debug("put data accepted :{}",data);
        data.setCreated(true);
        return data;
    }

}
