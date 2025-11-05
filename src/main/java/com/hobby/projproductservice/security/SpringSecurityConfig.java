package com.hobby.projproductservice.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Value("${productservice.security.oauth.enabled:false}")
    private boolean securityEnabled;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        if (securityEnabled) {
            // 🔒 Security enabled — protect endpoints with JWT
            http
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/products/{id}").authenticated()
                            .requestMatchers("/products").hasAuthority("SCOPE_ADMIN")
                            .anyRequest().permitAll()
                    )
                    .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        } else {
            // 🔓 Security disabled — allow all requests
            http
                    .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                    .csrf(csrf -> csrf.disable());
        }

        return http.build();
    }
}
