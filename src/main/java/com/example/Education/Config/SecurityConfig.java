package com.example.Education.Config;

import com.example.Education.Controllers.UserController;
import com.example.Education.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
/*@Bean
    UserDetailsService userDetailsService(UserController userController) {
        return username -> {
            User user = userController.findByUsername(username);
            if (user != null)
                return user;
            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }*/
    /*"/static/**","/styles.css"*/

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                /*.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)*/

                .authorizeHttpRequests(authorizeRequests ->authorizeRequests
                        .requestMatchers("/home", "/static/**").authenticated()
                        .requestMatchers("/login/*", "/static/**","/styles.css").permitAll()


                )

                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                        .defaultSuccessUrl("/home",true)
                        .permitAll()
                )
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/home")
                        .permitAll()
                )
                .build();
    }
    /*@Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(8);
    }*/

}
