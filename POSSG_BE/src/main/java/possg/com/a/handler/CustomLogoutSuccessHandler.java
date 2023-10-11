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
		System.out.println("로그아웃이 진행됩니다.");
		String authorizationHeader = request.getHeader("accessToken");
		String token = authorizationHeader.replace("Bearer", "").replace(" ", "");
	
		Claims claims =
				Jwts.parserBuilder()
				.setSigningKey(env.getProperty("custom.security.key")
		).build().parseClaimsJws(token.trim()).getBody();
		
		String userId = (String) claims.get("userId");
		
		service.logout(userId);
		
		request.getSession().invalidate();
		
		response.sendRedirect("/tokenController/afterLogout");
	}

}
