package possg.com.a.dto;

import java.sql.Timestamp;

public class CustomerDto {
    private int customerSeq;
    private String customerId;
    private int pinNumber;
    private String customerName;
    private String phoneNumber;
    private Timestamp registrationDate;
    private int customerStatus;

    // 생성자
    public CustomerDto() {}

    public CustomerDto(int customerSeq, String customerId, int pinNumber, String customerName, String phoneNumber, Timestamp registrationDate, int customerStatus) {
        this.customerSeq = customerSeq;
        this.customerId = customerId;
        this.pinNumber = pinNumber;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.registrationDate = registrationDate;
        this.customerStatus = customerStatus;
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

	public Timestamp getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Timestamp registrationDate) {
		this.registrationDate = registrationDate;
	}

	public int getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(int customerStatus) {
		this.customerStatus = customerStatus;
	}
	
    // toString()
    @Override
    public String toString() {
        return "CustomerDto{" +
                "customerSeq=" + customerSeq +
                ", customerId='" + customerId + '\'' +
                ", pinNumber=" + pinNumber +
                ", customerName='" + customerName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", registrationDate=" + registrationDate +
                ", customerStatus=" + customerStatus +
                '}';
    }
}
