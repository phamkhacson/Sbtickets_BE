package com.example.sbtickets.authentication.security;

import com.example.sbtickets.common.UrlConst;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
        jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
        return jwtAuthenticationTokenFilter;
    }
    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {

        return new RestAuthenticationEntryPoint();
    }
    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    protected void configure(HttpSecurity http) throws Exception {
        // Disable crsf cho đường dẫn /Sbtickets/**
        http.csrf().ignoringAntMatchers(UrlConst.HOME + "/**");
        http.authorizeRequests().antMatchers(UrlConst.HOME + "/login**").permitAll();
        http.authorizeRequests().antMatchers(UrlConst.HOME_USER.HOME_USER + "/**").permitAll();
        // bean restServicesEntryPoint sẽ xử lý những request chưa được xác thực.
        http.antMatcher(UrlConst.HOME + "/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers(HttpMethod.GET, UrlConst.HOMEADIM.HOMEADIM + "/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.GET, UrlConst.HOME_USER.HOME_USER + "/**").access("hasRole('ROLE_USER')")
                .antMatchers(HttpMethod.POST, UrlConst.HOME_USER.HOME_USER + "/**").access("hasRole('ROLE_USER')")
                .antMatchers(HttpMethod.POST, UrlConst.HOMEADIM.HOMEADIM + "/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.DELETE, UrlConst.HOMEADIM.HOMEADIM + "/**").access("hasRole('ROLE_ADMIN')").and()
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "PUT", "POST", "DELETE", "OPTIONS");
            }
        };
    }
}
