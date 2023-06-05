package com.a.sample;

import com.autoconfigure.SampleServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SampleAService {

    private final SampleServiceInterface sampleService;

    public String useSampleBBean() {
        return sampleService.sampleMethod();
    }
}