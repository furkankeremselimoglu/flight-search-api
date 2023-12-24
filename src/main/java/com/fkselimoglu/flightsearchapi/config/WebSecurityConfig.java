package com.fkselimoglu.flightsearchapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Basic authentication
                .httpBasic(httpBasicConfigurer -> httpBasicConfigurer.realmName("YourRealm"))
                // Disable CSRF
                .csrf(csrfConfigurer -> csrfConfigurer.disable())
                // Configure CORS (if necessary)
                .cors(corsConfigurer -> corsConfigurer.disable())
                // Configure authorization
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                        .anyRequest().authenticated())
                // Stateless session management
                .sessionManagement(sessionManagementConfigurer ->
                        sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
