package possg.com.a;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import possg.com.a.util.TokenUtil;

@SpringBootApplication
public class PossgBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PossgBeApplication.class, args);
		TokenUtil.getToken();
	}

}
