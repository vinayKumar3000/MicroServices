package com.example.cloud.users.userws.repository;

import com.example.cloud.users.userws.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

//interface , Capital String
public interface UserRepository extends CrudRepository<UserEntity, Long> {

  UserEntity findByEmail(String email);

  UserEntity findByUserId(String userId);

}
