package possg.com.a.handler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import possg.com.a.service.ConvenienceService;
import possg.com.a.util.JwtFilter;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

	private final Environment env;
	
	private final ConvenienceService service;
	
	public CustomLogoutSuccessHandler(Environment env, ConvenienceService service) {
		super();
		this.env = env;
		this.service = service;
	}

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		System.out.println("로그아웃이 여기서 진행됩니다.");
		String authorizationHeader = request.getHeader("accessToken");
		String key = env.getProperty("custom.security.key");
		String token = authorizationHeader.replace("Bearer", "").replace(" ", "");
		Claims claims =
				Jwts.parserBuilder()
				.setSigningKey(env.getProperty("custom.security.key")
		).build().parseClaimsJws(token.trim()).getBody();
		
		String userId = (String) claims.get("userId");
		
		// 토큰에 대한 삭제처리 로직은 없네요 Pass
		
		// refresh토큰은 db에 저장해놨다가 logout 요청이 오면 db에서 refresh만 삭제하고 front에서 accessToken 삭제합니다
		// 이건 여기서 할게요~!
		service.logout(userId);
		
		// 예외처리도 여기서 해야해요~!
		// 로그아웃이 이렇게 진행된 것처럼 사실 스프링 시큐리티를 이용하면 로그인도 이런 형태로 처리되는게 옳아요~! // 끝
	}

}
