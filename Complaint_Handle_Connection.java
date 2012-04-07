import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Complaint_Handle_Connection {

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

	public void fileComplaint( String ReferenceNo,String SenderName, String SenderAddress, String SenderEmail, String RecieverName, 
String RecieverAddress, String MediaType, String ComplaintType, String Item )  throws SQLException	
	{
		this.connectToDatabase();

		try {
	
			rs = stmt.executeQuery("Select * FROM ComplaintHandle");
			rs.moveToInsertRow();
		
			rs.updateString("ReferenceNo", ReferenceNo );
			rs.updateString("SenderName", SenderName  );
			rs.updateString("SenderAddress", SenderAddress );			
			rs.updateString("SenderEmail", SenderEmail );
			rs.updateString("RecieverName", RecieverName  );
			rs.updateString("RecieverAddress", RecieverAddress );
			rs.updateString("MediaType", MediaType );
			rs.updateString("ComplaintType", ComplaintType );
			rs.updateString("Item", Item );

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

