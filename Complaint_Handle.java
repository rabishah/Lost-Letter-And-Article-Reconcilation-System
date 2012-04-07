import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Complaint_Handle extends JFrame
{
	JTabbedPane tabbedPane;
        JLabel label[];
        JTextField textfield[];
        JPasswordField password;
        JPanel panel1;
        JPanel panel2;
        JButton button[];
	private String ReferenceNo;	

	public Complaint_Handle()
	{
		super( "Complaint Handle");
		tabbedPane = new JTabbedPane();

                label = new JLabel[8];
                textfield = new JTextField[8];
		button = new JButton[2];
               
		this.Complaint();
 
		tabbedPane.addTab( "Update UserInfo", null, panel2, "SecondPanel" );
                add( tabbedPane );

	}

	void Complaint()
	{
		String words[] = { "Sender's Name", "Sender's Address", "Sender's e-mail address", "Reciever's Name", "Reciever's Address", "Media Type", "Complaint Type", "Item" };

		for ( int count1 = 0; count1 < 8; count1++ )
		{
                	label[ count1 ] = new JLabel( words[ count1 ], SwingConstants.CENTER );
                	textfield[ count1 ] = new JTextField();
		}

                button[0] = new JButton( "Submit" );

                panel1 = new JPanel();
                panel1.setLayout( new GridLayout(9,2) );

                for ( int count = 0; count < 8; count++ )  {

			panel1.add( label[ count ] );
                	panel1.add( textfield[ count ] );
	
		}
	
		panel1.add( button[0] );
                tabbedPane.addTab( "Add User", null, panel1, "First Panel" );
	
		CodeforComplaint code  = new CodeforComplaint();
		ReferenceNo = code.generateCode();
		
		ComplaintListener complaintListener = new ComplaintListener();
		button[0].addActionListener( complaintListener );

	}

	private class ComplaintListener implements ActionListener
        {
                public void actionPerformed( ActionEvent event )
                {
                        Complaint_Handle_Connection userconnection = new Complaint_Handle_Connection();

                        if ( event.getSource() == button[0] ) {

                                try {
                                        userconnection.fileComplaint( ReferenceNo,textfield[0].getText(),textfield[1].getText(),textfield[2].getText(),
							textfield[3].getText(),textfield[4].getText(),textfield[5].getText(),textfield[6].getText(),
							textfield[7].getText() );
                                        JOptionPane.showMessageDialog( null, "Success \n Your Reference Number is " + ReferenceNo );
                                }
                                catch ( SQLException sqlException )
                                {
                                        JOptionPane.showMessageDialog( null, sqlException.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
                                }
                        }
                }
        }

//	public static void main( String args[] )
//        {
//		Complaint_Handle complaint_handle = new Complaint_Handle();

//                complaint_handle.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
//                complaint_handle.setSize( 500, 400 );
//                complaint_handle.setVisible( true );
//        }

}

