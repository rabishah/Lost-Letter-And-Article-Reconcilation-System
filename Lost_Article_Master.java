import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class Lost_Article_Master extends Lost_Item
{
	JTabbedPane tabbedPane;
        JLabel label[];
        JTextField textfield[];
        JPasswordField password;
        JPanel panel1;
        JPanel panel2;
        JButton button[];
        String Label[] = { "Sender's Name", "Sender's Address", "Sender's e-mail address", "Reciever's Name", "Reciever's Address", "Category" };
	String ReferenceNo;

	public Lost_Article_Master()
        {
              //  super( "");
                tabbedPane = new JTabbedPane();

                label = new JLabel[8];
                textfield = new JTextField[8];
                button = new JButton[2];

                this.add_lost_letter();

      //        tabbedPane.addTab( "Update Lost Letter Record ", null, panel2, "FirstPanel" );
                add( tabbedPane );

        }
      
        void add_lost_letter()
        {

		for ( int count1 = 0; count1 < 6; count1++ ) {

                	label[ count1 ] = new JLabel( Label[ count1 ], SwingConstants.CENTER );
                	textfield[ count1 ] = new JTextField();

		}

                button[0] = new JButton( "OK" );

                panel1 = new JPanel();
                panel1.setLayout( new GridLayout(7,2) );
                
		for ( int count = 0; count < 6; count++ )  {

                        panel1.add( label[ count ] );
                        panel1.add( textfield[ count ] );

                }

                panel1.add( button[0] );
                tabbedPane.addTab( "Add Lost Letter Record", null, panel1, "First Panel" );
		
		Unique_Code_Article code  = new Unique_Code_Article();
		ReferenceNo = code.generateCode();
		
		articleHandle handler = new articleHandle();
		button[0].addActionListener( handler );
					
        }

	private class articleHandle implements ActionListener
        {
                public void actionPerformed( ActionEvent event )
                {
                        Lost_Article_Connection userconnection = new Lost_Article_Connection();

                        if ( event.getSource() == button[0] ) {

                                try {
                                        userconnection.addRecord( ReferenceNo,textfield[0].getText(),textfield[1].getText(),textfield[2].getText(),
							textfield[3].getText(),textfield[4].getText(),textfield[5].getText() );
                                        JOptionPane.showMessageDialog( null, "Success \n Your Reference Number is " + ReferenceNo );
                                }
                                catch ( SQLException sqlException )
                                {
                                        JOptionPane.showMessageDialog( null, sqlException.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE );
                                }
                        }
                }
        }

	public static void main( String args[] )
        {

                Lost_Article_Master lost_article = new Lost_Article_Master();

                lost_article.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
                lost_article.setSize( 500, 300 );
                lost_article.setVisible( true );
        }

}
