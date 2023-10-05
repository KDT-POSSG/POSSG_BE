package possg.com.a.dto;

public class CostParam {
	private String ref;			// delivery ref	팔린 시간
	private int price; // delivery 등 배달 가격
	private int paymentPrice;	//payment 매장가격
	private String branchName; //conv	지점명
	private int convSeq;  
	private int choice;  // day week month
	private String date;
	private int costYear;
	private int costMonth;
	private String productName;
	private int count;
	
	public CostParam() {
	}

	
	public CostParam(String ref, int price, int paymentPrice, String branchName, int convSeq, int choice, String date,
			int costYear, int costMonth, String productName, int count) {
		super();
		this.ref = ref;
		this.price = price;
		this.paymentPrice = paymentPrice;
		this.branchName = branchName;
		this.convSeq = convSeq;
		this.choice = choice;
		this.date = date;
		this.costYear = costYear;
		this.costMonth = costMonth;
		this.productName = productName;
		this.count = count;
	}

	public CostParam(int price, String productName, int count) {
		super();
		this.price = price;
		this.productName = productName;
		this.count = count;
	}


	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPaymentPrice() {
		return paymentPrice;
	}

	public void setPaymentPrice(int paymentPrice) {
		this.paymentPrice = paymentPrice;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public int getConvSeq() {
		return convSeq;
	}

	public void setConvSeq(int convSeq) {
		this.convSeq = convSeq;
	}

	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getCostYear() {
		return costYear;
	}

	public void setCostYear(int costYear) {
		this.costYear = costYear;
	}

	public int getCostMonth() {
		return costMonth;
	}

	public void setCostMonth(int costMonth) {
		this.costMonth = costMonth;
	}
	
	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	@Override
	public String toString() {
		return "CostParam [ref=" + ref + ", price=" + price + ", paymentPrice=" + paymentPrice + ", branchName="
				+ branchName + ", convSeq=" + convSeq + ", choice=" + choice + ", date=" + date + ", costYear="
				+ costYear + ", costMonth=" + costMonth + ", productName=" + productName + ", count=" + count + "]";
	}

}


