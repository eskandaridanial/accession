package com.accession.io.config;

import com.accession.io.Application;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-12-29 12:56:40
 */
@CucumberContextConfiguration
@SpringBootTest(classes = {Application.class, ContainerConfig.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CucumberConfig {

    @Value("${server.port}")
    private String serverPort;

    @Value("${server.path}")
    private String serverPath;

    public String urlOf(String path) {
        return "http://localhost:" + serverPort + serverPath + path;
    }
}
