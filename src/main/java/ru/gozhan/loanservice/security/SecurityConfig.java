package ru.gozhan.loanservice.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final DataSource dataSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf().disable()
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/", "/loan-service.html",
                                "/loan-service/getTariffs", "/loan-service/getTariffs-view",
                                "/loan-service/order", "/loan-service/deleteOrder").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin()
                    .defaultSuccessUrl("/")
                    .permitAll()
                .and()
                .logout()
                    .logoutSuccessUrl("/")
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true)
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager() {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "SELECT username, password, enabled FROM usr WHERE username = ?"
        );
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT username, 'ROLE_USER' FROM usr WHERE username = ?"
        );
        jdbcUserDetailsManager.setCreateUserSql(
                "INSERT INTO usr (username, password, enabled, account_non_expired, " +
                        "credentials_non_expired, account_non_locked) " +
                        "VALUES (?, ?, ?, ?, ?, ?)"
        );
        jdbcUserDetailsManager.setChangePasswordSql(
                "UPDATE usr SET password = ? WHERE username = ?"
        );
        jdbcUserDetailsManager.setDeleteUserSql(
                "DELETE FROM usr WHERE username = ?"
        );

        return jdbcUserDetailsManager;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() throws Exception {

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(jdbcUserDetailsManager());
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }

}
