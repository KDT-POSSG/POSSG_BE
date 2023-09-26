package possg.com.a;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import possg.com.a.util.TokenUtil;

@SpringBootApplication
@ServletComponentScan 
@MapperScan("possg.com.a.dao")
public class PossgBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PossgBeApplication.class, args);
		TokenUtil.getToken();
	}

}
