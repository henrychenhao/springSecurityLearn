package com.henry.salary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author chenhao
 */
@SpringBootApplication
@EnableResourceServer
public class DistributedSecuritySalaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistributedSecuritySalaryApplication.class, args);
    }

}
