/*
package com.pet.petspringboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

@Configuration
public class UploadConfig {
    @Bean
    public MultipartConfigElement multipartConfigElement(
            @Value("${spring.http.multipart.maxFileSize}") String maxFileSize,
            @Value("${spring.http.multipart.maxRequestSize}") String maxRequestSize) {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(maxFileSize);
        factory.setMaxRequestSize(maxRequestSize);
        return factory.createMultipartConfig();
    }
}
*/
