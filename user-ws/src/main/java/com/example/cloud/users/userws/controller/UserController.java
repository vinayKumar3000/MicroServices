package com.example.cloud.users.userws.controller;

import javax.validation.Valid;

import com.example.cloud.users.userws.Transfer.UserDto;
import com.example.cloud.users.userws.model.request.CreateUserRequestModel;
import com.example.cloud.users.userws.model.response.CreateUserResponseModel;
import com.example.cloud.users.userws.service.UserService;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private Environment env;

  @Autowired
  private UserService userService;

  @GetMapping("/status-check")
  public String getStatus() {
    return "working  user-ws on Port " + env.getProperty("local.server.port");
  }

  @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
      MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
  public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel userDetails) {

    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    UserDto userDto = modelMapper.map(userDetails, UserDto.class);
    userService.createUser(userDto);

    CreateUserResponseModel userResponse = modelMapper.map(userDto, CreateUserResponseModel.class);

    return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
  }

}
