package possg.com.a.dto;

public class DeliveryParam {
	
	private int pageNumber;
	
	public DeliveryParam() {
	}

	public DeliveryParam(int pageNumber) {
		super();
		this.pageNumber = pageNumber;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	@Override
	public String toString() {
		return "DeliveryParam [pageNumber=" + pageNumber + "]";
	}
	
	

}
