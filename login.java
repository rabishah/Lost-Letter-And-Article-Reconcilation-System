import java.awt.GridLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.sql.SQLException;

public class login extends JFrame
{
	private JLabel username;
	private JLabel password;
	private JTextField usertext;
	private JPasswordField passwordtext;
	private JButton ok;
	private GridLayout gridLayout;
	private Container container;

	public login()
	{
		super( " Login Window " );
		 
		gridLayout = new GridLayout( 3,2, 2, 2 );
		container = getContentPane();
		setLayout( gridLayout );

		username = new JLabel( "Username" );
		add( username );
	
		usertext = new JTextField();
		add( usertext );

		password = new JLabel( "Password" );
		add( password );
		
		passwordtext = new JPasswordField();
		add( passwordtext );

		ok = new JButton( "OK" );
		add( ok );

		loginHandler handler = new loginHandler();
		
		ok.addActionListener( handler );
	}

	private class loginHandler implements ActionListener
	{
		public void actionPerformed( ActionEvent event )
		{
			String userString = "";
			String passwordString = "";
			loginConnection loginconnection = new loginConnection();

			if ( event.getSource() == ok ) {
					
				userString = usertext.getText();
				passwordString = new String( passwordtext.getPassword() ) ;
			 
				try {
					if ( loginconnection.verifyLogin( userString, passwordString ) == true ) {
						JOptionPane.showMessageDialog( null, "Login Successful" );
					}
					else 
					{
						JOptionPane.showMessageDialog( null, "Login Failure" );
					}
				}
				catch ( SQLException sqlException ) 
				{
					JOptionPane.showMessageDialog( null, sqlException.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
				}
			}
		}
	}

}
					
			
	
