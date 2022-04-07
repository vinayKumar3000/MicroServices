package com.example.cloud.users.userws.service;

import com.example.cloud.users.userws.Transfer.UserDto;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

  public UserDto createUser(UserDto userDetails);

  public UserDto getUserDetailsByEmail(String userName);

  UserDto getUserByUserId(String userId);

}
