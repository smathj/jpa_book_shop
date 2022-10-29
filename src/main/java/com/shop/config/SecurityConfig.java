package com.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // ! 버전 업에 따라, WebSecurityConfigurerAdapter는 더이상 상속하여 사용하지않는다

//    @Autowired
//    MemberService memberService;

    // * Http 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage("/members/login")    // 로그인 페이지
                .defaultSuccessUrl("/") // 로그인 성공시 이동할 경로
                .usernameParameter("email") // 로그인 시 사용할 파라미터 이름을 email로 지정
                .failureUrl("/members/login/error") // 로그인 실패시 이동할 경로
                .and()
                .logout()   // 로그아웃 설정
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))// 로그아웃 경로 설정
                .logoutSuccessUrl("/")  // 로그아웃 성공시 이동할 경로
        ;


        http.authorizeRequests()
                .mvcMatchers("/css/**", "/js/**", "/img/**").permitAll()
                .mvcMatchers("/", "/members/**", "/item/**", "/images/**").permitAll()
                .mvcMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        ;

        // * 인증되지 않은 사용자가 리소스 접근했을때 수행되는 핸들러 등록
        http.exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
        ;

        return http.build();
    }

    // * 암호화
    // * 자주 사용하는 sha512 암호화할때 사용하는 Bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
