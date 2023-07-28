package com.example.casestudy.config;
import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // Đặt giới hạn dung lượng file 10MB
    private static final long MAX_FILE_SIZE = 1024 * 1024 * 2048;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofBytes(MAX_FILE_SIZE));
        factory.setMaxRequestSize(DataSize.ofBytes(MAX_FILE_SIZE));
        return factory.createMultipartConfig();
    }
}
