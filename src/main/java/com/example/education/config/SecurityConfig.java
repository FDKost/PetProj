package com.example.education.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfAuthenticationStrategy;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.csrf.XorCsrfTokenRequestAttributeHandler;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.ws.config.annotation.EnableWs;

@Configuration
@EnableWebSecurity
@EnableWs
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    HttpSessionCsrfTokenRepository csrfTokenRepository = new HttpSessionCsrfTokenRepository();
        return http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(csrf ->
                        csrf.csrfTokenRequestHandler(new XorCsrfTokenRequestAttributeHandler())
                                .csrfTokenRepository(csrfTokenRepository)
                                .sessionAuthenticationStrategy(new CsrfAuthenticationStrategy(csrfTokenRepository))
                                .ignoringRequestMatchers(new AntPathRequestMatcher("/static/**"))
                                /*.requireCsrfProtectionMatcher(new AntPathRequestMatcher("/profile/create"))*/)

                .authorizeHttpRequests(authorizeRequests ->authorizeRequests
                        .requestMatchers( "/static/**","/order/**",
                                        "/profile/create","/profile/**","/api/**","/cart","/orders/**","/payment/**","/productsInOrder/**").authenticated()
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .requestMatchers("/styles.css","/home","/login/**", "/static/**","/registration","/logRegCSS.css","/images/**","/contact").permitAll()


                )
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                        .loginPage("/login")
                        .defaultSuccessUrl("/home",true)
                        .permitAll()
                )
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .permitAll()
                )
                .build();
    }
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(8);
    }
}
