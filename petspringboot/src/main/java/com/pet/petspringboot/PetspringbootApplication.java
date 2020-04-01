package com.pet.petspringboot;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.MultipartConfigElement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@SpringBootApplication(exclude = {MultipartAutoConfiguration.class})
public class PetspringbootApplication{
    @Bean
    public MultipartConfigElement configElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("2048MB");
        factory.setMaxRequestSize("2048MB");
        return factory.createMultipartConfig();
    }


   /* @Configuration
    public class WebConfig extends WebMvcConfigurerAdapter {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {

            registry.addResourceHandler("/petspringboot/**")
                    // /apple/**表示在磁盘apple目录下的所有资源会被解析为以下的路径
                    *//*  .addResourceLocations("file:E:/petspringboot/file/") //媒体资源*//*
                    .addResourceLocations("classpath:/petspringboot/")
              .addResourceLocations("classpath:/META-INF/file/");
            super.addResourceHandlers(registry);
        }
    }*/
  /*跨域配置*/
   /* @Configuration
    public class WebConfig extends WebMvcConfigurerAdapter {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost:8080", "null")
                    .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                    .maxAge(3600)
                    .allowCredentials(true);
        }
    }*/

    /**
     * 语音测评后台服务
     * 创建者 柒
     * 创建时间    2018年3月8日
     */
   /* @Configuration
    public class WechatApplication extends WebMvcConfigurerAdapter  {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/petspringboot/**").addResourceLocations("file:E:/petspringboot/file");
            super.addResourceHandlers(registry);
        }
    }
*/
    @Configuration
    public class ApplicationConfig extends WebMvcConfigurerAdapter {

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            /**
             * 如果我们将/xxxx/** 修改为 /** 与默认的相同时，则会覆盖系统的配置，可以多次使用 addResourceLocations 添加目录，
             * 优先级先添加的高于后添加的。
             *
             * 如果是/xxxx/** 引用静态资源 加不加/xxxx/ 均可，因为系统默认配置（/**）也会作用
             * 如果是/** 会覆盖默认配置，应用addResourceLocations添加所有会用到的静态资源地址，系统默认不会再起作用
             */
            registry.addResourceHandler("/**")
                    .addResourceLocations("classpath:/META-INF/resources/")
                    .addResourceLocations("classpath:/resources/")
                    .addResourceLocations("classpath:/static/")
                    .addResourceLocations("classpath:/public/");
            registry.addResourceHandler("/we/**").addResourceLocations("file:E:/petspringboot/file/");
            super.addResourceHandlers(registry);
        }
    }


    public static void main(String[] args) {
        SpringApplication.run(PetspringbootApplication.class, args);
    }
}
