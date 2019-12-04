package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Administrator on 2019/11/30.
 */
@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            // 重写父类提供的跨域请求处理接口
            public void addCorsMappings(CorsRegistry registry) {
                // 添加映射路径
                registry.addMapping("/**")
                        // 放行那些原始域
                        .allowedOrigins("*")
                        // 是否发送Cookie信息
                        .allowCredentials(true)
                        // 放行那些方法
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        // 放行那些原始域(头部信息)
                        .allowedHeaders("*")
                        // 暴露哪些头部信息
                        .exposedHeaders("token");
            }
        };
    }
}
