package com.example.APP.Company.infra.security;

import com.example.APP.Company.infra.customError.CustomAccessDeniedHandler;
import com.example.APP.Company.infra.customError.CustomAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private SecurityFilter securityFilter;

    @Autowired
    private  CustomAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private  CustomAccessDeniedHandler accessDeniedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(e -> e
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/position/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/user/register").hasAnyRole("USER    ", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/client/register").hasAnyRole("CLIENT", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/client/list").hasAnyRole("CLIENT", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/user/list").permitAll()
                        .requestMatchers(HttpMethod.GET, "/user/search").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/user/update/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/user/delete/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/position/list").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
}
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


}
