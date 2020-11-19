package Employee_Payroll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollDBService {

	private Connection getConnection() throws SQLException {
		String jdbcURL = "jdbc:mysql://localhost:3306/payrollservice?useSSL=false";
		String username = "root";
		String password = "lovey";
		Connection con;
		System.out.println("Connecting to database:" + jdbcURL);
		con = DriverManager.getConnection(jdbcURL, username, password);
		System.out.println("Connection is successful:" + con);
		return con;
	}

	public List<EmployeePayrollData> readData() {
		String query = "SELECT * FROM employeepayroll; ";
		List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
		try (Connection con = this.getConnection();) {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				double salary = rs.getDouble("salary");
				LocalDate start = rs.getDate("start").toLocalDate();
				employeePayrollList.add(new EmployeePayrollData(id, name, salary, start));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeePayrollList;
	}
}
