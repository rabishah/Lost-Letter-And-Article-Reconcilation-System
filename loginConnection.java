import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class loginConnection {
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	boolean result;

	boolean verifyLogin( String username, String password ) throws SQLException
	{
		result = false;
	
		conn = DriverManager.getConnection("jdbc:mysql://localhost/lalrs", "root", "rabi");
	
		stmt = conn.createStatement();


		rs = stmt.executeQuery("Select Username, Password FROM login");

			// Processing ResultSet Objects
		try {

			while ( rs.next() ) {
				String firstname = rs.getString("Username");
				String pass = rs.getString("Password");
				
				if ( ( firstname.equals(username) == true ) && ( pass.equals(password) ) ) {
					result = true;
					break;
				}  
			}

			stmt.close();
			rs.close();
			conn.close();
			
			return result;
		}
		catch( SQLException sqlexception ) {
				sqlexception.printStackTrace();
		}

		return false;
	}
}

