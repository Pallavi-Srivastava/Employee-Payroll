package Employee_Payroll;

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
	public void givenEmployeePayroll_WhenRetrieved_ShouldMatchEmployeeCount() {
		List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData(IOService.DB_IO);
		Assert.assertEquals(4, employeePayrollData.size());
	}
}
