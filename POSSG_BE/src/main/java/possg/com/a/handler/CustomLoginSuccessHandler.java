//package possg.com.a.handler;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import possg.com.a.dto.ConvenienceDto;
//import possg.com.a.dto.TokenDto;
//import possg.com.a.service.ConvenienceService;
//import possg.com.a.util.TokenCreate;
//
//@Component
//public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler  {
//
//	@Autowired
//	private ConvenienceService service;
//	
//	private TokenCreate tokenCreate;
//	
//	@Override // 반드시 들여쓰기 예쁘게 시켜주세요ㅠㅠ
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//            Authentication authentication) throws IOException, ServletException {
//		
//		System.out.println("로그인중,,,,,");
//	 	ConvenienceDto dto = new ConvenienceDto();
//	 	
//	 	String userId = ((String)authentication.getPrincipal());
//	 	String pwd = ((String)authentication.getCredentials());
//        
//	 	dto.setUserId(userId);
//	 	dto.setPwd(pwd);
//	 	
//        ConvenienceDto conv = (ConvenienceDto) authentication.getPrincipal();
//        
//        if (conv != null) {
//	        List<GrantedAuthority> roles = new ArrayList<>();
//	        roles.add(new SimpleGrantedAuthority("CONVENIENCE"));
//	
//	        // Access Token 생성
//	        String accessToken = tokenCreate.generateJwtToken(conv);
//	
//	        // Refresh Token 생성
//	        String refreshToken = tokenCreate.generateRefreshToken(conv);
//	        System.out.println(refreshToken);
//	
//	        TokenDto token = new TokenDto();
//	
//	        token.setUserId(conv.getUserId());
//	        token.setRefresh(refreshToken);
//	
//	        int refresh = service.insertToken(token);
//        
//	        if(refresh == 0) ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("NO");
//
//            ConvenienceDto requestDto = new ConvenienceDto();
//
//            requestDto.setBranchName(conv.getBranchName());
//            requestDto.setConvSeq(conv.getConvSeq());
//
//            response.addHeader("accessToken", accessToken);
//        }
//	}
//}
