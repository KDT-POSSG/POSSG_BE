package possg.com.a.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;
@RestController
@RequestMapping("dummy")
public class DummyDataGenerator {
	
	static class Product {
        private int productSeq;
        private String productName;
        private int price;

        public Product(int productSeq, String productName, int price) {
            this.productSeq = productSeq;
            this.productName = productName;
            this.price = price;
        }

        public int getProductSeq() {
            return productSeq;
        }

        public String getProductName() {
            return productName;
        }
        
        public int getPrice() {
        	return price;
        }
    }
	
	@Value("${spring.datasource.hikari.username}")
    private String dbusername;
	
	@Value("${spring.datasource.hikari.password}")
    private String dbpassword;
	
	@Value("${spring.datasource.hikari.jdbc-url}")
    private String dburl;
	
	@PostMapping("dummy")
	public String generateDummyData() {
        Faker faker = new Faker(new Locale("ko", "KR"));

        String url = dburl;
        String user = dbusername;
        String password = dbpassword;

        List<Product> productList = new ArrayList<>();
        int records = 1000;
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT product_seq, product_name, price FROM Product where conv_seq = 1")) {
            
            while (rs.next()) {
                int productSeq = rs.getInt("product_seq");
                String productName = rs.getString("product_name");
                int price = rs.getInt("price");
                productList.add(new Product(productSeq, productName, price));
            }

            Random random = new Random();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            DateTimeFormatter refFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

            String insertSql = "INSERT INTO Delivery (order_seq, user_id, product_seq, order_status, quantity, "
                    + " product_name, order_date, ref, location, price, branch_name, not_discount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = connection.prepareStatement(insertSql)) {
                for (int i = 101; i <= records; i++) {
                    Product selectedProduct = productList.get(random.nextInt(productList.size()));

                    int orderSeq = i + 1; // 연속된 주문 번호
                    int userId = random.nextInt(9) + 2; // 2~11
                    int productSeq = selectedProduct.getProductSeq();
                    int orderStatus = 4;
                    int quantity = random.nextInt(5) + 1; // 1 ~ 5 수량
                    String productName = selectedProduct.getProductName();
                    LocalDateTime orderDate = LocalDateTime.now().minusDays(random.nextInt(600)); // 최대 1년 전 날짜
                    String ref = orderDate.format(refFormatter);
                    String location = faker.address().streetAddress(); // 무작위 주소
                    int notDiscount = selectedProduct.getPrice() * quantity;
                    int price = notDiscount - (int)((notDiscount * random.nextInt(16)) / 100.0); // 할인률 적용
                    String branchName = "센텀시티 이마트";
                    

                    pstmt.setInt(1, orderSeq);
                    pstmt.setInt(2, userId);
                    pstmt.setInt(3, productSeq);
                    pstmt.setInt(4, orderStatus);
                    pstmt.setInt(5, quantity);
                    pstmt.setString(6, productName);
                    pstmt.setString(7, orderDate.format(dateFormatter));
                    pstmt.setString(8, ref);
                    pstmt.setString(9, location);
                    pstmt.setInt(10, price);
                    pstmt.setString(11, branchName);
                    pstmt.setInt(12, notDiscount);

                    pstmt.addBatch();

                    if (i % 100 == 0 || i == records) { // 일정 간격으로 batch 실행
                        pstmt.executeBatch();
                    }
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "데이터 생성";
    }
	
	@PostMapping("dummyDeliveryList")
	public String dummyDeliveryList() {

        String url = dburl;
        String user = dbusername;
        String password = dbpassword;

        List<Delivery> productList = new ArrayList<>();
        int records = 1000;
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT user_id, not_discount, price, ref, order_date, quantity  FROM Delivery where branch_name = '센텀시티 이마트' ")) {
            
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                int notDiscount = rs.getInt("not_discount");
                int price = rs.getInt("price");
                String ref = rs.getString("ref");
                String delDate = rs.getString("order_date");
                int quantity = rs.getInt("quantity");

                productList.add(new Delivery(userId, notDiscount, price, ref, delDate, quantity));
            }
            
            String[] greetings = {
            	    "항상 감사합니다", 
            	    "즐거운 하루 보내세요", 
            	    "오늘도 행복하게!", 
            	    "힘내세요!", 
            	    "좋은 하루 되세요", 
            	    "배달 조심히 와주세요", 
            	    "행복한 시간 되세요", 
            	    "늘 웃음 가득한 날 되길 바래요", 
            	    "오늘 하루도 기대하며 시작해요", 
            	    "좋은 일만 가득하길 바라요", 
            	    "힘든 일 있으면 언제든 연락주세요", 
            	    "건강하게 지내세요", 
            	    "맛있게 드세요!", 
            	    "기분 좋은 일이 생길 것 같아요", 
            	    "잘 부탁드립니다!"
            	};

            	Random random = new Random();
            
            String insertSql = "INSERT INTO Delivery_list (seq, del_ref, del_date, del_status, del_total_number, del_total_price, del_remark, branch_name, "
            		+ " not_discount, user_id) "
            		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

            try (PreparedStatement pstmt = connection.prepareStatement(insertSql)) {
                for (int i = 901; i <= records; i++) {
                	Delivery selectedProduct = productList.get(i);

                    int seq = i + 1; // 연속된 주문 번호
                    String delRef = selectedProduct.getRef();
                    String delDate = selectedProduct.getDelDate();
                    int delStatus = 4;
                    int delTotalNumber = selectedProduct.getQuantity();
                    int delTotalPrice = selectedProduct.getPrice();                   
                    int notDiscount = selectedProduct.getNotDiscount();
                    String delRemark = greetings[random.nextInt(greetings.length)];
                    String branchName = "센텀시티 이마트";
                    int userId = selectedProduct.userId;
                    

                    pstmt.setInt(1, seq);
                    pstmt.setString(2, delRef);
                    pstmt.setString(3, delDate);
                    pstmt.setInt(4, delStatus);
                    pstmt.setInt(5, delTotalNumber);
                    pstmt.setInt(6, delTotalPrice);
                    pstmt.setString(7, delRemark);
                    pstmt.setString(8, branchName);
                    pstmt.setInt(9, notDiscount);
                    pstmt.setInt(10, userId);


                    pstmt.addBatch();

                    if (i % 100 == 0 || i == records) { // 일정 간격으로 batch 실행
                        pstmt.executeBatch();
                    }                  
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }              
        return "데이터 전송";
	}
	
	static class Delivery{
		private int userId;
		private int notDiscount;
		private int price;
		private String ref;
		private String delDate;
		private int quantity;
		
		public Delivery(int userId, int notDiscount, int price, String ref, String delDate, int quantity) {
			super();
			this.userId = userId;
			this.notDiscount = notDiscount;
			this.price = price;
			this.ref = ref;
			this.delDate = delDate;
			this.quantity = quantity;
		}
		public int getUserId() {
			return userId;
		}

		public int getNotDiscount() {
			return notDiscount;
		}

		public int getPrice() {
			return price;
		}

		public String getRef() {
			return ref;
		}

		public String getDelDate() {
			return delDate;
		}	
		
		public int getQuantity() {
			return quantity;
		}
		
	}
}
