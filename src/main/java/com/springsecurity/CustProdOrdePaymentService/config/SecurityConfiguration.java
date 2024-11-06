package com.springsecurity.CustProdOrdePaymentService.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfiguration {

    @Autowired
    private MyUserDetailasService userDetailasService;

    /**
     * Configures the HTTP security filter chain.
     */
    @Bean
    public SecurityFilterChain config(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // Disable CSRF for the endpoints that need to accept multipart requests (file uploads)
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/api/products/save", "/api/customers/save")
                )
                .authorizeHttpRequests(req -> req
                        // Allow these specific endpoints for unauthenticated access
                        .requestMatchers("/api/customers/save", "/api/customers/**").permitAll()
                        .requestMatchers("/api/products/save").permitAll()  // Public endpoint for file upload
                        .anyRequest().authenticated()) // Other requests need authentication
                .logout(logout -> logout.permitAll()) // Allow logout endpoint
                .build();
    }

    /**
     * Password encoder bean, using BCrypt hashing algorithm.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Provides UserDetailsService for loading user-specific data during authentication.
     */
    @Bean
    public UserDetailsService userDetailasService() {
        return userDetailasService;
    }

    /**
     * Sets up DaoAuthenticationProvider with user details and password encoder.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailasService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * Custom authentication manager that uses the DaoAuthenticationProvider.
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(authenticationProvider());
    }
}
