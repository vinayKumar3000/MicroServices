package com.example.cloud.users.userws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private Environment env;

  @GetMapping("/status-check")
  public String getStatus() {
    return "working  user-ws on Port " + env.getProperty("local.server.port");
  }

}
