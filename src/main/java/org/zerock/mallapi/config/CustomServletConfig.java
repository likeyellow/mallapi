package org.zerock.mallapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.zerock.mallapi.controller.formatter.LocalDateFormatter;
import org.zerock.mallapi.controller.formatter.LocalDateTimeFormatter;

@Configuration
public class CustomServletConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {

        registry.addFormatter(new LocalDateFormatter());
        registry.addFormatter(new LocalDateTimeFormatter()); // accessTime 기록을 위한 포매터
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
