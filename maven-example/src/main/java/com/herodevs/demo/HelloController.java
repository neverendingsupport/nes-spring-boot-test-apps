package com.herodevs.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  // private final NesTestService nesTestService = new NesTestService();

  @GetMapping("/")
  public String index() {
    return "Success! This is a Spring Boot application with Maven.";
  }

}
