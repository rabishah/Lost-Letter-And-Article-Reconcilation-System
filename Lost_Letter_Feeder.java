import javax.swing.JFrame;

public class Lost_Letter_Feeder    
{
	public Lost_Letter_Feeder()
	{	
		Lost_Letter_Master lam  = new Lost_Letter_Master();

                lam.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
                lam.setSize( 500, 400 );
                lam.setVisible( true );
	}

	public static void main( String[] args )
	{
		Lost_Letter_Feeder aa = new Lost_Letter_Feeder();
	}
}
