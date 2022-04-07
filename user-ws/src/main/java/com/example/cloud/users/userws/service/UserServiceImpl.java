package com.example.cloud.users.userws.service;

import java.util.ArrayList;

import com.example.cloud.users.userws.Transfer.UserDto;
import com.example.cloud.users.userws.Utility.Utils;
import com.example.cloud.users.userws.entity.UserEntity;
import com.example.cloud.users.userws.repository.UserRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  UserRepository userRepository;
  Utils utils;
  BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, Utils utils, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userRepository = userRepository;
    this.utils = utils;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @Override
  public UserDto createUser(UserDto userDetails) {

    userDetails.setUserId(utils.generateUserId());
    userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);
    // Generate Id
    userRepository.save(userEntity);
    return userDetails;
  }

  @Override
  public UserDto getUserDetailsByEmail(String email) {
    UserEntity userEntity = userRepository.findByEmail(email);

    if (userEntity == null)
      throw new UsernameNotFoundException(email);

    return new ModelMapper().map(userEntity, UserDto.class);
  }

  @Override
  public UserDto getUserByUserId(String userId) {

    UserEntity userEntity = userRepository.findByUserId(userId);
    if (userEntity == null)
      throw new UsernameNotFoundException("User not found");

    UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);

    return userDto;
  }

  // 4
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity userEntity = userRepository.findByEmail(username);

    if (userEntity == null)
      throw new UsernameNotFoundException(username);

    return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true, true, true,
        new ArrayList<>());
  }

}
