package possg.com.a.dto;

public class CustomerDto {
    private int customerSeq;
    private String customerId;
    private int pinNumber;
    private String customerName;
    private String phoneNumber;
    private String registrationDate;
    private int customerStatus;
    private int convSeq;

    // 생성자
    public CustomerDto() {}


   public CustomerDto(int customerSeq, String customerId, int pinNumber, String customerName, String phoneNumber, String registrationDate, int customerStatus) {
        this.customerSeq = customerSeq;
        this.customerId = customerId;
        this.pinNumber = pinNumber;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.registrationDate = registrationDate;
        this.customerStatus = customerStatus;
    }
  
	public CustomerDto(int customerSeq, String customerId, int pinNumber, String customerName, String phoneNumber,
			Timestamp registrationDate, int customerStatus, int convSeq) {
		super();
		this.customerSeq = customerSeq;
		this.customerId = customerId;
		this.pinNumber = pinNumber;
		this.customerName = customerName;
		this.phoneNumber = phoneNumber;
		this.registrationDate = registrationDate;
		this.customerStatus = customerStatus;
		this.convSeq = convSeq;
	}

   
    // Getter, Setter


	public int getCustomerSeq() {
		return customerSeq;
	}

	public void setCustomerSeq(int customerSeq) {
		this.customerSeq = customerSeq;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public int getPinNumber() {
		return pinNumber;
	}

	public void setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public int getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(int customerStatus) {
		this.customerStatus = customerStatus;
	}

	public int getConvSeq() {
		return convSeq;
	}

	public void setConvSeq(int convSeq) {
		this.convSeq = convSeq;
	}

	@Override
	public String toString() {
		return "CustomerDto [customerSeq=" + customerSeq + ", customerId=" + customerId + ", pinNumber=" + pinNumber
				+ ", customerName=" + customerName + ", phoneNumber=" + phoneNumber + ", registrationDate="
				+ registrationDate + ", customerStatus=" + customerStatus + ", convSeq=" + convSeq + "]";
	}

}
