package possg.com.a.dto;


// 직원 조회용 직원 파라미터
public class EmployeeParam {
	private String employeeSeq;
	private String empName;
	private String branchName;
	private String birthDate;
	private String gender;
	private String phoneNumber;
	private String hireDate;
	private String terminationDate;
	private int salary;
	
	public EmployeeParam() {
		
	}
	
	public EmployeeParam(String employeeSeq, String empName, String branchName, String birthDate, String gender,
			String phoneNumber, String hireDate, String terminationDate, int salary) {
		super();
		this.employeeSeq = employeeSeq;
		this.empName = empName;
		this.branchName = branchName;
		this.birthDate = birthDate;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.hireDate = hireDate;
		this.terminationDate = terminationDate;
		this.salary = salary;
	}
	public String getEmployeeSeq() {
		return employeeSeq;
	}
	public void setEmployeeSeq(String employeeSeq) {
		this.employeeSeq = employeeSeq;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
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
	public String getHireDate() {
		return hireDate;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}
	public String getTerminationDate() {
		return terminationDate;
	}
	public void setTerminationDate(String terminationDate) {
		this.terminationDate = terminationDate;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "EmployeeParam [employeeSeq=" + employeeSeq + ", empName=" + empName + ", branchName=" + branchName
				+ ", gender=" + gender + ", phoneNumber=" + phoneNumber + ", hireDate=" + hireDate
				+ ", terminationDate=" + terminationDate + ", salary=" + salary + "]";
	}

	

}
