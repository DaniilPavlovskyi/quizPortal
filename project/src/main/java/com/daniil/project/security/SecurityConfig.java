package com.daniil.project.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery("select username, password, enabled from users where username=?");

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id, authority from authorities where user_id=?");

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/registration", "/api/global-score", "/showLoginPage", "/authenticateUser").permitAll()
                        .requestMatchers(HttpMethod.GET, "/", "/quiz**", "/results**", "/quizzes**").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/create-quiz").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/save").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/showLoginPage")
                        .loginProcessingUrl("/authenticateUser")
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll)
                .exceptionHandling(conf -> conf.accessDeniedPage("/access-denied"))
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
