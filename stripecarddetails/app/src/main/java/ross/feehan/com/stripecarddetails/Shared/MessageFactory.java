package ross.feehan.com.stripecarddetails.Shared; /*
 * Created by Ross Feehan on 11/05/2016.
 */

import android.content.Context;

import ross.feehan.com.stripecarddetails.R;

public class MessageFactory {

    private Context ctx;

    //CONSTRUCTOR
    public MessageFactory(Context ctx) {
        this.ctx = ctx;
    }

    public String invalidCardholderName(){return ctx.getString(R.string.invalidCardholderName);}

    public String invalidCardNumber(){return ctx.getString(R.string.invalidCardNumber);}

    public String invalidCardExpMonth(){return ctx.getString(R.string.invalidCardExpMonth);}

    public String invalidCardExpYear(){return ctx.getString(R.string.invalidCardExpYear);}

    public String invalidCardCvv(){return ctx.getString(R.string.invalidCardCvv);}

    public String invalidCardDetails(){return ctx.getString(R.string.invalidCardDetails);}

    public String successfullySavedCardDetails(){return ctx.getString(R.string.successfullySavedCardDetails);}

    public String cardAddedToStripe(){return ctx.getString(R.string.cardAddedToStripe);}

    public String cardNotAddedToStripe(){return ctx.getString(R.string.cardNotAddedToStripe);}
}
