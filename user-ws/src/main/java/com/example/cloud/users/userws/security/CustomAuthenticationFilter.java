package com.example.cloud.users.userws.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.cloud.users.userws.Transfer.UserDto;
import com.example.cloud.users.userws.model.request.LoginRequestModel;
import com.example.cloud.users.userws.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.core.userdetails.User;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private UserService userService;
  private Environment environment;

  public CustomAuthenticationFilter(UserService userService,
      Environment environment,
      AuthenticationManager authenticationManager) {
    this.userService = userService;
    this.environment = environment;
    super.setAuthenticationManager(authenticationManager);
  }

  // 3
  @Override
  public Authentication attemptAuthentication(HttpServletRequest req,
      HttpServletResponse res) throws AuthenticationException {
    try {

      LoginRequestModel creds = new ObjectMapper()
          .readValue(req.getInputStream(), LoginRequestModel.class);

      return getAuthenticationManager().authenticate(
          new UsernamePasswordAuthenticationToken(
              creds.getEmail(),
              creds.getPassword(),
              new ArrayList<>()));

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  // 5
  @Override
  protected void successfulAuthentication(HttpServletRequest req,
      HttpServletResponse res,
      FilterChain chain,
      Authentication auth) throws IOException, ServletException {
    String email = ((User) auth.getPrincipal()).getUsername();
    UserDto userDetails = userService.getUserDetailsByEmail(email);

    String token = Jwts.builder()
        .setSubject(userDetails.getUserId())
        .setExpiration(
            new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration_time"))))
        .signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret"))
        .compact();

    res.addHeader("token", token);
    res.addHeader("userId", userDetails.getUserId());
  }

}
