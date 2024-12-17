package com.backbase.goldensample.token.controller;

import com.backbase.buildingblocks.test.http.TestRestTemplateConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.activemq.ActiveMQContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("it")
@Testcontainers(disabledWithoutDocker = true)
class ExampleControllerIT {

    @Container
    private static final ActiveMQContainer activeMqContainer = new ActiveMQContainer("apache/activemq-classic");

    @Container
    private static final MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql");

    @Autowired
    private MockMvc mvc;

    @DynamicPropertySource
    static void configureCloudStreamBinder(DynamicPropertyRegistry registry) {
        activeMqContainer.start();
        registry.add("spring.activemq.broker-url", activeMqContainer::getBrokerUrl);
        mySQLContainer.start();
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }

    @Test
    void exampleTest() throws Exception {

        MvcResult result =
                mvc.perform(get("/message")
                                .header(AUTHORIZATION, TestRestTemplateConfiguration.TEST_SERVICE_BEARER))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        assertThat(responseBody).as("ID in response should match ID in request").contains("Hello World");
    }
}
