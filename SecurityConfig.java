package com.fashionpeak.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * SecurityConfig - Configuración de Spring Security para Fashion Peak.
 * Define roles, usuarios y permisos de acceso por URL.
 *
 * @author Laura Valentina Torres Chaparro
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configura el filtro de seguridad HTTP.
     * Define qué rutas requieren autenticación y cuáles son públicas.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Rutas públicas - no requieren login
                .requestMatchers("/", "/catalogo", "/login", "/carrito",
                                 "/confirmacion", "/css/**", "/js/**",
                                 "/img/**", "/webjars/**").permitAll()
                // Rutas de admin - requieren rol ADMIN
                .requestMatchers("/productos/**").hasRole("ADMIN")
                // Cualquier otra ruta requiere autenticación
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/productos", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .permitAll()
            );

        return http.build();
    }

    /**
     * Define los usuarios en memoria para autenticación.
     * En producción se usaría una base de datos.
     */
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails admin = User.builder()
            .username("admin@fashionpeak.com")
            .password(encoder.encode("admin123"))
            .roles("ADMIN")
            .build();

        UserDetails cliente = User.builder()
            .username("cmartinez291@gmail.com")
            .password(encoder.encode("12345678"))
            .roles("CLIENTE")
            .build();

        return new InMemoryUserDetailsManager(admin, cliente);
    }

    /** Codificador de contraseñas BCrypt */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
