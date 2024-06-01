package com.example.ejercicio_clase9.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class WebSecurityConfig {

    final DataSource dataSource;

    public WebSecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsManager users(DataSource dataSource){
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        String sql1 = "SELECT username, password, status FROM users WHERE username = ?";
        String sql2 = "SELECT u.username, r.name FROM users u "
                + "INNER JOIN roles r ON (u.idrol = r.id) "
                + "WHERE u.username = ? and u.status = 1";

        users.setUsersByUsernameQuery(sql1);
        users.setAuthoritiesByUsernameQuery(sql2);
        return users;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable();
        http.httpBasic();


        http.authorizeHttpRequests()
                .requestMatchers("/ws/personaje/**").hasAuthority("ADMIN")
                .requestMatchers("/ws/personaje/list", "ws/personaje/get/").hasAnyAuthority("USER", "EDITOR")
                .requestMatchers("ws/personaje/delete/").hasAuthority("EDITOR")
                .anyRequest().permitAll();

        return http.build();
    }
}
