package possg.com.a.dto;

public class CallProductCustomerDto {
    private int callSeq;
    private int customerSeq;
    private int productSeq;
    private int amount;
    private String cName;
    private String bName;
    private int price;
    private String callDate;
    private String productName;

    // 생성자
    public CallProductCustomerDto() {}

	public CallProductCustomerDto(int callSeq, int customerSeq, int productSeq, int amount, String cName, String bName,
			int price, String callDate, String productName) {
		super();
		this.callSeq = callSeq;
		this.customerSeq = customerSeq;
		this.productSeq = productSeq;
		this.amount = amount;
		this.cName = cName;
		this.bName = bName;
		this.price = price;
		this.callDate = callDate;
		this.productName = productName;
	}

	public int getCallSeq() {
		return callSeq;
	}

	public void setCallSeq(int callSeq) {
		this.callSeq = callSeq;
	}

	public int getCustomerSeq() {
		return customerSeq;
	}

	public void setCustomerSeq(int customerSeq) {
		this.customerSeq = customerSeq;
	}

	public int getProductSeq() {
		return productSeq;
	}

	public void setProductSeq(int productSeq) {
		this.productSeq = productSeq;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCallDate() {
		return callDate;
	}

	public void setCallDate(String callDate) {
		this.callDate = callDate;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "CallProductCustomerDto [callSeq=" + callSeq + ", customerSeq=" + customerSeq + ", productSeq="
				+ productSeq + ", amount=" + amount + ", cName=" + cName + ", bName=" + bName + ", price=" + price
				+ ", callDate=" + callDate + ", productName=" + productName + "]";
	}

    

}
