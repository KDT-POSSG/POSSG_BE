package possg.com.a.dto;

public class PointDto {
    private int ptSeq;
    private String ptName;
    private int ptPrice;
    private int ptDuration;
    private int ptStatus;

    // 생성자
    public PointDto() {}

    public PointDto(int ptSeq, String ptName, int ptPrice, int ptDuration, int ptStatus) {
        this.ptSeq = ptSeq;
        this.ptName = ptName;
        this.ptPrice = ptPrice;
        this.ptDuration = ptDuration;
        this.ptStatus = ptStatus;
    }

    // Getter, Setter
    public int getPtSeq() {
		return ptSeq;
	}

	public void setPtSeq(int ptSeq) {
		this.ptSeq = ptSeq;
	}

	public String getPtName() {
		return ptName;
	}

	public void setPtName(String ptName) {
		this.ptName = ptName;
	}

	public int getPtPrice() {
		return ptPrice;
	}

	public void setPtPrice(int ptPrice) {
		this.ptPrice = ptPrice;
	}

	public int getPtDuration() {
		return ptDuration;
	}

	public void setPtDuration(int ptDuration) {
		this.ptDuration = ptDuration;
	}

	public int getPtStatus() {
		return ptStatus;
	}

	public void setPtStatus(int ptStatus) {
		this.ptStatus = ptStatus;
	}

	// toString()
    @Override
    public String toString() {
        return "PTDto{" +
                "ptSeq=" + ptSeq +
                ", ptName='" + ptName + '\'' +
                ", ptPrice=" + ptPrice +
                ", ptDuration=" + ptDuration +
                ", ptStatus=" + ptStatus +
                '}';
    }
}
