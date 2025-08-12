package com.telusko.SpringSecurityExample;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SpringSecurityExampleController {

    private List<Student> students = new ArrayList<>(List.of(
            new Student("John", 20),
            new Student("Jane", 22),
            new Student("Jack", 21)
    ));

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Security!";
    }

    @GetMapping("/students")
    public List<Student> admin() {
        return students;
    }

    @GetMapping("csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    }

    @PostMapping("/addStudent")
    public Student addStudent(@RequestBody Student student) {
        students.add(student);
        return student;
    }
}
