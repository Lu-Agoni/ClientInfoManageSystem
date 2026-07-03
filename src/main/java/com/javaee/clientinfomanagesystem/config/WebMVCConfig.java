package com.javaee.clientinfomanagesystem.config;
import com.javaee.clientinfomanagesystem.intercept.userIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new userIntercepter())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/user/login",
                        "/user/dologin",
                        "/user/register",
                        "/user/doregister",
                        "/CSS/**",
                        "/js/**",
                        "/images/**"
                );
    }
}