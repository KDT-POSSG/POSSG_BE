package possg.com.a.dto;

public class CallProductConvDto {
    private int callSeq;
    private int convSeq;
    private int productSeq;
    private int callStatus;

    // 생성자
    public CallProductConvDto() {}

    public CallProductConvDto(int callSeq, int convSeq, int productSeq, int callStatus) {
        this.callSeq = callSeq;
        this.convSeq = convSeq;
        this.productSeq = productSeq;
        this.callStatus = callStatus;
    }
    
    // Getter, Setter
	public int getCallSeq() {
		return callSeq;
	}

	public void setCallSeq(int callSeq) {
		this.callSeq = callSeq;
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

	public int getCallStatus() {
		return callStatus;
	}

	public void setCallStatus(int callStatus) {
		this.callStatus = callStatus;
	}
	
    // toString()
    @Override
    public String toString() {
        return "CallProductConvDto{" +
                "callSeq=" + callSeq +
                ", convSeq=" + convSeq +
                ", productSeq=" + productSeq +
                ", callStatus=" + callStatus +
                '}';
    }
}
