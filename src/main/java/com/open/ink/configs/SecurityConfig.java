package com.open.ink.configs;

import com.open.ink.configs.jwt.JwtAuthenticationEntryPoint;
import com.open.ink.configs.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;




@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity

//@EnableWebMvc//during api doc

@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	@Autowired
	private JwtAuthenticationEntryPoint authEntryPoint;
	
	@Autowired
	private JwtAuthenticationFilter authFilter;

	@Autowired
	private UserDetailServiceImple userDetailsServiceImple;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors(Customizer.withDefaults())
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests((authz) -> authz
						.requestMatchers("/api/v1/auth/login").permitAll()
						.requestMatchers("/api/v1/auth/register").permitAll()
						.requestMatchers("/api/v1/auth/**").permitAll()
						.requestMatchers("/swagger-ui/**").permitAll()
						.requestMatchers("/images/**").permitAll()
                        .requestMatchers(HttpMethod.GET).permitAll()
						.anyRequest().authenticated())

				.exceptionHandling( exceptions -> exceptions.authenticationEntryPoint(authEntryPoint))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


		http.authenticationProvider(this.authenticationProvider());

		http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

		DefaultSecurityFilterChain myConfigure = http.build();
		return myConfigure;
	}

	

	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsServiceImple);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

 	@Bean
    public AuthenticationManager authenticationManager() {
        ProviderManager providerManager = new ProviderManager(Collections.singletonList(authenticationProvider()));
        return providerManager;
    }
}
