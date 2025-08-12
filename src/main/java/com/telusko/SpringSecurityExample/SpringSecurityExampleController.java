package com.telusko.SpringSecurityExample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringSecurityExampleController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Security!";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Hello, Admin!";
    }

    @GetMapping("/user")
    public String user() {
        return "Hello, User!";
    }
}
