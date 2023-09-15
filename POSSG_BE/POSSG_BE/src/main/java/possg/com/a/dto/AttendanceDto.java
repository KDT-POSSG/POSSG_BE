package possg.com.a.dto;

public class AttendanceDto {
    private int attSeq;
    private int employeeSeq;
    private String attendance;
    private String leaveWork;
    private String workHours;
    private String remark;
    private String matter;

    // 생성자
    public AttendanceDto() {
    	
    }

	public AttendanceDto(int attSeq, int employeeSeq, String attendance, String leaveWork, String workHours,
			String remark, String matter) {
		super();
		this.attSeq = attSeq;
		this.employeeSeq = employeeSeq;
		this.attendance = attendance;
		this.leaveWork = leaveWork;
		this.workHours = workHours;
		this.remark = remark;
		this.matter = matter;
	}

	public int getAttSeq() {
		return attSeq;
	}

	public void setAttSeq(int attSeq) {
		this.attSeq = attSeq;
	}

	public int getEmployeeSeq() {
		return employeeSeq;
	}

	public void setEmployeeSeq(int employeeSeq) {
		this.employeeSeq = employeeSeq;
	}

	public String getAttendance() {
		return attendance;
	}

	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}

	public String getLeaveWork() {
		return leaveWork;
	}

	public void setLeaveWork(String leaveWork) {
		this.leaveWork = leaveWork;
	}

	public String getWorkHours() {
		return workHours;
	}

	public void setWorkHours(String workHours) {
		this.workHours = workHours;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMatter() {
		return matter;
	}

	public void setMatter(String matter) {
		this.matter = matter;
	}

	@Override
	public String toString() {
		return "AttendanceDto [attSeq=" + attSeq + ", employeeSeq=" + employeeSeq + ", attendance=" + attendance
				+ ", leaveWork=" + leaveWork + ", workHours=" + workHours + ", remark=" + remark + ", matter=" + matter
				+ "]";
	}

    
}
