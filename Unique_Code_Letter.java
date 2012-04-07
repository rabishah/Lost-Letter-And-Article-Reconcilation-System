import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Random;

public class Unique_Code_Letter extends Unique {


    public String generateCode()
    {
        String dateNow;

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
        dateNow = dateFormat.format( date );

        Random rand = new Random();

        int num;
        num = rand.nextInt(999999);

        Code = dateNow + "Letter" +  Integer.toString( num );

	return Code;
    }

}
