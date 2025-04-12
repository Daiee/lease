package com.daie.lease.common.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        // 创建 CORS 配置对象
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true); // 允许携带 Cookie
        corsConfiguration.addAllowedOriginPattern("*"); // 允许所有来源，生产环境建议限制为特定域名
        corsConfiguration.addAllowedHeader("*"); // 允许所有头部
        corsConfiguration.addAllowedMethod("*"); // 允许所有方法

        // 将 CORS 配置应用到指定路径
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration); // 对所有路径生效
        return new CorsFilter(source);
    }
}
