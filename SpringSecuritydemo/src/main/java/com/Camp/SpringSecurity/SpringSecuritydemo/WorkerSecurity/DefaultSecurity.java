package com.Camp.SpringSecurity.SpringSecuritydemo.WorkerSecurity;

import com.Camp.SpringSecurity.SpringSecuritydemo.Filter.JwtFilter;
import com.Camp.SpringSecurity.SpringSecuritydemo.WorkerService.WorkerUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class DefaultSecurity {

    @Autowired
    private JwtFilter jwtFilter;
    @Bean
    public PasswordEncoder getPasswordEncoder( ){
        return new BCryptPasswordEncoder();
    }
    @Bean
     public SecurityFilterChain defaultSecurityChain( HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                authorize -> {
                    authorize.requestMatchers("/user/save", "/user/authenticate").permitAll();
                    authorize.anyRequest().authenticated(); }
                ).formLogin(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); //
        return  http.build();
    }
    /*
   @Bean
    public UserDetailsService userDetailsService (PasswordEncoder encoder ){

        UserDetails user1 = User.withUsername("Shiblee")
                .password(getPasswordEncoder().encode("12345"))
                .roles("Admin")
                .build();
        UserDetails user2 = User.withUsername("anyone")
                .password(getPasswordEncoder().encode("12345"))
                .roles("user")
                .build();


        return new InMemoryUserDetailsManager(user1,user2);
   }
*/
   @Bean
   public UserDetailsService getUserdetails ( ){

       return new WorkerUserDetailService();
   }
    @Bean
    public AuthenticationProvider authenticationProvider( ){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(getUserdetails());
        authenticationProvider.setPasswordEncoder(getPasswordEncoder());
        return authenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
     return authConfig.getAuthenticationManager();
   }




}
