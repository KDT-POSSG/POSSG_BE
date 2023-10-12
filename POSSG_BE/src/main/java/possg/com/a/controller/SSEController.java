package possg.com.a.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import possg.com.a.dto.ProductDto;
import possg.com.a.service.SSEService;
import reactor.core.publisher.Flux;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // 프론트엔드 CORS 허용
public class SSEController {

    @Autowired
    SSEService service; // ProductService는 ProductDto를 처리하는 서비스 클래스로 가정합니다.

    @GetMapping(value = "/notifications" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<List<ProductDto>> getNotifications() {
    	return service.getExpiredProductsStream();
    }
    
    /* 
     * Emitter를 이용한 방법 --> 차후에 쓸만하면 써보자
     * 
    public SseEmitter getNotifications() {
        SseEmitter emitter = new SseEmitter(60_000L);  // 1분 동안 연결을 유지
        
        Flux<List<ProductDto>> flux = service.getExpiredProductsStream();

        flux.subscribe(
                products -> {
                    try {
                        emitter.send(products);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                },
                error -> emitter.completeWithError(error),  // 에러 발생 시
                emitter::complete  // 모든 데이터 전송 완료 시
        );

        // 클라이언트 연결 종료 시, SSEEmitter를 제대로 종료합니다.
        emitter.onTimeout(() -> emitter.complete());
        emitter.onError((e) -> emitter.complete());

        return emitter;
    }
    */
}

