package com.looknlook.looknlook.config;

import com.looknlook.looknlook.util.security.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //웹소켓 보안 설정
        http.headers().frameOptions().sameOrigin();


        //폼 로그인 기증과 베이직 인증 비활성
    /*    http.formLogin().disable()
                .httpBasic().disable();*/
        http.formLogin()
            .loginPage("/")
            .failureUrl("/login-error")
             .defaultSuccessUrl("/home");

        http.cors();

        //CSRF 방지 지원 기능 비활성
        http.csrf().disable();

        //웹경로 보안 설정
        http.authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers("/").permitAll() //모두허용
                .antMatchers("/**").permitAll() //권한 허용
                .anyRequest().authenticated(); //권한이 있기만 하면 접근

        http.logout()
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true) //세션 날리기
                .deleteCookies("JSESSIONID"); //특정쿠키 제거

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception { // 9
      auth.userDetailsService(userService)
      	// 해당 서비스(userService)에서는 UserDetailsService를 implements해서
          // loadUserByUsername() 구현해야함 (서비스 참고)
      	.passwordEncoder(new BCryptPasswordEncoder());
     }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/script/**", "image/**", "/fonts/**", "lib/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
