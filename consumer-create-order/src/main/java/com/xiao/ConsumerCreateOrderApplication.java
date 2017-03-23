package com.xiao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class ConsumerCreateOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerCreateOrderApplication.class, args);
    }

    @GetMapping("/")
    public String home() {
        return "ConsumerCreateOrder home page";
    }
}
