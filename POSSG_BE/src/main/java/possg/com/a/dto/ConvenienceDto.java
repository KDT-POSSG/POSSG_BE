package possg.com.a.dto;

import java.sql.Timestamp;

public class ConvenienceDto {
    private int convSeq;
    private String userId;
    private String pwd;
    private String representativeName;
    private String branchName;
    private String phoneNumber;
    private Timestamp registrationDate;
    private int convStatus;

    // 생성자
    public ConvenienceDto() {}

    public ConvenienceDto(int convSeq, String userId, String pwd, String representativeName, String branchName, String phoneNumber, Timestamp registrationDate, int convStatus) {
        this.convSeq = convSeq;
        this.userId = userId;
        this.pwd = pwd;
        this.representativeName = representativeName;
        this.branchName = branchName;
        this.phoneNumber = phoneNumber;
        this.registrationDate = registrationDate;
        this.convStatus = convStatus;
    }
    
    // Getter, Setter

    public int getConvSeq() {
		return convSeq;
	}

	public void setConvSeq(int convSeq) {
		this.convSeq = convSeq;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRepresentativeName() {
		return representativeName;
	}

	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
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

	public int getConvStatus() {
		return convStatus;
	}

	public void setConvStatus(int convStatus) {
		this.convStatus = convStatus;
	}

	// toString()
    @Override
    public String toString() {
        return "ConvenienceDto{" +
                "convSeq=" + convSeq +
                ", userId='" + userId + '\'' +
                ", pwd='" + pwd + '\'' +
                ", representativeName='" + representativeName + '\'' +
                ", branchName='" + branchName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", registrationDate=" + registrationDate +
                ", convStatus=" + convStatus +
                '}';
    }
}
