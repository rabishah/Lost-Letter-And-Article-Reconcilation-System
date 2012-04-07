import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class UserConnection {

	Connection conn;
	Statement stmt;
	ResultSet rs;

	public void connectToDatabase() throws SQLException
	{
		conn = null;
		stmt = null;
		rs = null;

		conn = DriverManager.getConnection("jdbc:mysql://localhost/lalrs", "root", "rabi");
		stmt = conn.createStatement();
		stmt = conn.createStatement( ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE );
			
	}

	public void addUser( int userid, String username, String password) throws SQLException	
	{
		this.connectToDatabase();

		try {
	
			rs = stmt.executeQuery("Select UserId, Username, Password FROM login");
			rs.moveToInsertRow();
		
			rs.updateInt("UserId", userid );
			rs.updateString("Username", username  );
			rs.updateString("Password", password );

			rs.insertRow();
			rs.beforeFirst();

		}
		catch( SQLException sqlexception ) {
                                sqlexception.printStackTrace();
                }
		
		this.closeConnection();
	}
	
	public void closeConnection() throws SQLException
	{
		stmt.close();
		rs.close();
		conn.close();
	}
}

