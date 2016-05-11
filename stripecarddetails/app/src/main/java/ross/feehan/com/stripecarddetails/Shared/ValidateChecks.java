package ross.feehan.com.stripecarddetails.Shared; /*
 * Created by Ross Feehan on 11/05/2016.
 */

public class ValidateChecks {

    public boolean validString(String stringToTest) {
        if(stringToTest == null || stringToTest.isEmpty() || stringToTest.equals(" ")){
            return false;
        }
        else{
            return true;
        }
    }
}
