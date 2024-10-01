package com.backend.iLearn.config;

import com.backend.iLearn.modules.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final UserRepository userRepository;

   @Bean
   public UserDetailsService userDetailsService(){
       System.out.println("In userDetailsService **************** ");

       return username -> userRepository.findByEmail_(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
   }

   @Bean
    public AuthenticationProvider adminAuthenticationProvider(){
       System.out.println("authenticationProvider *********************    3");
       DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
       System.out.println("authenticationProvider *********************    3*1");
       authenticationProvider.setUserDetailsService(userDetailsService());
       System.out.println("authenticationProvider *********************    3*2");
       authenticationProvider.setPasswordEncoder(passwordEncoder());
       System.out.println("setUserDetailsService and setPasswordEncoder in authenticationProvider *********************    5");
       return authenticationProvider;
   }

   @Bean
    public PasswordEncoder passwordEncoder() {
       System.out.println("passwordEncoder *********************    1");
       return  new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        System.out.println("authenticationManager *********************    2");
       return config.getAuthenticationManager();
    }

}
