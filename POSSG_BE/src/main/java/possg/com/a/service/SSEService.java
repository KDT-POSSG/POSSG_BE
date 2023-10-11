package possg.com.a.service;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import possg.com.a.dao.SSEDao;
import possg.com.a.dto.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Service
@Transactional
public class SSEService {
	
	// ProductDto를 처리하기 위한 필요한 서비스나 레포지토리를 주입합니다.
	@Autowired
	SSEDao dao;
	
	public Flux<List<ProductDto>> getExpiredProductsStream() {
        return Flux.interval(Duration.ofSeconds(60)) // 테스트용으로 아직 60초로 설정
                .map(tick -> dao.checkExpiredProduct());
    }
	
	/*
	 * 쓰지는 않지만 하트비트로 keep-alive함을 보내는 코드
	 * 
	 public Flux<String> getExpiredProductsStream() {
        Flux<Long> heartbeatFlux = Flux.interval(Duration.ofSeconds(20))
                                       .map(tick -> -1L); // 특별한 값을 사용하여 heartbeat를 구분합니다.
        
        Flux<Long> productFlux = Flux.interval(Duration.ofMinutes(1))
                                     .map(tick -> tick);
        
        return Flux.merge(heartbeatFlux, productFlux)
                   .flatMap(tick -> {
                       if (tick == -1L) {
                           return Flux.just("heartbeat"); // 또는 빈 문자열로 보낼 수 있습니다.
                       } else {
                           List<ProductDto> products = dao.checkExpiredProduct();
                           // 실제 데이터를 문자열로 변환하는 로직을 추가합니다. 예: JSON 변환
                           return Flux.just(convertProductsToString(products));
                       }
                   });
    } 
	 */
}
