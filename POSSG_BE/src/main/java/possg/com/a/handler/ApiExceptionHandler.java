package possg.com.a.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLSyntaxErrorException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler implements AccessDeniedHandler {
	
	@Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.getWriter().write("접근이 거부되었습니다.");
    }
	
	@ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<?> NullPointerException(NullPointerException  e) {		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("예상치 못한 서버 에러 입니다. 관리자에게 문의 해 주세요(NullPointerException)");
    }
	
	@ExceptionHandler(value = SQLSyntaxErrorException.class)
    public ResponseEntity<?> SQLSyntaxErrorException(SQLSyntaxErrorException  e) {		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("예상치 못한 서버 에러 입니다. 관리자에게 문의 해 주세요(SQLSyntaxErrorException)");
    }
	
	

}
