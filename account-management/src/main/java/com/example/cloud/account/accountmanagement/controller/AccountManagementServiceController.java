package com.example.cloud.account.accountmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountManagementServiceController {

  @Autowired
  private Environment env;

  @GetMapping("/status-check")
  public String getStatus() {
    return "account-ws working on port " + env.getProperty("local.server.port");
  }

}
