package possg.com.a.dto;

public class DeliveryJoinDto {
	private int orderSeq;
	private String userId;
	private int ProductSeq;
	private int orderStatus;
	private int quantity;
	private String productName;
	private String ref;
	private String location;
	private int price;
	private String branchName;
	private int seq;
	private String delDate;
	private int delStatus;
	private int delTotalNumber;
	private int delTotalPrice;
	private String delRemark;
	
	public DeliveryJoinDto() {
	}

	public DeliveryJoinDto(int orderSeq, String userId, int productSeq, int orderStatus, int quantity,
			String productName, String ref, String location, int price, String branchName, int seq, String delDate,
			int delStatus, int delTotalNumber, int delTotalPrice, String delRemark) {
		super();
		this.orderSeq = orderSeq;
		this.userId = userId;
		ProductSeq = productSeq;
		this.orderStatus = orderStatus;
		this.quantity = quantity;
		this.productName = productName;
		this.ref = ref;
		this.location = location;
		this.price = price;
		this.branchName = branchName;
		this.seq = seq;
		this.delDate = delDate;
		this.delStatus = delStatus;
		this.delTotalNumber = delTotalNumber;
		this.delTotalPrice = delTotalPrice;
		this.delRemark = delRemark;
	}

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

	public int getProductSeq() {
		return ProductSeq;
	}

	public void setProductSeq(int productSeq) {
		ProductSeq = productSeq;
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

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getDelDate() {
		return delDate;
	}

	public void setDelDate(String delDate) {
		this.delDate = delDate;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getDelTotalNumber() {
		return delTotalNumber;
	}

	public void setDelTotalNumber(int delTotalNumber) {
		this.delTotalNumber = delTotalNumber;
	}

	public int getDelTotalPrice() {
		return delTotalPrice;
	}

	public void setDelTotalPrice(int delTotalPrice) {
		this.delTotalPrice = delTotalPrice;
	}

	public String getDelRemark() {
		return delRemark;
	}

	public void setDelRemark(String delRemark) {
		this.delRemark = delRemark;
	}

	@Override
	public String toString() {
		return "DeliveryJoinDto [orderSeq=" + orderSeq + ", userId=" + userId + ", ProductSeq=" + ProductSeq
				+ ", orderStatus=" + orderStatus + ", quantity=" + quantity + ", productName=" + productName + ", ref="
				+ ref + ", location=" + location + ", price=" + price + ", branchName=" + branchName + ", seq=" + seq
				+ ", delDate=" + delDate + ", delStatus=" + delStatus + ", delTotalNumber=" + delTotalNumber
				+ ", delTotalPrice=" + delTotalPrice + ", delRemark=" + delRemark + "]";
	}
	
	

}
