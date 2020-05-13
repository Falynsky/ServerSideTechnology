package com.falynsky.tss4.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final UserDetailsServiceImpl userDetailsService;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
/*
        //add line below if you want to test rest api in Postman and method corsConfigurationSource() to fill
        http.cors().and().csrf().disable();
*/
        http.requiresChannel().anyRequest().requiresSecure();
        http.authorizeRequests()
                .antMatchers("/users/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/files/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/grades").hasAuthority("ROLE_ADMIN")
                .and()
                .formLogin().defaultSuccessUrl("/grades");
    }

/*    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        List<String> allowed = Collections.singletonList("*");
        configuration.setAllowedOrigins(allowed);
        configuration.setAllowedMethods(allowed);
        configuration.setAllowedHeaders(allowed);
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }*/
}
