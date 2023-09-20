package possg.com.a.util;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{

	private final TokenCreate tokenCreate;
	
	@Autowired
	public JwtFilter(TokenCreate tokenCreate) {
		this.tokenCreate = tokenCreate;
	}
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		boolean isError = false;	
		
		final String authorization = request.getHeader("accessToken");
		logger.info("Authorization" + authorization);
		 
		if(authorization == null || !authorization.startsWith("Bearer ")) {
			logger.info("authorization이 없거나 잘못보냈습니다.");
			filterChain.doFilter(request, response);
			return;
		}
		
		// accessToken 꺼내기
		String accessToken = authorization.split(" ")[1];

	    if(accessToken != null) { // Access 토큰이 있다면
		    if(!tokenCreate.isExpired(accessToken)) { // Access 토큰이 만료되지 않았다면
		    	
		    	String userId = tokenCreate.getuserIdFromToken(accessToken);		    	
		    	String role = tokenCreate.getRoleFromToken(accessToken);
		    	
		    	String customerId = tokenCreate.getCustomerIdFromToken(accessToken);
		    	
		    	
		    	if(role.equals("convenience")) {
		    		 UsernamePasswordAuthenticationToken authenticationToken =
		 	    			new UsernamePasswordAuthenticationToken(userId, null, List.of(new SimpleGrantedAuthority("ROLE_CONVENIENCE")));
		 	    	SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		 	    	System.out.println("CONVENIENCE"+authenticationToken);
		    	}else if(role.equals("customer")) {
		    		UsernamePasswordAuthenticationToken authenticationToken =
		 	    			new UsernamePasswordAuthenticationToken(customerId, null, List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER")));
		 	    	SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		 	    	System.out.println("CONVENIENCE"+authenticationToken);
		    	}
   	 		    	
		    	
		    } else {
		    	System.out.println("accessToken이 만료되었습니다");
		    	isError = true;		        
		        request.getRequestDispatcher("/tokenController/errorRedirect").forward(request, response);
		    }
		    
	    } else { // access 토큰이 없다면
	    	System.out.println("access토큰이 없습니다.");
			return;
	    }
	    
	    if(!isError)filterChain.doFilter(request, response); // 인증도장	    
		 // authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	}
	
}
