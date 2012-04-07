import javax.swing.JFrame;

public class Complaint_Handle_User
{
	public Complaint_Handle_User()
	{	
		Complaint_Handle complaint_handle = new Complaint_Handle();

                complaint_handle.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
                complaint_handle.setSize( 500, 400 );
                complaint_handle.setVisible( true );
	}

	public static void main( String[] args )
	{
		Complaint_Handle_User chu = new Complaint_Handle_User();
	}
}
