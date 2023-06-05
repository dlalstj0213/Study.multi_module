package com.common.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
//@EnableConfigurationProperties(SampleLibProperties.class)
@ContextConfiguration(classes = {SampleSessionAutoConfiguration.class})
@TestPropertySource("classpath:application.yml")
class SampleSessionAutoConfigurationTest {

    //    @Autowired
//    private SampleLibProperties properties;
    @Autowired
    private SampleTestTemplate template;

//    @Test
//    void test() {
//        assertTrue(properties.isEnabled());
//    }

    @Test
    void templateTest() {
        System.out.println(template.getSampleData());
    }
}