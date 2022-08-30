package org.vdev.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.vdev.constants.Constants;
import org.vdev.filter.InitializationRequestFilter;
import org.vdev.filter.JwtRequestFilter;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	@Autowired
	private InitializationRequestFilter initializationRequestFilter;
	@Autowired
	private Environment env;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
    public Cloudinary cloudinary() {
		String cloudName = env.getProperty("cloudiary.cloud-name"); 
		String apiKey = env.getProperty("cloudiary.api-key");
		String apiSecret = env.getProperty("cloudiary.api-secret");
        Cloudinary c = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret,
                "secure", true
        ));

        return c;
    }
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()

				.antMatchers("/api/user/**").hasAnyRole(Constants.ROLE.USER, Constants.ROLE.STAFF, Constants.ROLE.ADMIN)
				.antMatchers("/api/staff/**").hasAnyRole(Constants.ROLE.STAFF, Constants.ROLE.ADMIN)
				.antMatchers("/api/admin/**").hasRole(Constants.ROLE.ADMIN)
				.and().authorizeRequests().antMatchers("/api/authenticate", "/api/initialization", "/api/info/**", "/api/login/**").permitAll()
				.anyRequest().authenticated()
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(initializationRequestFilter, UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
}
