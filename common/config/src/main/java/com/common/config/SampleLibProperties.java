package com.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "samplelib")
public class SampleLibProperties {
    private final boolean isEnabled;

    public SampleLibProperties(boolean isEnabled) {this.isEnabled = isEnabled;}

    public boolean isEnabled() {
        return this.isEnabled;
    }
}