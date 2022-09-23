package com.example.restfulwebservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {





    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/h2-console/**").permitAll();  // 통과  (인증처리 넘김..)
        http.csrf().disable();  // csrf 사용안함
        http.headers().frameOptions().disable();  // 헤더의 프레임값도 사용안함
    }



    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {

        auth.inMemoryAuthentication()
                .withUser("lee")
                .password("{noop}test1234")
                .roles("USER");
        // {noop} :  인코딩없이 사용하겠다
        // 로그인 완료되면 권한 주기
    }
}
