package possg.com.a.dto;

import java.sql.Timestamp;

public class DeliveryDto {
    private int orderSeq;
    private String userId;
    private String productSeq;
    private int orderStatus;
    private int quantity;
    private String productName;
    private Timestamp orderDate;
    private String ref;
    private String location;
    private int price;

    // 생성자
    public DeliveryDto() {}
    
    public DeliveryDto(int orderSeq, String userId, String productSeq, int orderStatus, int quantity, String productName, Timestamp orderDate, String ref, String location, int price) {
        this.orderSeq = orderSeq;
        this.userId = userId;
        this.productSeq = productSeq;
        this.orderStatus = orderStatus;
        this.quantity = quantity;
        this.productName = productName;
        this.orderDate = orderDate;
        this.ref = ref;
        this.location = location;
    }

    // getter, setter
    
    public int getOrderSeq() {
		return orderSeq;
	}

	public void setOrderSeq(int orderSeq) {
		this.orderSeq = orderSeq;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProductSeq() {
		return productSeq;
	}

	public void setProductSeq(String productSeq) {
		this.productSeq = productSeq;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

    public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	// toString()
    @Override
    public String toString() {
        return "DeliveryDTO{" +
                "orderSeq=" + orderSeq +
                ", userId='" + userId + '\'' +
                ", productSeq='" + productSeq + '\'' +
                ", orderStatus=" + orderStatus +
                ", quantity=" + quantity +
                ", productName='" + productName + '\'' +
                ", orderDate=" + orderDate +
                ", ref=" + ref +
                ", location='" + location + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
