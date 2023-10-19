package possg.com.a.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLSyntaxErrorException;
import java.util.NoSuchElementException;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.MethodNotAllowedException;

import javax.naming.ServiceUnavailableException;

@RestControllerAdvice
public class ApiExceptionHandler implements AccessDeniedHandler {
   
   @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.getWriter().write("접근이 거부되었습니다.");
    }
   
   @ExceptionHandler(value = SecurityException.class)
   public ResponseEntity<?> SecurityException(SecurityException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("시큐리티 규정을 위반하였습니다.(SecurityException)");
   }
   
   @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<?> NullPointerException(NullPointerException  e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("예상치 못한 서버 에러 입니다. 관리자에게 문의 해 주세요(NullPointerException)");
    }
   
   @ExceptionHandler(value = SQLSyntaxErrorException.class)
    public ResponseEntity<?> SQLSyntaxErrorException(SQLSyntaxErrorException  e) {      
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("예상치 못한 서버 에러 입니다. 관리자에게 문의 해 주세요(SQLSyntaxErrorException)");
    }      
   
   @ExceptionHandler(value = HttpMessageNotReadableException.class)
   public ResponseEntity<?> HttpMessageNotReadableException(HttpMessageNotReadableException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("요청 형식이 올바르지 않습니다. 요청값을 확인 해 주세요(HttpMessageNotReadableException)");
   }
      
   @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
   public ResponseEntity<?> HttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("요청 형식이 올바르지 않습니다. 요청값을 확인 해 주세요(HttpMediaTypeNotSupportedException)");
   }
   
   @ExceptionHandler(value = IndexOutOfBoundsException.class)
   public ResponseEntity<?> IndexOutOfBoundsException(IndexOutOfBoundsException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("요청한 인덱스 값이 배열을 넘어갔습니다.(IndexOutOfBoundsException)");
   }
   
   @ExceptionHandler(value = IllegalArgumentException.class)
   public ResponseEntity<?> IllegalArgumentException(IllegalArgumentException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 인수가 메서드에 전달 되었습니다.(IllegalArgumentException)");
   }
   
   @ExceptionHandler(IllegalStateException.class)
   public ResponseEntity<String> IllegalStateException(IllegalStateException e) {
      e.printStackTrace();
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 값으로 된 요청입니다.(IllegalStateException)");
   }
   
   @ExceptionHandler(value = NotFoundException.class)
   public ResponseEntity<?> NotFoundException(NotFoundException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("요청 하신 리소스를 찾을 수 없습니다.(NotFoundException)");
   }
   
   @ExceptionHandler(value = NoSuchElementException.class)
   public ResponseEntity<?> NoSuchElementException(NoSuchElementException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("요청하신 엘리먼트가 없습니다.(NoSuchElementException)");
   }

   @ExceptionHandler(value = MethodNotAllowedException.class)
   public ResponseEntity<?> MethodNotAllowedException(MethodNotAllowedException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("http method가 리소스에 허용되지 않습니다.(MethodNotAllowedException)");
   }      
   
   @ExceptionHandler(ServiceUnavailableException.class)
   public ResponseEntity<String> ServiceUnavailableException(ServiceUnavailableException e) {
      e.printStackTrace();
       return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("서버가 과부화 상태이거나 유지보수 중입니다. 잠시후 요청 해 주십시오.(ServiceUnavailableException)");
   }   
   
}