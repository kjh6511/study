package com.masjjim.config;

import com.masjjim.util.security.CustomAccessDeniedHandler;
import com.masjjim.util.security.CustomUserDetailsService;
import com.masjjim.util.security.RestAuthenticationEntryPoint;
import com.masjjim.util.security.jwt.filter.JwtAuthenticationFilter;
import com.masjjim.util.security.jwt.filter.JwtRequestFilter;
import com.masjjim.util.security.jwt.filter.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final JwtTokenProvider jwtTokenProvider;

    @Override
        protected void configure(HttpSecurity http) throws Exception {
            log.info("======= login =======");

            //폼 로그인 기증과 베이직 인증 비활성
            http.formLogin().disable()
                    .httpBasic().disable();

            http.cors();

            //CSRF 방지 지원 기능 비활성
            http.csrf().disable();

            //JWT 관련 필터 보안 컨텍스트에 추가
            http
            		.addFilterAt(new JwtAuthenticationFilter(authenticationManager(), jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                    .addFilterBefore(new JwtRequestFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            //웹경로 보안 설정
            http.authorizeRequests()
                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                    .antMatchers("/").permitAll() //모두허용
                    .antMatchers("/front/**").permitAll() //모두허용
                    .antMatchers("/member/register/**").access("permitAll")
                    .antMatchers("/member/check/**").permitAll() //모두허용
                    .antMatchers("/board/**").permitAll() //모두허용
                    .antMatchers("/**").authenticated() //권한 허용
                    .anyRequest().authenticated();

            http.logout()
                    .logoutUrl("/logout_out") //302 상태코드
                    .invalidateHttpSession(true)	// 세션 초기화
                    .deleteCookies("JSESSIONID","COOKIES")
                    .logoutSuccessUrl("/logout")
                    .permitAll();

            //처리
            http.exceptionHandling()
            .authenticationEntryPoint(new RestAuthenticationEntryPoint())
            .accessDeniedHandler(accessDeniedHandler());

        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }


        @Bean
        public AccessDeniedHandler accessDeniedHandler() {
        	return new CustomAccessDeniedHandler();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(customUserDetailsService())
                    .passwordEncoder(passwordEncoder());
        }


        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public UserDetailsService customUserDetailsService() {
            return new CustomUserDetailsService();
        }

        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
            final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

            CorsConfiguration config = new CorsConfiguration();
            config.setAllowCredentials(true);
    //        config.addAllowedOrigin("*");
            config.addAllowedOriginPattern("*");
            config.addAllowedHeader("*");
            config.addAllowedMethod("*");
            config.addAllowedMethod("OPTIONS");
            config.addAllowedMethod("HEAD");
            config.addAllowedMethod("GET");
            config.addAllowedMethod("PUT");
            config.addAllowedMethod("POST");
            config.addAllowedMethod("DELETE");
            config.addAllowedMethod("PATCH");
            config.setExposedHeaders(Arrays.asList("Authorization","Content-Disposition"));
            source.registerCorsConfiguration("/**", config);

            return source;
        }

    //swagger, static 설정
    @Override public void configure(WebSecurity web) {
        web .ignoring()
                .mvcMatchers("/swagger-ui.html/**", "/configuration/**", "/swagger-resources/**", "/v2/api-docs","/webjars/**", "/webjars/springfox-swagger-ui/*.{js,css}");
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        web.ignoring().antMatchers("/img/**","/css/**","/js/**","/vendor/**","/scss/**");
    }

}
