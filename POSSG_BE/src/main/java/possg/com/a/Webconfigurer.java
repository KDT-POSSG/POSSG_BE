package possg.com.a;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 접속 허가 설정
@Configuration
public class Webconfigurer implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
		registry.addMapping("/**").allowedOrigins("http://localhost:3000");
		//registry.addMapping("/**").allowedOrigins("*");	//모두허용
	}
	
	
	
}
