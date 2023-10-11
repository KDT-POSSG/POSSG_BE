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
}
