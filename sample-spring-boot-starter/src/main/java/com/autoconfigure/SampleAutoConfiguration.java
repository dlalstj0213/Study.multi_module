package com.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

//@Configuration
//@ConditionalOnClass(SampleService.class)

@AutoConfiguration
public class SampleAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public SampleServiceInterface sampleService() {
        System.out.println("##> RUN AUTO CONFIGURATION");
        return new SampleService();
    }
}