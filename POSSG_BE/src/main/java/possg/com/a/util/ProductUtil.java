package possg.com.a.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import net.crizin.KoreanCharacter;
import net.crizin.KoreanRomanizer;
import possg.com.a.dto.ProductDto;
import possg.com.a.service.ProductService;

@RestController
public class ProductUtil {
	
	@Autowired
	ProductService service;
	
	public static List<ProductDto> productScrap(ProductService proService) throws InterruptedException {
		System.out.println("ProductController productScrap " + new Date());
		
		int conv_seq = 1;
		
        int cnt = 1;
        
        Random random = new Random();
        
        List<ProductDto> dtoList = new ArrayList<ProductDto>();
        
        while (true) {
        	
        	// 상품 상세정보 필요시 크롤링 페이지 다른 주소로 변경 (변경 시 파라미터 수정 필요)>> https://emile.emarteveryday.co.kr/
        	
            String url = "https://www.emart24.co.kr/goods/event?search=&page=" + cnt + "&category_seq=&align=";
        	//String url = "https://www.emart24.co.kr/goods/ff?search=&page=" + cnt + "&category_seq=&align=";
            try {
            	Document document = Jsoup.connect(url)
                        .timeout(10 * 1000)  // 10초 타임아웃
                        .userAgent("Mozilla/5.0")  // User-Agent 설정
                        .get();
                Elements items = document.select(".itemWrap"); // 해당 페이지 상품들
                System.out.println("count= " + cnt);
                if (items.isEmpty()) {
                    break;
                }
                for (Element item : items) {
                    int category_id = 1; //2; // 카테고리 (1: 행사상품, 2: 신선식품)
                    String product_name = item.select(".itemtitle a").text(); // 제품 이름
                    String product_roman_name = KoreanRomanizer.romanize(product_name, KoreanCharacter.ConsonantAssimilation.Regressive);
                    
                    String priceStr = item.select(".priceOff").text().replace(" 원", "").replace(",", ""); // 할인 전 가격
                    String priceDiscountStr = item.select(".price").text().replace(" 원", "").replace(",", ""); // 할인 후 가격
                    if (priceStr == null || priceStr.equals("")) {
                    	priceStr = priceDiscountStr;
                    }
                    int price = Integer.parseInt(priceStr);
                    int price_origin = (int)Math.ceil(price * 0.7);
                    int price_discount = Integer.parseInt(priceDiscountStr);
                    int stock_quantity = random.nextInt(20); // max 20
                    String expiration_date = DateCalc();	// max 2025년 3월 2일
                    double discount_rate = DiscountRateCalc(price, price_discount);
                    // 1: 행사X, 2: 세일, 3: 덤증정, 4: 1+1, 5: 2+1, 6: 1+2, 7: 2+2
                    int promotion_info =PromotionInfoCheck(item.select(".iTemTit .floatR").text()); // 1;  
                    String barcode = "0000000000000";
                    String img_url = item.select(".itemImg img").attr("src"); // 이미지 URL
                    
                    System.out.println("세일 여부: " + category_id);
                    System.out.println("제품 이름: " + product_name);
                    System.out.println("제품 로마자 이름:" + product_roman_name);
                    System.out.println("할인 전 가격: " + price);
                    System.out.println("상품 원가: " + price_origin);
                    System.out.println("할인 후 가격: " + price_discount);
                    System.out.println("상품 재고: " + stock_quantity);
                    System.out.println("유통 기한: " + expiration_date);
                    System.out.println("할인율: " + discount_rate);
                    System.out.println("할인 정보: " + promotion_info);
                    System.out.println("바코드: " + barcode);
                    System.out.println("이미지 URL: " + img_url);

                    System.out.println("----------------");      
                    
                    ProductDto dto = new ProductDto(0, conv_seq, category_id, product_name, product_roman_name, price, price_origin, price_discount,
							stock_quantity, expiration_date, discount_rate,
							promotion_info, barcode, img_url);
            		
                    // 동일한 제목이 있으면 해당 제목 return
                    boolean compare = findProductName(dto, proService);
                    /*
                    if(compare) {
            			System.out.println("\n product skip check: \n" );
            			break;
            		}
            		*/
            		dtoList.add(dto);
                }

                cnt++;
                // Thread.sleep(1000);  // 1초 대기

            } catch (IOException e) {
            	System.out.println("in error count= " + cnt);
                e.printStackTrace();
                try {
                    Thread.sleep(5000);  // 5초 대기 후 재시도
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
        return dtoList;
    }
	
	public static boolean findProductName(ProductDto dto, ProductService service) {
		System.out.println("ProductController findProductName " + new Date());
    	List<ProductDto> productNameCompare = service.findProductName(dto);
    	System.out.println("name= " + dto.getProductName());
    	if (productNameCompare != null && !productNameCompare.isEmpty()) {
    		System.out.println("productNameCompare= " + productNameCompare.get(0).getProductName());
    		String productName = productNameCompare.get(0).getProductName();
    	    if (productName == null) { // 동일한 상품X
            	System.out.println("ProductController product add " + new Date());
                //service.newswrite(newNews);
                return false;
    	    }
    	}     
        // 동일한 상품 존재
        System.out.println("ProductController no product to add " + new Date());
    	return true;
	}
	
	
	public static String DateCalc(){
    	
    	// 현재 날짜 가져오기
        Calendar calendar = Calendar.getInstance();
        
        /*
        // 원하는 날짜로 설정 (10월 27일)
        calendar.set(Calendar.MONTH, Calendar.OCTOBER); // 10월 (0부터 시작이므로 9)
        calendar.set(Calendar.DAY_OF_MONTH, 27); // 27일
        */
        // 랜덤 객체 생성
        Random random = new Random();
        
        // 0~365 사이의 랜덤한 숫자 생성
        int randomDays = random.nextInt(180);//random.nextInt(1, 7);
        
        // 랜덤한 날짜를 더하기
        calendar.add(Calendar.DAY_OF_YEAR, randomDays);
        //calendar.add(Calendar.YEAR, 1);
        // 날짜를 스트링으로 포맷하기
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(calendar.getTime()) + " 18:00";
        
        return formattedDate;
    }
    
    public static double DiscountRateCalc(int price, int priceDiscount) {
    	
        double discountRate = ((double) (price - priceDiscount) / price) * 100;
        discountRate = Math.round(discountRate);
        //System.out.println("할인률: " + discountRate + "%");
        
        return discountRate;
	}
    
    public static int PromotionInfoCheck(String info) {
    	//System.out.println("promotion check: " + info);
    	int InfoNum = 1;
    	
    	if (info.equals("세일")) {
    		InfoNum = 2;
    	}else if (info.equals("덤증정")) {
    		InfoNum = 3;
    	}else if (info.equals("1 + 1")) {
    		InfoNum = 4;
    	}else if (info.equals("2 + 1")) {
    		InfoNum = 5;
    	}else if (info.equals("1 + 2")) {
    		InfoNum = 6;
    	}else if (info.equals("2 + 2")) {
    		InfoNum = 7;
    	}

		return InfoNum;
	}
    
    public static String generateCallRef() {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	    String timestamp = sdf.format(new Date());
	    return timestamp;
	}
}