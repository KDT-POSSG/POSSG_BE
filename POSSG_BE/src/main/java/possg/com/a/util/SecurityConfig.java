package possg.com.a.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import possg.com.a.dto.ConvenienceDto;
import possg.com.a.service.ConvenienceService;

@Component
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Value("${custom.security.key}")
    public static String securityKey;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final DataSource dataSource;
    
    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
        	.httpBasic().disable()
        	.csrf().disable()
         	.cors().and()
            .authorizeRequests(authorizeRequests ->
                authorizeRequests
                	.requestMatchers("/register").permitAll()
                	.requestMatchers("/login").permitAll()
                	                	
            );

        return http.build();
    }
	
		
	
	  private final Long expiredTime = 1000 * 60L * 60L * 24L; // 유효기간 1일
	  
	  @Autowired
	  ConvenienceService service;	  	  
	  /**
	   * Member 정보를 담은 JWT 토큰을 생성한다.
	   *
	   * @param member Member 정보를 담은 객체
	   * @return String JWT 토큰
	   */
	  public String generateJwtToken(ConvenienceDto dto) {
		  System.out.println("securityKey" + securityKey);
	    Date now = new Date();
	    
	    return Jwts.builder()
	        .setSubject(dto.getRepresentativeName()) // 보통 username
	        .setHeader(createHeader())
	        .setClaims(createClaims(dto)) // 클레임, 토큰에 포함될 정보
	        .setExpiration(new Date(now.getTime() + expiredTime)) // 만료일
	        .signWith(SignatureAlgorithm.HS256, securityKey)
	        .compact();
	  }
	  
	// Refresh Token 생성 로직 예시
	  public String generateRefreshToken(ConvenienceDto dto) {
	      Date now = new Date();
	      
	      return Jwts.builder()
	          .setSubject(dto.getRepresentativeName())
	          .setHeader(createHeader())
	          .setClaims(createClaims(dto)) // 사용자 ID 등의 정보 포함
	          .setIssuedAt(now)
	          .setExpiration(new Date(now.getTime() + expiredTime)) // 적절한 유효기간 설정
	          .signWith(SignatureAlgorithm.HS256, securityKey)
	          .compact();
	  }
	  
	  
	  // token header
	  private Map<String, Object> createHeader() {
		    Map<String, Object> header = new HashMap<>();
		    header.put("typ", "JWT");
		    header.put("alg", "HS256"); // 해시 256 사용하여 암호화
		    header.put("regDate", System.currentTimeMillis());
		    return header;
		  }

	  // claim
	  
	  private Map<String, Object> createClaims(ConvenienceDto dto) {
		    Map<String, Object> claims = new HashMap<>();
		    // claim에 유저 정보 넣기
	          claims.put("userName", dto.getRepresentativeName()); // username
	          claims.put("userId", dto.getUserId()); 
	          claims.put("branchName", dto.getBranchName());
	          claims.put("regiDate", dto.getRegistrationDate());
	          claims.put("convSeq", dto.getConvSeq());  
		    
		    /*claims.put("userdata", service.mypage(securityKey));*/
		    return claims;
		  }
	  
	  // token에서 원하는 값 추출하는 로직
	   public static Claims tokenParser(String tokenHeader) {
	      
	      // "Bearer " 문자열을 제거하여 실제 토큰을 추출
	       String accessToken = tokenHeader.replace("Bearer ", "");

	       // JWT 토큰 검증
	          JwtParser jwtParser = Jwts.parserBuilder()
	                 .setSigningKey(securityKey)
	                 .build();

	           Claims claims = jwtParser.parseClaimsJws(accessToken).getBody();

	           // 사용자 ID 추출
	           //int customerSeq = claims.get("customerSeq", Integer.class);
	           
	           return claims;
	   }   
	  
	  private Claims getClaims(String token) {
		  JwtParser jwtParser = Jwts.parserBuilder()
	    		    .setSigningKey(securityKey) // 여기서 secretKey는 생성한 시크릿 키입니다.
	    		    .build();
		  
		  return jwtParser.parseClaimsJws(token).getBody();
	  }
	  
	  public String getuserNameFromToken(String token) {
		  return (String) getClaims(token).get("userName");
	  }
	  
	  public String getuserIdFromToken(String token) {
		  return (String) getClaims(token).get("userId");
	  }
	  
	  public String getbranchNameFromToken(String token) {
		  return (String) getClaims(token).get("branchName");
	  }
	  
	  public String getpwdFromToken(String token) {
		  return (String) getClaims(token).get("pwd");
	  }
	  
	  public String getphoneNumFromToken(String token) {
		  return (String) getClaims(token).get("phoneNum");
	  }
	  
	  public String getregiDateFromToken(String token) {
		  return (String) getClaims(token).get("regiDate");
	  }
	  
	  public String getconvKeyFromToken(String token) {
		  return (String) getClaims(token).get("convKey");
	  }
	  
	  
	  
	  
}
