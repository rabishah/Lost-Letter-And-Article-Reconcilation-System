import javax.swing.JFrame;

public class Lost_Article_Feeder    
{
	public Lost_Article_Feeder()
	{	
		Lost_Article_Master lam  = new Lost_Article_Master();

                lam.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
                lam.setSize( 500, 400 );
                lam.setVisible( true );
	}

	public static void main( String[] args )
	{
		Lost_Article_Feeder aa = new Lost_Article_Feeder();
	}
}
