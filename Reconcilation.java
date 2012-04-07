import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class Reconcilation extends JFrame
{

	static final String DRIVER = "com.mysql.jdbc.Driver";
	static final String DATABASE_URL = "jdbc:mysql://localhost/lalrs";     
	static final String USERNAME = "root";
	static final String PASSWORD = "rabi";

	static final String DEFAULT_QUERY = "SELECT * FROM LostLetterMaster";
	
	private Reconcilation_Connection tableModel;
	private Reconcilation_Connection tableModel1;
	private JList reconcileList;
	private final String Reference[] = { "SenderName", "SenderAddress", "RecieverName", "RecieverAddress", "Item" };
	String selected;
        String selected1;
	private GridBagLayout layout; 
        private GridBagConstraints constraints; 
    
	public Reconcilation()
	{
		super("Reconcilation");
		try 
		{
			layout = new GridBagLayout();
      			setLayout( layout ); 
      			constraints = new GridBagConstraints(); 

			tableModel = new Reconcilation_Connection( DRIVER, DATABASE_URL, USERNAME, PASSWORD, DEFAULT_QUERY );
			
			reconcileList = new JList( Reference );
			reconcileList.setVisibleRowCount(5);
			reconcileList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
				
			JButton submitButton = new JButton( "Submit Query" );
			final JTextField articleSelect = new JTextField();
			final JTextField itemSelect = new JTextField();			
			
			// Listener for List
			reconcileList.addListSelectionListener(
			
				new ListSelectionListener()
				{
					public void valueChanged( ListSelectionEvent event )
					{
						Object temp  = ( reconcileList.getSelectedValue() );
						selected = "SELECT * FROM ";
						selected1 = temp.toString();

				//		JOptionPane.showMessageDialog( null, selected );
					}
				}
			);
		
			JTable resultTable = new JTable( tableModel );
			 
			submitButton.addActionListener( 
				
				new ActionListener()
				{
					public void actionPerformed( ActionEvent event )
					{
						try
						{
							selected = selected + itemSelect.getText() + " where " + selected1 + " = '" + articleSelect.getText()+ "'";
							JOptionPane.showMessageDialog( null, selected );
							tableModel.setQuery( selected );
						}
						catch ( SQLException sqlException )
						{
							JOptionPane.showMessageDialog( null, sqlException.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE );
							try
							{
								tableModel.setQuery( DEFAULT_QUERY );
							}
							catch ( SQLException sqlException2 )
                                                	{
                                                        	JOptionPane.showMessageDialog( null, sqlException2.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE );
								tableModel.disconnectFromDatabase();
								System.exit( 1 );
							}
						}
					}
				}


			);
			
			JLabel referenceLabel = new JLabel( " Reference No " );
			JTextField referenceField = new JTextField();
			JButton submit = new JButton( " Submit " );
			
			tableModel1 = new Reconcilation_Connection( DRIVER, DATABASE_URL, USERNAME, PASSWORD, "Select * FROM ComplaintHandle" );
			JTable resultTable1 = new JTable( tableModel1 );

			constraints.fill = GridBagConstraints.BOTH;
 			addComponent( reconcileList, 0, 0, 1, 2 );    
			
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 10;  
      			constraints.weighty = 10;  
			addComponent( itemSelect, 0, 1, 1, 1 );

			
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 10;  
      			constraints.weighty = 10;  
			addComponent( articleSelect, 0, 2, 1, 1 );

					
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 10;  
      			constraints.weighty = 10;  			  
			addComponent( submitButton, 0, 3, 1, 1 );

			
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 30;  
      			constraints.weighty = 30;  			  
			addComponent( resultTable, 3, 0, 4, 2 );

			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 5;  
      			constraints.weighty = 5;  			  
			addComponent( referenceLabel, 6, 0, 1, 1 );
			
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 5;  
      			constraints.weighty = 5;  			  
			addComponent( referenceField, 6, 1, 1, 1 );
			
			
			constraints.fill = GridBagConstraints.HORIZONTAL;
	//		constraints.weightx = 5;  
      	//		constraints.weighty = 5;  			  
			addComponent( submit, 6, 3, 2, 1 );
			
			
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 30;  
      			constraints.weighty = 30;  			  
			addComponent( resultTable1 , 8, 0, 4, 3 );
			
			JLabel status = new JLabel("Status");
			JTextField statusField = new JTextField();
			JButton ok = new JButton(" OK ");
			
			constraints.fill = GridBagConstraints.HORIZONTAL;
//			constraints.weightx = 30;  
  //    			constraints.weighty = 30;  			  
			addComponent( status , 11, 1, 1, 1 );
			
			constraints.fill = GridBagConstraints.BOTH;
//		constraints.weightx = 30;  
  ///    			constraints.weighty = 30;  			  
			addComponent( statusField , 11, 2, 1, 1 );
			
			constraints.fill = GridBagConstraints.HORIZONTAL;
	//constraints.weightx = 30;  
      	//		constraints.weighty = 30;  			  
			addComponent( ok ,11, 3, 2, 1 );

			setSize( 800, 600 );
			setVisible( true );
		}
			
		catch ( ClassNotFoundException classNotFound )
		{
 						
				JOptionPane.showMessageDialog( null, "Database Driver Not Found", "Database error", JOptionPane.ERROR_MESSAGE );
				tableModel.disconnectFromDatabase();
			
				System.exit( 1 );
		}
			
		catch ( SQLException sqlException )
		{
                                JOptionPane.showMessageDialog( null, sqlException.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE );
				tableModel.disconnectFromDatabase();
				System.exit( 1 );
		}

		setDefaultCloseOperation( DISPOSE_ON_CLOSE );

		addWindowListener(
			new WindowAdapter()
			{
				public void windowClosed( WindowEvent event )
				{
					tableModel.disconnectFromDatabase();
					System.exit( 0 );
				}
			}
		);	
	}

	private void addComponent( Component component,int row, int column, int width, int height )
   	{
      		constraints.gridx = column; // set gridx
      		constraints.gridy = row; // set gridy
      		constraints.gridwidth = width; // set gridwidth
      		constraints.gridheight = height; // set gridheight
      		layout.setConstraints( component, constraints ); // set constraints
      		add( component ); // add component
	}

	public static void main( String[] args ) {

                new Reconcilation();

        }

}
