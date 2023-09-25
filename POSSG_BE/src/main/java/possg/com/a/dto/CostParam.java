package possg.com.a.dto;

public class CostParam {
	private String deliveryProductName;	// delivery productName  상품명
	private String paymentProductName;
	private int ref;			// delivery ref	팔린 시간
	private int deliveryPrice; // delivery 등 배달 가격
	private int paymentPrice;	//payment 매장가격
	private int totalLaborCost; // cost 총 고정 지출	
	private String branchName; //conv	지점명
	private double cost; // cost 도매가 (원가)
	private int convSeq;
	private int productSeq;
	private int choice;
	
	public CostParam() {
	}

	


	public CostParam(String deliveryProductName, String paymentProductName, int ref, int deliveryPrice,
			int paymentPrice, int totalLaborCost, String branchName, double cost, int choice, int convSeq, int productSeq) {
		super();
		this.deliveryProductName = deliveryProductName;
		this.paymentProductName = paymentProductName;
		this.ref = ref;
		this.deliveryPrice = deliveryPrice;
		this.paymentPrice = paymentPrice;
		this.totalLaborCost = totalLaborCost;
		this.branchName = branchName;
		this.cost = cost;
		this.choice = choice;
		this.convSeq = convSeq;
		this.productSeq = productSeq;
	}




	public String getDeliveryProductName() {
		return deliveryProductName;
	}

	public void setDeliveryProductName(String deliveryProductName) {
		this.deliveryProductName = deliveryProductName;
	}

	public String getPaymentProductName() {
		return paymentProductName;
	}

	public void setPaymentProductName(String paymentProductName) {
		this.paymentProductName = paymentProductName;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getDeliveryPrice() {
		return deliveryPrice;
	}

	public void setDeliveryPrice(int deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}

	public int getPaymentPrice() {
		return paymentPrice;
	}

	public void setPaymentPrice(int paymentPrice) {
		this.paymentPrice = paymentPrice;
	}

	public int getTotalLaborCost() {
		return totalLaborCost;
	}

	public void setTotalLaborCost(int totalLaborCost) {
		this.totalLaborCost = totalLaborCost;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getChoice() {
		return choice;
	}




	public void setChoice(int choice) {
		this.choice = choice;
	}


	public int getConvSeq() {
		return convSeq;
	}




	public void setConvSeq(int convSeq) {
		this.convSeq = convSeq;
	}


	public int getProductSeq() {
		return productSeq;
	}




	public void setProductSeq(int productSeq) {
		this.productSeq = productSeq;
	}


	@Override
	public String toString() {
		return "CostParam [deliveryProductName=" + deliveryProductName + ", paymentProductName=" + paymentProductName
				+ ", ref=" + ref + ", deliveryPrice=" + deliveryPrice + ", paymentPrice=" + paymentPrice
				+ ", totalLaborCost=" + totalLaborCost + ", branchName=" + branchName + ", cost=" + cost + ", convSeq="
				+ convSeq + ", productSeq=" + productSeq + ", choice=" + choice + "]";
	}



}


