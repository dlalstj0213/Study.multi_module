package com.autoconfigure;

import org.springframework.stereotype.Service;

@Service
public class SampleService implements SampleServiceInterface {

    @Override
    public String sampleMethod() {
        return "This is Sample Method";
    }
}