package possg.com.a.dto;

public class EmployeeDto {
	private int employeeSeq;
	private int convSeq;
    private String empName;
    private String birthDate;
    private String gender;
    private String phoneNumber;
    private String hireDate;
    private String terminationDate;
    private int salary;
    
    //생성자
    
    public EmployeeDto() {
    	
    }

	public EmployeeDto(int employeeSeq, int convSeq, String empName, String birthDate, String gender, String phoneNumber,
			String hireDate, String terminationDate, int salary) {
		super();
		this.employeeSeq = employeeSeq;
		this.convSeq = convSeq;
		this.empName = empName;
		this.birthDate = birthDate;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.hireDate = hireDate;
		this.terminationDate = terminationDate;
		this.salary = salary;
	}

	public int getEmployeeSeq() {
		return employeeSeq;
	}

	public void setEmployeeSeq(int employeeSeq) {
		this.employeeSeq = employeeSeq;
	}

	public int getConvSeq() {
		return convSeq;
	}

	public void setConvSeq(int convSeq) {
		this.convSeq = convSeq;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
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
		return "EmployeeDto [employeeSeq=" + employeeSeq + ", convSeq=" + convSeq + ", empName=" + empName
				+ ", birthDate=" + birthDate + ", gender=" + gender + ", phoneNumber=" + phoneNumber + ", hireDate="
				+ hireDate + ", terminationDate=" + terminationDate + ", salary=" + salary + "]";
	}

}
