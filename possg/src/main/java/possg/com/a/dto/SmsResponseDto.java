package possg.com.a.dto;

import java.time.LocalDateTime;

public class SmsResponseDto {
	
	private String requestId;
	private LocalDateTime requestTime;
	private String statusCode;
	private String statusName;
	private int veri;
	
	public SmsResponseDto() {
	}
	
	public SmsResponseDto(String requestId, LocalDateTime requestTime, String statusCode, String statusName, int veri) {
		super();
		this.requestId = requestId;
		this.requestTime = requestTime;
		this.statusCode = statusCode;
		this.statusName = statusName;
		this.veri = veri;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public LocalDateTime getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(LocalDateTime requestTime) {
		this.requestTime = requestTime;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	


	public int getVeri() {
		return veri;
	}

	public void setVeri(int veri) {
		this.veri = veri;
	}

	@Override
	public String toString() {
		return "SmsResponseDto [requestId=" + requestId + ", requestTime=" + requestTime + ", statusCode=" + statusCode
				+ ", statusName=" + statusName + ", veri=" + veri + "]";
	}

}
