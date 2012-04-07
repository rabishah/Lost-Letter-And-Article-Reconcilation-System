import javax.swing.JOptionPane;

public class Addition
{
	public static void main( String args[] )
	{

		String firstNo = JOptionPane.showInputDialog( "Enter first number" );
		String secondNo = JOptionPane.showInputDialog( "Enter second number" );

		int number1 = Integer.parseInt( firstNo );
		int number2 = Integer.parseInt( secondNo );

		int sum = number1 + number2;
		
		JOptionPane.showMessageDialog( null, " The sum is " + sum, "Sum of two integers", JOptionPane.PLAIN_MESSAGE );

	}
}

