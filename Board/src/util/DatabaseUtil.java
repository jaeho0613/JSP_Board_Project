package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/exam",
					"root",
					"362514");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
