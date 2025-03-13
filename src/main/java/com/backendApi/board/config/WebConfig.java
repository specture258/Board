package com.backendApi.board.config;

import com.backendApi.board.interceptor.AdminCheckInterceptor;
import com.backendApi.board.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/board/**")
                .excludePathPatterns("/board/signin", "/board/signup", "/board" );

        registry.addInterceptor(new AdminCheckInterceptor())
                .order(2)
                .addPathPatterns("/board/management/**")
                .excludePathPatterns("/board/signin", "/board/signup", "/board" );
    }
}
