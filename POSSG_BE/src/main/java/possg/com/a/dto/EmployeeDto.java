package possg.com.a.dto;

import java.sql.Timestamp;

public class EmployeeDto {
	private int employeeSeq;
    private String empName;
    private Timestamp birthDate;
    private String gender;
    private String phoneNumber;
    private Timestamp hireDate;
    private Timestamp terminationDate;
    private int salary;
    
    //생성자
    
    public EmployeeDto() {
    	
    }
    
	public EmployeeDto(int employeeSeq, String empName, Timestamp birthDate, String gender, String phoneNumber,
			Timestamp hireDate, Timestamp terminationDate, int salary) {
		super();
		this.employeeSeq = employeeSeq;
		this.empName = empName;
		this.birthDate = birthDate;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.hireDate = hireDate;
		this.terminationDate = terminationDate;
		this.salary = salary;
	}

	//Getter, Setter
	
	public int getEmployeeSeq() {
		return employeeSeq;
	}

	public void setEmployeeSeq(int employeeSeq) {
		this.employeeSeq = employeeSeq;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Timestamp getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Timestamp birthDate) {
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

	public Timestamp getHireDate() {
		return hireDate;
	}

	public void setHireDate(Timestamp hireDate) {
		this.hireDate = hireDate;
	}

	public Timestamp getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(Timestamp terminationDate) {
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
		return "EmployeeDto [employeeSeq=" + employeeSeq + ", empName=" + empName + ", birthDate=" + birthDate
				+ ", gender=" + gender + ", phoneNumber=" + phoneNumber + ", hireDate=" + hireDate
				+ ", terminationDate=" + terminationDate + ", salary=" + salary + "]";
	}
    
	
    
}
