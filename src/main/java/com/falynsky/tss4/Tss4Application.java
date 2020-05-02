package com.falynsky.tss4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
public class Tss4Application {

    public static void main(String[] args) {
        SpringApplication.run(Tss4Application.class, args);
    }

    @Bean
    public MessageSource messageSource() {
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/tss_pl");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("ISO-8859-2");
        messageSource.setCacheSeconds(5);
        return messageSource;
    }
}


