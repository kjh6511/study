package com.reviewer.reviewer.config;

import com.reviewer.reviewer.security.UserSecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
@Slf4j
public class SecurityConfig {

    private final UserSecurityService userSecurityService;

    private final AuthenticationFailureHandler customFailureHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //웹소켓 보안 설정
//        http.headers().frameOptions().sameOrigin();

        //폼 로그인 기증과 베이직 인증 비활성
          /*    http.formLogin().disable()
                      .httpBasic().disable();*/

        http.formLogin((formLogin) ->
                formLogin.loginPage("/login")
//                        .failureUrl("/login-error")
                        .failureHandler(customFailureHandler)
                        .defaultSuccessUrl("/"));

//        http.cors();

        //CSRF 방지 지원 기능 비활성
        http.csrf((csrf) -> csrf.disable());

        //웹경로 보안 설정
        http.authorizeHttpRequests((authz) -> authz
                .requestMatchers("/").permitAll()//모두허용
                .requestMatchers("/**").permitAll()//권한 허용
                .anyRequest().authenticated() //권한이 있기만 하면 접근
        );


        http.logout((logout) ->
                logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true) //세션 날리기
                        .deleteCookies("JSESSIONID")); //특정쿠키 제거

        return http.build();

    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/css/**", "/script/**", "/image/**", "/js/**");
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
       return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
