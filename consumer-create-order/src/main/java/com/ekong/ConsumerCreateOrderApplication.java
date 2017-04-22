package com.ekong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableFeignClients
public class ConsumerCreateOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerCreateOrderApplication.class, args);
    }

    @GetMapping("/")
    public String home() {
        return "ConsumerCreateOrder home page";
    }
}
