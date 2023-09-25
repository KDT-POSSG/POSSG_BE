package possg.com.a.dto;

public class CallProductConvOrderListDto {
    private int seq;
    private String callRef;
    private String callDate;
    private int callStatus;
    private int callTotalNumber;
    private int callTotalPrice;
    private String callRemark;

    public CallProductConvOrderListDto() {
    }

    public CallProductConvOrderListDto(int seq, String callRef, String callDate, int callStatus, int callTotalNumber, int callTotalPrice, String callRemark) {
        this.seq = seq;
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

	@Override
	public String toString() {
		return "CallProductConvOrderListDto [seq=" + seq + ", callRef=" + callRef + ", callDate=" + callDate
				+ ", callStatus=" + callStatus + ", callTotalNumber=" + callTotalNumber + ", callTotalPrice=" + callTotalPrice
				+ ", callRemark=" + callRemark + "]";
	}

    
}
