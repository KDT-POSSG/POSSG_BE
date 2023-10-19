package possg.com.a.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import possg.com.a.handler.ApiExceptionHandler;
import possg.com.a.handler.CustomLogoutSuccessHandler;
import possg.com.a.service.ConvenienceService;

@Component
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@CrossOrigin(origins = "*") //CROS 설정
public class SecurityConfig {
		
	@Value("${custom.security.key}")
    public String securityKey;
	
	private final Environment env; //application에 있는 내용을 변수처럼 가져와서 쓸 수 있는 인터페이스
	
	private final TokenCreate tokenCreate;
	
	private final ConvenienceService service;
		
	
	@Autowired
	public SecurityConfig(Environment env, TokenCreate tokenCreate, ConvenienceService service) {
		this.env = env;
		this.tokenCreate = tokenCreate;
		this.service = service;
	}
	
	//
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http.addFilterBefore(new JwtFilter(tokenCreate), UsernamePasswordAuthenticationFilter.class);
    	
    	http.authorizeHttpRequests(req ->
			req				
				.requestMatchers("/NoSecurityZoneController/**", "/tokenController/**", "/healthcheck", "/afterLogout/**", "/dummy/**").permitAll()
				.requestMatchers("/myPage/**").hasAuthority("ROLE_CONVENIENCE")
				.anyRequest().authenticated()
				//.requestMatchers("/**").permitAll()
				)
		    	.exceptionHandling()
		        	.accessDeniedHandler(new ApiExceptionHandler());    	
    	http.csrf((csrf) -> csrf.disable());
    	http.cors();
    	   	
    	http
    		.logout()
    		.logoutUrl("/logout")
    		.logoutSuccessHandler(new CustomLogoutSuccessHandler(env, service));   

        return http.build();
    }

	  
}
