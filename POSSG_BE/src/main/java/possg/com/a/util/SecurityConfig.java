package possg.com.a.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import possg.com.a.dto.ConvenienceDto;
import possg.com.a.dto.CustomerDto;
import possg.com.a.service.ConvenienceService;

@Component
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@CrossOrigin(origins = "http://localhost:3000") //CROS 설정
public class SecurityConfig {
		
	@Value("${custom.security.key}")
    public String securityKey;
	
	private final TokenCreate tokenCreate;
	
	@Autowired
	public SecurityConfig(TokenCreate tokenCreate) {
		this.tokenCreate = tokenCreate;
	}
	
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http        	
         	.authorizeRequests(request  ->
         	request         
         	.requestMatchers("/NoSecurityZoneController/**").permitAll()
        	.requestMatchers("/tokenController/**").permitAll()
        	//.requestMatchers("/public/**").permitAll()
        	.anyRequest().authenticated()        	
         		)
         	.csrf().disable()
        	.cors().and()
        	.addFilterBefore(new JwtFilter(tokenCreate), BasicAuthenticationFilter.class);

        return http.build();
    }
    
	  
}
