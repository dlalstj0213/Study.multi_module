package com.a.sample;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    private final SampleAService sampleAService;

    public SampleController(SampleAService sampleAService) {
        this.sampleAService = sampleAService;
    }

    @RequestMapping("/sample")
    void sampleB() {
        String result = sampleAService.useSampleBBean();
        System.out.println(result);
    }
}