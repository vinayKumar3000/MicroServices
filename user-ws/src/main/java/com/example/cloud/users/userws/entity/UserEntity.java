package com.example.cloud.users.userws.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Seralizable
@Entity
@Table(name = "Users")
public class UserEntity implements Serializable {

  private static final long serialVersionUID = 123L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID", updatable = false, nullable = true)
  private Long id;

  @Column(nullable = false, length = 30)
  private String firstName;

  @Column(nullable = false, length = 30)
  private String lastName;

  @Column(nullable = false, length = 120, unique = true)
  private String email;

  @Column(nullable = false, length = 150, unique = true)
  private String encryptedPassword;

  @Column(nullable = false, length = 150, unique = true)
  private String userId;

  public String getFirstName() {
    return firstName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEncryptedPassword() {
    return encryptedPassword;
  }

  public void setEncryptedPassword(String encryptedPassword) {
    this.encryptedPassword = encryptedPassword;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

}
