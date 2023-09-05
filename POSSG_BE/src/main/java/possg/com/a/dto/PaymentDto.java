package possg.com.a.dto;

public class PaymentDto {
    private int paymentSeq;
    private int userSeq;
    private int productSeq;
    private String paymentMethod;
    private String discountInfo;
    private int price;
    private int count;
    private String paymentDate;
    private int ref;
    private String cardNum;

    // Getter, Setter
    // 생략...

    // 생성자
    public PaymentDto() {}

    public PaymentDto(int paymentSeq, int userSeq, int productSeq, String paymentMethod, String discountInfo, int price, int count, String paymentDate, int ref, String cardNum) {
        this.paymentSeq = paymentSeq;
        this.userSeq = userSeq;
        this.productSeq = productSeq;
        this.paymentMethod = paymentMethod;
        this.discountInfo = discountInfo;
        this.price = price;
        this.count = count;
        this.paymentDate = paymentDate;
        this.ref = ref;
        this.cardNum = cardNum;
    }

    //Getter, Setter
    
    public int getPaymentSeq() {
		return paymentSeq;
	}

	public void setPaymentSeq(int paymentSeq) {
		this.paymentSeq = paymentSeq;
	}

	public int getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}

	public int getProductSeq() {
		return productSeq;
	}

	public void setProductSeq(int productSeq) {
		this.productSeq = productSeq;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getDiscountInfo() {
		return discountInfo;
	}

	public void setDiscountInfo(String discountInfo) {
		this.discountInfo = discountInfo;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	// toString()
    @Override
    public String toString() {
        return "PaymentDto{" +
                "paymentSeq=" + paymentSeq +
                ", userSeq=" + userSeq +
                ", productSeq=" + productSeq +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", discountInfo='" + discountInfo + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", paymentDate=" + paymentDate +
                ", ref=" + ref +
                ", cardNum='" + cardNum + '\'' +
                '}';
    }
}
