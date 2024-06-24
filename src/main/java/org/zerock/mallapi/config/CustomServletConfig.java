package org.zerock.mallapi.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import org.zerock.mallapi.controller.formatter.LocalDateFormatter;

@Configuration
public class CustomServletConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {

        registry.addFormatter(new LocalDateFormatter());
    }

    //@Override
    //public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // registry.addResourceHandler("/**")
        //         .addResourceLocations("classpath:/sample/", "classpath:/static/")
        //         .setCacheControl(CacheControl.maxAge(Duration.ofDays(365)));
    

        // registry.addResourceHandler("/upload/**")
        //         .addResourceLocations("file:///Users/Jason/Desktop/uploads/");
 
    //s}
}
