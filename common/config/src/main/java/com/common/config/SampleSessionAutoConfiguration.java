package com.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(SampleLibProperties.class)
@ConditionalOnProperty(name = "samplelib.is-enabled", havingValue = "true", matchIfMissing = true)
@RequiredArgsConstructor
public class SampleSessionAutoConfiguration {

    private final SampleLibProperties sampleLibProperties;

    @Bean
    @ConditionalOnMissingBean
    public SessionUtilTemplate utilTemplate() {
        return new SessionUtil();
    }

    @Bean
    @ConditionalOnMissingBean
    public SampleTestTemplate sampleTestTemplate() {

        System.out.println("##> Run - Config ::: SampleTestTemplate - " + sampleLibProperties.isEnabled());
        return new SampleTestImpl();
    }
}