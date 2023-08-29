package possg.com.a.dto;

import java.sql.Timestamp;

public class AttendanceDto {
    private int attSeq;
    private int employeeSeq;
    private Timestamp attendance;
    private Timestamp leaveWork;
    private Timestamp workHours;
    private String remark;
    private String matter;

    // 생성자
    public AttendanceDto() {}

    public AttendanceDto(int attSeq, int employeeSeq, Timestamp attendance, Timestamp leaveWork, Timestamp workHours, String remark, String matter) {
        this.attSeq = attSeq;
        this.employeeSeq = employeeSeq;
        this.attendance = attendance;
        this.leaveWork = leaveWork;
        this.workHours = workHours;
        this.remark = remark;
        this.matter = matter;
    }

    // Getter, Setter
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

	public Timestamp getAttendance() {
		return attendance;
	}

	public void setAttendance(Timestamp attendance) {
		this.attendance = attendance;
	}

	public Timestamp getLeaveWork() {
		return leaveWork;
	}

	public void setLeaveWork(Timestamp leaveWork) {
		this.leaveWork = leaveWork;
	}

	public Timestamp getWorkHours() {
		return workHours;
	}

	public void setWorkHours(Timestamp workHours) {
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

	// toString()
    @Override
    public String toString() {
        return "AttendanceDto{" +
                "attSeq=" + attSeq +
                ", employeeSeq=" + employeeSeq +
                ", attendance=" + attendance +
                ", leaveWork=" + leaveWork +
                ", workHours=" + workHours +
                ", remark='" + remark + '\'' +
                ", matter='" + matter + '\'' +
                '}';
    }
}
