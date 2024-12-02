package HyEnd.Proj.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.*;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        // 올바른 URL 패턴으로 수정
                        .requestMatchers("/", "/index.html", "/static/**").permitAll()
                        .requestMatchers("/users/**").permitAll() // 특정 엔드포인트 허용
                        .anyRequest().permitAll()//.authenticated()
                )
                .formLogin(form -> form.disable());
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:63342"); // 프론트엔드 주소 허용
        configuration.setAllowCredentials(true);
        configuration.addAllowedMethod("*"); // 모든 HTTP 메소드 허용
        configuration.addAllowedHeader("*"); // 모든 헤더 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
