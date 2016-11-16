package fr.zenika.spring.mockmvc.controller;

import fr.zenika.spring.mockmvc.controller.message.MyMessage;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Map;

/**
 * My Rest Controller interface.
 */
@PreAuthorize("denyAll")
public interface MyController {


    /**
     * get the version properties
     * @return Version Properties
     */
    Map<String, String> getVersionProperties();

    /**
     * Get Data
     * @return String
     */
    String getData();

    /**
     * Put Data
     * @param data to put
     */
    MyMessage putData(MyMessage data);
}
