package Employee_Payroll;

import java.time.LocalDate;

public class EmployeePayrollData {

	public int id;
	public String name;
	public double salary;
	public LocalDate startData;

	public EmployeePayrollData(int id, String name, double salary, LocalDate startData) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.startData = startData;
	}

	@Override
	public String toString() {
		return "EmployeePayrollData [id=" + id + ", name=" + name + ", salary=" + salary + ", start=" + startData + "]";
	}
}
