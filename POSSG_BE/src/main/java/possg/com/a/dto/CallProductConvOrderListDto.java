package possg.com.a.dto;

public class CallProductConvOrderListDto {
    private int seq;
    private int convSeq;
    private String callRef;
    private String callDate;
    private int callStatus;
    private int callTotalNumber;
    private int callTotalPrice;
    private String callRemark;

    private int pageNumber = 0;
    private int pageSize = 20;

    public CallProductConvOrderListDto() {
    }

    public CallProductConvOrderListDto(int seq, int convSeq, String callRef, String callDate, int callStatus, int callTotalNumber, int callTotalPrice, String callRemark) {
        this.seq = seq;
        this.convSeq = convSeq;
        this.callRef = callRef;
        this.callDate = callDate;
        this.callStatus = callStatus;
        this.callTotalNumber = callTotalNumber;
        this.callTotalPrice = callTotalPrice;
        this.callRemark = callRemark;
    }

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getConvSeq() {
		return convSeq;
	}

	public void setConvSeq(int convSeq) {
		this.convSeq = convSeq;
	}

	public String getCallRef() {
		return callRef;
	}

	public void setCallRef(String callRef) {
		this.callRef = callRef;
	}

	public String getCallDate() {
		return callDate;
	}

	public void setCallDate(String callDate) {
		this.callDate = callDate;
	}

	public int getCallStatus() {
		return callStatus;
	}

	public void setCallStatus(int callStatus) {
		this.callStatus = callStatus;
	}

	public int getCallTotalNumber() {
		return callTotalNumber;
	}

	public void setCallTotalNumber(int callTotalNumber) {
		this.callTotalNumber = callTotalNumber;
	}

	public int getCallTotalPrice() {
		return callTotalPrice;
	}

	public void setCallTotalPrice(int callTotalPrice) {
		this.callTotalPrice = callTotalPrice;
	}

	public String getCallRemark() {
		return callRemark;
	}

	public void setCallRemark(String callRemark) {
		this.callRemark = callRemark;
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
		return "CallProductConvOrderListDto [seq=" + seq + ", convSeq=" + convSeq + ", callRef=" + callRef
				+ ", callDate=" + callDate + ", callStatus=" + callStatus + ", callTotalNumber=" + callTotalNumber
				+ ", callTotalPrice=" + callTotalPrice + ", callRemark=" + callRemark + ", pageNumber=" + pageNumber
				+ ", pageSize=" + pageSize + "]";
	}

    
}
