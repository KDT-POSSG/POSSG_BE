package possg.com.a.dto;

public class CallProductConvParam {
    private int convSeq;
    private int pageNumber = 0;
    private int pageSize = 20;

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


	@Override
	public String toString() {
		return "CallProductConvParam [convSeq=" + convSeq + ", pageNumber=" + pageNumber + ", pageSize=" + pageSize
				+ "]";
	}
    
}

	