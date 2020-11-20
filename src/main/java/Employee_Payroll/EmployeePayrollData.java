package Employee_Payroll;

import java.time.LocalDate;

public class EmployeePayrollData {

	public int id;
	public String name;
	public double salary;
	public LocalDate startData;
	public String gender;

	public EmployeePayrollData(int id, String name, double salary, LocalDate startData,String gender) {
		this(id, name, salary, gender);
		this.startData = startData;
	}

	public EmployeePayrollData(int id, String name, double salary, String gender) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.gender=gender;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeePayrollData other = (EmployeePayrollData) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(salary) != Double.doubleToLongBits(other.salary))
			return false;
		if (startData == null) {
			if (other.startData != null)
				return false;
		} else if (!startData.equals(other.startData))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmployeePayrollData [id=" + id + ", name=" + name + ", salary=" + salary + ", start=" + startData + "]";
	}
}
