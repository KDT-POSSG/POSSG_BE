package possg.com.a.dto;

public class CallProductCustomerDto {
    private int callSeq;
    private int customerSeq;
    private int productSeq;
    private int callStatus;

    // 생성자
    public CallProductCustomerDto() {}

    public CallProductCustomerDto(int callSeq, int customerSeq, int productSeq, int callStatus) {
        this.callSeq = callSeq;
        this.customerSeq = customerSeq;
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

	public int getCallStatus() {
		return callStatus;
	}

	public void setCallStatus(int callStatus) {
		this.callStatus = callStatus;
	}

	// toString()
    @Override
    public String toString() {
        return "CallProductCustomerDto{" +
                "callSeq=" + callSeq +
                ", customerSeq=" + customerSeq +
                ", productSeq=" + productSeq +
                ", callStatus=" + callStatus +
                '}';
    }
}
