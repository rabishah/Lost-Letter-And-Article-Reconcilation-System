import javax.swing.JFrame;

public class User extends JFrame {

	private String firstName;
	private String lastName;
	private String UserId;
	
	void login() {
		
		login Login = new login();

		Login.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
                Login.setSize( 350, 100 );
                Login.setVisible( true );

	}

	public static void main( String args[] ) {

		User user = new User();
		user.login();
	}
}

