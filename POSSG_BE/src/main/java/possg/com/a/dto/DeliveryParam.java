package possg.com.a.dto;

public class DeliveryParam {
	
	private int pageNumber;
	private String branchName;
	private int orderStatus;
	
	public DeliveryParam() {
	}

	public DeliveryParam(int pageNumber, String branchName, int orderStatus) {
		super();
		this.pageNumber = pageNumber;
		this.branchName = branchName;
		this.orderStatus = orderStatus;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "DeliveryParam [pageNumber=" + pageNumber + ", branchName=" + branchName + ", orderStatus="
				+ orderStatus + "]";
	}

}
