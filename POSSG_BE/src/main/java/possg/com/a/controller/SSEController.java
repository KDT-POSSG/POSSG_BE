package possg.com.a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

import possg.com.a.dto.ProductDto;
import possg.com.a.service.SSEService;
import reactor.core.publisher.Flux;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // 프론트엔드 CORS 허용
public class SSEController {

    @Autowired
    SSEService service; // ProductService는 ProductDto를 처리하는 서비스 클래스로 가정합니다.

    @GetMapping(value = "/notifications" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ProductDto> getNotifications() {
    	return service.getExpiredProductsStream();
    }
}

