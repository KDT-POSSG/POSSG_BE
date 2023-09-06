package possg.com.a.dto;

<<<<<<< HEAD

import java.sql.Timestamp;

=======
>>>>>>> 5c7b5eca8cde3bbdf8f27e932958c0fee9f5c3de
public class DeliveryDto {
    private int orderSeq;
    private String userId;
    private String productSeq;
    private int orderStatus;
    private int quantity;
    private String productName;
    private String orderDate;
<<<<<<< HEAD
    private String ref;
    private String location;
    private int price;
   
=======
    private int ref;
    private String location;
>>>>>>> 5c7b5eca8cde3bbdf8f27e932958c0fee9f5c3de

    // 생성자
    public DeliveryDto() {}
    
<<<<<<< HEAD
    public DeliveryDto(int orderSeq, String userId, String productSeq, int orderStatus, int quantity, String productName, String orderDate, String ref, String location, int price) {
=======
    public DeliveryDto(int orderSeq, String userId, String productSeq, int orderStatus, int quantity, String productName, String orderDate, int ref, String location) {
>>>>>>> 5c7b5eca8cde3bbdf8f27e932958c0fee9f5c3de
        this.orderSeq = orderSeq;
        this.userId = userId;
        this.productSeq = productSeq;
        this.orderStatus = orderStatus;
        this.quantity = quantity;
        this.productName = productName;
        this.orderDate = orderDate;
        this.ref = ref;
        this.location = location;
<<<<<<< HEAD
        this.price = price;
=======
>>>>>>> 5c7b5eca8cde3bbdf8f27e932958c0fee9f5c3de
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

<<<<<<< HEAD
	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

=======
>>>>>>> 5c7b5eca8cde3bbdf8f27e932958c0fee9f5c3de
	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

<<<<<<< HEAD
=======
	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

>>>>>>> 5c7b5eca8cde3bbdf8f27e932958c0fee9f5c3de
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

<<<<<<< HEAD

    public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	// toString()
=======
	

    // toString()
>>>>>>> 5c7b5eca8cde3bbdf8f27e932958c0fee9f5c3de
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
<<<<<<< HEAD
                ", price='" + price + '\'' +
=======
>>>>>>> 5c7b5eca8cde3bbdf8f27e932958c0fee9f5c3de
                '}';
    }
}
