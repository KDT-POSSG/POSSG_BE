package possg.com.a.util;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import possg.com.a.dto.ConvenienceDto;
import possg.com.a.dto.CustomerDto;
import possg.com.a.service.ConvenienceService;

@Component
public class TokenCreate {

	@Value("${custom.security.key}")
    public String securityKey;
	
	  private final Long expiredTime = 1000 * 60L * 60L * 24L; // 유효기간 1일
	  
	  @Autowired
	  ConvenienceService service;	  	  
	  /**
	   * Member 정보를 담은 JWT 토큰을 생성한다.
	   *
	   * @param member Member 정보를 담은 객체
	   * @return String JWT 토큰
	   */
	  // accessToken
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
	  
	// Refresh Token 생성 로직
	  public String generateRefreshToken(ConvenienceDto dto) {
	      Date now = new Date();
	      Long expiredTime = 1000 * 60L * 60L * 24L * 14L; // 2주
	      
	      return Jwts.builder()
	          .setSubject(dto.getRepresentativeName())
	          .setHeader(createHeader())
	          .setClaims(createClaims(dto)) // 사용자 ID 등의 정보 포함
	          .setIssuedAt(now)
	          .setExpiration(new Date(now.getTime() + expiredTime)) // 적절한 유효기간 설정
	          .signWith(SignatureAlgorithm.HS256, securityKey)
	          .compact();
	  }
	  
	  // customer token accessToken
	  public String generateCustomerToken(CustomerDto dto) {
		  System.out.println("securityKey" + securityKey);
	    Date now = new Date();
	    
	    Long expiredTime = 1000 * 60L * 60L * 2L; // 2시간
	    
	    return Jwts.builder()
	        .setSubject(dto.getCustomerName()) // 보통 username
	        .setHeader(createHeader())
	        .setClaims(CustomerClaims(dto)) // 클레임, 토큰에 포함될 정보
	        .setExpiration(new Date(now.getTime() + expiredTime)) // 만료일
	        .signWith(SignatureAlgorithm.HS256, securityKey)
	        .compact();
	  }
	  
	  // customer refreshToken
	  public String generateCustomerRefreshToken(CustomerDto dto) {
	      Date now = new Date();
	      Long expiredTime = 1000 * 60L * 60L * 24L * 14L; // 2주
	      
	      return Jwts.builder()
	          .setSubject(dto.getCustomerName())
	          .setHeader(createHeader())
	          .setClaims(CustomerClaims(dto)) // 사용자 ID 등의 정보 포함
	          .setIssuedAt(now)
	          .setExpiration(new Date(now.getTime() + expiredTime)) // 적절한 유효기간 설정
	          .signWith(SignatureAlgorithm.HS256, securityKey)
	          .compact();
	  }
	  
	  private Map<String, Object> CustomerClaims(CustomerDto dto) {
		    Map<String, Object> claims = new HashMap<>();
		    // claim에 유저 정보 넣기
		    claims.put("customerId", dto.getCustomerId()); // username
		    claims.put("phoneNum", dto.getPhoneNumber());
		    claims.put("customerSeq", dto.getCustomerSeq());
		    claims.put("location", dto.getLocation());	    
		    /*claims.put("userdata", service.mypage(securityKey));*/
		    return claims;
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
		    
		    return claims;
		  }
	  
	  
	  public Claims getClaims(String token) {
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
	  
	  public String getconvSeqFromToken(String token) {
		  return (String) getClaims(token).get("convSeq");
	  }
	  
	 
	  public boolean isExpired(String token) {
//		    return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token)
//		            .getBody().getExpiration().before(new Date());	// 유효한 경우 false, 만료 true
			try {
				Jwts.parserBuilder().setSigningKey(securityKey).build().parseClaimsJws(token)
	            .getBody().getExpiration().before(new Date());
				
				return false;
			}
			catch (Exception e) {
				System.out.println("JwtUtil 만료된 토큰입니다.");
				return true;
			}
		}
		
		public boolean isRefreshTokenExpired(String refreshToken, LocalDateTime refreshTokenExpiry) {
	        if (refreshToken == null || refreshTokenExpiry == null) {
	            return false;
	        }
	        LocalDateTime currentTime = LocalDateTime.now();
	        return refreshTokenExpiry.isAfter(currentTime);	// 유효한 경우 true, 만료 false
	    }
		
		
}
