package org.example.securityspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/cat-page.html").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .defaultSuccessUrl("/cat-page", true)
                .and()
                .logout()
                .logoutSuccessUrl("/login");
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return new InMemoryUserDetailsManager(
                org.springframework.security.core.userdetails.User.withUsername("user")
                        .password(encoder.encode("password"))
                        .roles("USER").build(),
                org.springframework.security.core.userdetails.User.withUsername("admin")
                        .password(encoder.encode("password"))
                        .roles("ADMIN").build()
        );
    }
}
