package possg.com.a.dto;


public class DeliveryDto {
    private int orderSeq;
    private int userId;
    private String productSeq;
    private int orderStatus;
    private int quantity;
    private String productName;
    private String orderDate;
    private String ref;
    private String location;
    private int price;
    private String branchName;
    private double discountRate;
    private int promotionInfo;
    private int convSeq;
   


    // 생성자
    public DeliveryDto() {}



	public DeliveryDto(int orderSeq, int userId, String productSeq, int orderStatus, int quantity, String productName,
			String orderDate, String ref, String location, int price, String branchName, double discountRate,
			int promotionInfo, int convSeq) {
		super();
		this.orderSeq = orderSeq;
		this.userId = userId;
		this.productSeq = productSeq;
		this.orderStatus = orderStatus;
		this.quantity = quantity;
		this.productName = productName;
		this.orderDate = orderDate;
		this.ref = ref;
		this.location = location;
		this.price = price;
		this.branchName = branchName;
		this.discountRate = discountRate;
		this.promotionInfo = promotionInfo;
		this.convSeq = convSeq;
	}



	public int getOrderSeq() {
		return orderSeq;
	}



	public void setOrderSeq(int orderSeq) {
		this.orderSeq = orderSeq;
	}



	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
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



	public String getOrderDate() {
		return orderDate;
	}



	public void setOrderDate(String orderDate) {
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



	public String getBranchName() {
		return branchName;
	}



	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}



	public double getDiscountRate() {
		return discountRate;
	}



	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}



	public int getPromotionInfo() {
		return promotionInfo;
	}



	public void setPromotionInfo(int promotionInfo) {
		this.promotionInfo = promotionInfo;
	}



	public int getConvSeq() {
		return convSeq;
	}



	public void setConvSeq(int convSeq) {
		this.convSeq = convSeq;
	}



	@Override
	public String toString() {
		return "DeliveryDto [orderSeq=" + orderSeq + ", userId=" + userId + ", productSeq=" + productSeq
				+ ", orderStatus=" + orderStatus + ", quantity=" + quantity + ", productName=" + productName
				+ ", orderDate=" + orderDate + ", ref=" + ref + ", location=" + location + ", price=" + price
				+ ", branchName=" + branchName + ", discountRate=" + discountRate + ", promotionInfo=" + promotionInfo
				+ ", convSeq=" + convSeq + "]";
	}
	
}
