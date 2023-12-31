package possg.com.a.dto;


public class ConvenienceDto {
    private int convSeq;
    private String userId;
    private String pwd;
    private String representativeName;
    private String branchName;
    private String phoneNumber;
    private String registrationDate;
    private int convStatus;
    private String convKey;
    private String newPwd;
    private String convLocation;
    private double latitude;	//위도
    private double longtitude; //경도
    private String accountCode;

    // 생성자
    public ConvenienceDto() {}

	public ConvenienceDto(int convSeq, String userId, String pwd, String representativeName, String branchName,
			String phoneNumber, String registrationDate, int convStatus, String convKey, String newPwd, String convLocation, String accountCode) {
		super();
		this.convSeq = convSeq;
		this.userId = userId;
		this.pwd = pwd;
		this.representativeName = representativeName;
		this.branchName = branchName;
		this.phoneNumber = phoneNumber;
		this.registrationDate = registrationDate;
		this.convStatus = convStatus;
		this.convKey = convKey;
		this.newPwd = newPwd;
		this.convLocation = convLocation;
		this.accountCode = accountCode;
	}
	
	

	public ConvenienceDto(int convSeq, String userId, String pwd, String representativeName, String branchName,
			String phoneNumber, String registrationDate, int convStatus, String convKey, String newPwd,
			String convLocation, double latitude, double longtitude) {
		super();
		this.convSeq = convSeq;
		this.userId = userId;
		this.pwd = pwd;
		this.representativeName = representativeName;
		this.branchName = branchName;
		this.phoneNumber = phoneNumber;
		this.registrationDate = registrationDate;
		this.convStatus = convStatus;
		this.convKey = convKey;
		this.newPwd = newPwd;
		this.convLocation = convLocation;
		this.latitude = latitude;
		this.longtitude = longtitude;
	}

    public ConvenienceDto(int convSeq, String userId, String pwd, String representativeName, String branchName, String phoneNumber, String registrationDate, int convStatus) {
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

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public int getConvStatus() {
		return convStatus;
	}

	public void setConvStatus(int convStatus) {
		this.convStatus = convStatus;
	}
	
	public String getConvKey() {
		return convKey;
	}

	public void setConvKey(String convKey) {
		this.convKey = convKey;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	
	public String getConvLocation() {
		return convLocation;
	}

	public void setConvLocation(String convLocation) {
		this.convLocation = convLocation;
	}	

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	@Override
	public String toString() {
		return "ConvenienceDto [convSeq=" + convSeq + ", userId=" + userId + ", pwd=" + pwd + ", representativeName="
				+ representativeName + ", branchName=" + branchName + ", phoneNumber=" + phoneNumber
				+ ", registrationDate=" + registrationDate + ", convStatus=" + convStatus + ", convKey=" + convKey
				+ ", newPwd=" + newPwd + ", convLocation=" + convLocation + ", latitude=" + latitude + ", longtitude="
				+ longtitude + ", accountCode=" + accountCode + "]";
	}


}
