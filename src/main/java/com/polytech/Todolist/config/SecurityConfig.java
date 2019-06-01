package com.polytech.Todolist.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
    private DataSource datasource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.jdbcAuthentication().dataSource(datasource)
        .passwordEncoder(passwordEncoder);
 
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().defaultSuccessUrl("/todolist", true)
		.and().authorizeRequests().mvcMatchers("/","/registerPage","/register").permitAll().anyRequest().authenticated()
		.and().csrf().disable();
		
    }
}
