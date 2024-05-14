package com.academy.TransDana.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Set;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final static String ROLE_ADMIN = "ROLE_ADMIN";
    private final static String ROLE_OPERATOR = "ROLE_OPERATOR";
    public final static String ROLE_USER = "ROLE_USER";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) ->
                        authorize
                                .requestMatchers("/users/routes").hasAnyAuthority(ROLE_USER, ROLE_ADMIN)
                                .requestMatchers("/operator/routes").hasAnyAuthority(ROLE_OPERATOR, ROLE_ADMIN)
                                .requestMatchers("/routes").hasAnyAuthority(ROLE_ADMIN)
                                .anyRequest().authenticated()

                ).formLogin(
                        form -> form
                                .successHandler(((request, response, authentication) -> {
                                    Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
                                    if (roles.contains(ROLE_ADMIN)){
                                        response.sendRedirect("/admin/routes");
                                    } else if (roles.contains(ROLE_USER)){
                                        response.sendRedirect("/users/routes");
                                    } else if (roles.contains(ROLE_OPERATOR)){
                                        response.sendRedirect("/operator/routes");
                                    }
                                }))
                                .permitAll()
                ).logout((logout) -> logout.logoutSuccessUrl("/login"));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
