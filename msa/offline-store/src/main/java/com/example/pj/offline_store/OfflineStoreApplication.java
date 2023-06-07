package com.example.pj.offline_store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class OfflineStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(OfflineStoreApplication.class, args);
    }
}