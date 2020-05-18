package com.falynsky.tss4.services;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class DefaultView implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/user/welcome-page");
        registry.addViewController("/login?logout").setViewName("redirect:/user/welcome-page");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}