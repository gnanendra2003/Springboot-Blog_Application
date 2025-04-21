package com.gnana.blog.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gnana.blog.utility.ErrorStructure;
import com.gnana.blog.utility.ResponseStructure;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {
    @Value("${app.client.origin}")
    public String clientOrigin;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(HttpMethod.GET, "/blogs/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/register").permitAll()
                                .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginProcessingUrl("/login")
                        .successHandler(this::successHandler)
                        .failureHandler(this::failureHandler))
                .build();
    }

    private void failureHandler(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        ErrorStructure error = new ErrorStructure();
        error.setStatus(HttpStatus.UNAUTHORIZED.value());
        error.setMessage("Login failed: " + exception.getMessage());

        writeJsonResponse(response, error, HttpStatus.UNAUTHORIZED);
    }

    private void successHandler(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        ResponseStructure<Object> responseBody = ResponseStructure.createResponse(HttpStatus.OK.value(),"Login Successful.",authentication.getPrincipal());
        writeJsonResponse(response, responseBody, HttpStatus.OK);
    }

    private <T> void writeJsonResponse(HttpServletResponse response, T body, HttpStatus status) throws IOException {
        try {
            response.setStatus(status.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(response.getWriter(), body);
        } catch (IOException e) {
            log.error("Error writing JSON response", e);
        }
    }

    @Bean
    @Primary
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin(clientOrigin);
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
