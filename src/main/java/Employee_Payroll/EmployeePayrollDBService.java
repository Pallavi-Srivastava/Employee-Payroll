package Employee_Payroll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeePayrollDBService {

	private PreparedStatement employeePayrollPreparedStatement;
	private static EmployeePayrollDBService employeePayrollDBService;

	private EmployeePayrollDBService() {
	}

	public static EmployeePayrollDBService getInstance() {
		if (employeePayrollDBService == null)
			employeePayrollDBService = new EmployeePayrollDBService();
		return employeePayrollDBService;
	}

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

	public List<EmployeePayrollData> readData(LocalDate start, LocalDate end) throws EmployeePayrollException {
		String query = null;
		if (start != null)
			query = String.format("select * from employeepayroll WHERE Start between '%s' and '%s';", start, end);
		if (start == null)
			query = "select * from employeepayroll";
		List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
		try (Connection con = this.getConnection();) {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(query);
			employeePayrollList = this.getEmployeePayrollData(rs);
		} catch (SQLException e) {
			throw new EmployeePayrollException(e.getMessage(),
					EmployeePayrollException.ExceptionType.DatabaseException);
		}
		return employeePayrollList;
	}

	public int updateEmployeeData(String name, double salary) throws EmployeePayrollException {
		return this.updateEmployeeDataUsingStatement(name, salary);
	}

	private int updateEmployeeDataUsingStatement(String name, double salary) throws EmployeePayrollException {
		String query = String.format("update employeepayroll set salary= %.2f where name='%s';", salary, name);
		try (Connection connection = this.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			return preparedStatement.executeUpdate(query);
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		return 0;
	}

	public List<EmployeePayrollData> getEmployeePayrollData(String name) throws EmployeePayrollException {
		List<EmployeePayrollData> employeePayrollData = null;
		if (this.employeePayrollPreparedStatement == null)
			this.prepareStatementForEmployeeData();
		try {
			employeePayrollPreparedStatement.setString(1, name);
			ResultSet resultSet = employeePayrollPreparedStatement.executeQuery();
			employeePayrollData = this.getEmployeePayrollData(resultSet);
		} catch (SQLException e) {
			throw new EmployeePayrollException(e.getMessage(),
					EmployeePayrollException.ExceptionType.DatabaseException);
		}
		return employeePayrollData;
	}

	private List<EmployeePayrollData> getEmployeePayrollData(ResultSet resultSet) throws EmployeePayrollException {
		List<EmployeePayrollData> employeePayrollData = new ArrayList();
		try {
			while (resultSet.next()) {
				int id = resultSet.getInt("ID");
				String name = resultSet.getString("Name");
				double salary = resultSet.getDouble("Salary");
				LocalDate startDate = resultSet.getDate("Start").toLocalDate();
				String gender = resultSet.getString("Gender");
				employeePayrollData.add(new EmployeePayrollData(id, name, salary, startDate, gender));
			}
		} catch (SQLException e) {
			throw new EmployeePayrollException(e.getMessage(),
					EmployeePayrollException.ExceptionType.DatabaseException);
		}
		return employeePayrollData;
	}

	private void prepareStatementForEmployeeData() throws EmployeePayrollException {
		try {
			Connection connection = this.getConnection();
			String sql = "select * from employeepayroll where name = ?";
			employeePayrollPreparedStatement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			throw new EmployeePayrollException(e.getMessage(),
					EmployeePayrollException.ExceptionType.DatabaseException);
		}
	}
}
