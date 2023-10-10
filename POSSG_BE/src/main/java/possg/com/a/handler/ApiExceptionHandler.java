package possg.com.a.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class ApiExceptionHandler implements AccessDeniedHandler {
	
	@Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
		
		System.out.println("exception handler 발동!!!!!!!!!!!!!! 로그인으로 넘어가라~~~~~~");
        response.sendRedirect(request.getContextPath() + "/NoSecurityZoneController/login");
    }

}
