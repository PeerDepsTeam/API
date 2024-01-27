package com.peerdeps.peerdepsapi.endpoint.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class HealthCheckController {
  @GetMapping("/ping")
  public  String healthCheck(){
    return "pong";
  }
}
