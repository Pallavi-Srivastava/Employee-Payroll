package Employee_Payroll;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

public class EmployeePayroll {

	public static void main(String[] args) {
		String jdbcURL = "jdbc:mysql://localhost:3306/payrollservice?useSSL=false";
		String username = "root";
		String password = "lovey";
		Connection connection;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded");
		} catch (ClassNotFoundException classNotFoundException) {
			throw new IllegalStateException("Cannot find the driver in the classpath: ", classNotFoundException);
		}
		listDrivers();
		try {
			System.out.println("Connecting to database:" + jdbcURL);
			connection = DriverManager.getConnection(jdbcURL, username, password);
			System.out.println("Connection is successful:" + connection);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private static void listDrivers() {
		Enumeration<Driver> driverList = DriverManager.getDrivers();
		while (driverList.hasMoreElements()) {
			Driver driverClass = driverList.nextElement();
			System.out.println(" " + driverClass.getClass().getName());
		}
	}
}
