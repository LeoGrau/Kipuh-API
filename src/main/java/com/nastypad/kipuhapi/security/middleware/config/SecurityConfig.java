package com.nastypad.kipuhapi.security.middleware.config;

import com.nastypad.kipuhapi.security.domain.persistence.UserRepository;
import com.nastypad.kipuhapi.security.domain.service.UserService;
import com.nastypad.kipuhapi.security.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration("securityConfig")
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

   @Bean
   public UserDetailsService userDetailsService()  {
      return new UserServiceImpl();
   }

//   @Bean
//   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//      return http.csrf().disable()
//              .authorizeHttpRequests()
//              .requestMatchers("/**").permitAll()
//              .and()
//              .authorizeHttpRequests().requestMatchers("/products/**")
//              .authenticated().and().formLogin().and().build();
//   }

   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }

   @Bean
   public AuthenticationProvider authenticationProvider() throws Exception {
      DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
      authenticationProvider.setUserDetailsService(userDetailsService());
      authenticationProvider.setPasswordEncoder(passwordEncoder());
      return authenticationProvider;
   }

   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
      return configuration.getAuthenticationManager();
   }

}
