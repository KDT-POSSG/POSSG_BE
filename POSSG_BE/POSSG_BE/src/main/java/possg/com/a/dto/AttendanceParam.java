package possg.com.a.dto;

import java.util.Date;

// 근태 조회 결과 파라미터
public class AttendanceParam {
	private int attSeq;
    private int employeeSeq;
    private String branchName;
    private String empName;
    private String gender;
    private String birthDate;
    private int salary;
    private String phoneNumber;
    private String attendance;
    private String leaveWork;
    private String workHours;
    private String remark;
    private String matter;
    
    public AttendanceParam() {
    	
    }

	

	public AttendanceParam(int attSeq, int employeeSeq, String branchName, String empName, String gender,
			String birthDate, int salary, String phoneNumber, String attendance, String leaveWork, String workHours,
			String remark, String matter) {
		super();
		this.attSeq = attSeq;
		this.employeeSeq = employeeSeq;
		this.branchName = branchName;
		this.empName = empName;
		this.gender = gender;
		this.birthDate = birthDate;
		this.salary = salary;
		this.phoneNumber = phoneNumber;
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

	


	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	@Override
	public String toString() {
		return "AttendanceParam [attSeq=" + attSeq + ", employeeSeq=" + employeeSeq + ", branchName=" + branchName
				+ ", empName=" + empName + ", gender=" + gender + ", birthDate=" + birthDate + ", salary=" + salary
				+ ", phoneNumber=" + phoneNumber + ", attendance=" + attendance + ", leaveWork=" + leaveWork
				+ ", workHours=" + workHours + ", remark=" + remark + ", matter=" + matter + "]";
	}
}
