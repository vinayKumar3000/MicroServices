package com.example.cloud.users.userws.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateUserRequestModel {

  @NotNull(message = "FirstName can not be null")
  @Size(min = 2, max = 10, message = "firstName should be greater than 2 characters and less than 10 characters")
  private String firstName;

  @NotNull(message = "lastName can not be null")
  @Size(min = 2, max = 10, message = "firstName should be greater than 2 characters and less than 10 characters")
  private String lastName;

  @NotNull(message = "password can not be null")
  @Size(min = 2, max = 10, message = "firstName should be greater than 2 characters and less than 10 characters")
  private String password;

  @NotNull(message = "email can not be null")
  @Email
  private String email;

  public String getFirstName() {
    return firstName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

}
