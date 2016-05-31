import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SampleSQLite {

	public static void main(String[] args) throws ClassNotFoundException {
		// JDBCドライバーの指定
		Class.forName("org.sqlite.JDBC");

		Connection connection = null;
		try {
			// DBに接続
			connection = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite3\\sample.sqlite3");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);

			ResultSet results = statement.executeQuery("select * from Person");
			while(results.next()) {
				System.out.println("name = " + results.getString("name"));
				System.out.println("id = " + results.getInt("id"));
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

}
