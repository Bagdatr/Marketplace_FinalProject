package kz.rakhimov.marketplace_java_final_project.config;

import kz.rakhimov.marketplace_java_final_project.services.MainUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public MainUserService userService(){
        return new MainUserService();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userService())
                .passwordEncoder(passwordEncoder());
        http.formLogin()
                .loginPage("/main/login")
                .loginProcessingUrl("/sign-in")
                .defaultSuccessUrl("/main/home")
                .failureUrl("/login?error")
                .usernameParameter("user-email")
                .passwordParameter("user-password");
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/main/login");
        http.exceptionHandling().accessDeniedPage("/403");
        return http.build();
    }
}