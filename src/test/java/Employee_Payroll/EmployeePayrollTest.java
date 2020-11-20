package Employee_Payroll;

import java.sql.SQLException;
import java.util.List;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import Employee_Payroll.EmployeePayrollService.IOService;

public class EmployeePayrollTest {

	private static EmployeePayrollService employeePayrollService;

	@BeforeClass
	public static void createcensusAnalyser() {
		employeePayrollService = new EmployeePayrollService();
		System.out.println("Welcome to the Employee Payroll Program.. ");
	}

	@Test
	public void givenEmployeePayroll_WhenRetrieved_ShouldMatchEmployeeCount()
			throws EmployeePayrollException, SQLException {
		List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData(IOService.DB_IO);
		Assert.assertEquals(4, employeePayrollData.size());
	}

	@Test
	public void givenEmployeePayroll_WhenUpdate_ShouldSyncWithDB() throws EmployeePayrollException, SQLException {
		List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData(IOService.DB_IO);
		employeePayrollService.updateRecord("James", 3000000);
		boolean result = employeePayrollService.checkUpdatedRecordSyncWithDatabase("James");
		Assert.assertTrue(result);
	}

	@Test
	public void givenEmployeePayroll_WhenRetrieved_ShouldMatchEmployeeCountInGivenRange()
			throws EmployeePayrollException, SQLException {
		List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData(IOService.DB_IO,
				"2020-06-14", "2020-11-10");
		Assert.assertEquals(2, employeePayrollData.size());
	}

	@Test
	public void givenEmployeePayrollData_ShouldReturnTotalOfMaleEmployeeSalary() throws EmployeePayrollException {
		Assert.assertEquals(6000000, employeePayrollService.readEmployeePayrollData("Sum", "M"));
	}

	@Test
	public void givenEmployeePayrollData_ShouldReturnTotalOfFemaleEmployeeSalary() throws EmployeePayrollException {
		Assert.assertEquals(10000000, employeePayrollService.readEmployeePayrollData("Sum", "F"));
	}

	@Test
	public void givenEmployeePayrollData_ShouldReturnAvgOfMaleEmployeeSalary() throws EmployeePayrollException {
		Assert.assertEquals(3000000, employeePayrollService.readEmployeePayrollAvgData("Avg", "M"));
	}

	@Test
	public void givenEmployeePayrollData_ShouldReturnAvgOfFemaleEmployeeSalary() throws EmployeePayrollException {
		Assert.assertEquals(5000000, employeePayrollService.readEmployeePayrollAvgData("Avg", "F"));
	}
}
