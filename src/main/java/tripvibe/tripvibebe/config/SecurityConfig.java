package tripvibe.tripvibebe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf((csrf) -> csrf.disable()); // 나중에 이거 삭제해야함
        http.authorizeHttpRequests((authorizeRequests) -> authorizeRequests
                        .requestMatchers("/**").permitAll());  // 특정 경로를 허용

        //==추가==//
//        http.cors(httpSecurityCorsConfigurer
//                -> httpSecurityCorsConfigurer
//                .configurationSource(corsConfigurationSource()));

//        http.formLogin((formLogin)
//                -> formLogin.loginPage("/tripvibe/signin") // 로그인 폼이 있는 페이지 url
//                .defaultSuccessUrl("/") // 로그인 성공시 url
//                .failureUrl("/fail")// 로그인 실패시 url
//        );
        return http.build();
    }


    // 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Spring 보안 및 인증 담당
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}



