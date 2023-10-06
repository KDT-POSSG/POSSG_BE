package possg.com.a.dto;

public class CallProductConvParam {
    private int convSeq;
    private int pageNumber = 0;
    private int pageSize = 20;
    
    private int totalAmount;
    private int totalPrice;
    private int totalPriceOrigin;
    private int totalProduct;
    
	// 생성자
    public CallProductConvParam() {
    	
    }

	public CallProductConvParam(int convSeq, int pageNumber) {
		super();
		this.convSeq = convSeq;
		this.pageNumber = pageNumber;
	}
	
    public CallProductConvParam(int convSeq, int pageNumber, int pageSize) {
		super();
		this.convSeq = convSeq;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}
	
	public CallProductConvParam(int convSeq, int pageNumber, int pageSize, int totalAmount, int totalPrice,
			int totalPriceOrigin, int totalProduct) {
		super();
		this.convSeq = convSeq;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalAmount = totalAmount;
		this.totalPrice = totalPrice;
		this.totalPriceOrigin = totalPriceOrigin;
		this.totalProduct = totalProduct;
	}

	public int getConvSeq() {
		return convSeq;
	}

	public void setConvSeq(int convSeq) {
		this.convSeq = convSeq;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getTotalPriceOrigin() {
		return totalPriceOrigin;
	}

	public void setTotalPriceOrigin(int totalPriceOrigin) {
		this.totalPriceOrigin = totalPriceOrigin;
	}

	public int getTotalProduct() {
		return totalProduct;
	}

	public void setTotalProduct(int totalProduct) {
		this.totalProduct = totalProduct;
	}

	@Override
	public String toString() {
		return "CallProductConvParam [convSeq=" + convSeq + ", pageNumber=" + pageNumber + ", pageSize=" + pageSize
				+ ", totalAmount=" + totalAmount + ", totalPrice=" + totalPrice + ", totalPriceOrigin="
				+ totalPriceOrigin + ", totalProduct=" + totalProduct + "]";
	}
    
}

	