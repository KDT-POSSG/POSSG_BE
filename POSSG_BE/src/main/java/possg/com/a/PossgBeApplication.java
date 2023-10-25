package possg.com.a;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import possg.com.a.service.ConvenienceService;
import possg.com.a.service.CustomerService;
import possg.com.a.util.TokenUtil;

@SpringBootApplication
@ServletComponentScan
@EnableScheduling
@MapperScan("possg.com.a")
public class PossgBeApplication {
	
	@Autowired
	 private ConvenienceService service;
	 
	 @Autowired
	 private CustomerService custService;

	public static void main(String[] args) {
		SpringApplication.run(PossgBeApplication.class, args);
		TokenUtil.getToken();
	}
	
	@Scheduled(cron = "0 0 12 * * *")
    public void dailyDatabaseCleanupTask() {
    	service.autoTokenClean();
    	custService.deleteCustomerToken();
    }
	
	@Scheduled(cron = "0 */5 * * * *")
	public void deleteOldData() {
	    service.autoSmsClean();
	}
	

}
