package com.example.cloud.users.userws.security;

import com.example.cloud.users.userws.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

//configurerAdapter
@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

  private Environment environment;
  private UserService userService;
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public WebSecurity(Environment environment, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.environment = environment;
    this.userService = userService;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  // 2 while initializing
  // throws Exception
  @Override
  public void configure(HttpSecurity http) throws Exception {

    http.csrf().disable();
    // Permits all paths starts with /users/** except for /users/login (Passes
    // through CustomAuthenticationFilter)
    http.authorizeRequests().antMatchers("/users/**").permitAll().and().addFilter(getAuthenticationFilter());
    http.headers().frameOptions().disable();

  }

  private CustomAuthenticationFilter getAuthenticationFilter() throws Exception {
    CustomAuthenticationFilter authenticationFilter = new CustomAuthenticationFilter(userService, environment,
        authenticationManager());
    // authenticationFilter.setAuthenticationManager(authenticationManager());
    authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));
    return authenticationFilter;
  }

  // 1 while initializing
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
  }

}
