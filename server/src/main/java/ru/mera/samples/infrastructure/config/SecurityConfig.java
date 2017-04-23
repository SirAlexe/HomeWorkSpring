package ru.mera.samples.infrastructure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("===>configureGlobal");
		auth.inMemoryAuthentication().withUser("rest").password("rocks").roles("USER").and().withUser("admin")
				.password("admin").roles("ADMIN");
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("===>configure");
		http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
		
	}
}
