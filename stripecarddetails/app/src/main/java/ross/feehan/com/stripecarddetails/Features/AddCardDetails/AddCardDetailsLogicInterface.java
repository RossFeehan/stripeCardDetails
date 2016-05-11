package ross.feehan.com.stripecarddetails.Features.AddCardDetails; /*
 * Created by Ross Feehan on 11/05/2016.
 */

public interface AddCardDetailsLogicInterface {

    public static final int FIRST_AMERICAN_EXPRESS_PREFIX = 34;
    public static final int SECOND_AMERICAN_EXPRESS_PREFIX = 37;
    public static final int VISA_PREFIX = 4;
    public static final int MASTERCARD_START_PREFIX = 51;
    public static final int MASTERCARD_END_PREFIX = 55;
    public static final int UNKNOWN_CARD_PREFIX_LENGTH = 4;

    public static final String AMERICAN_EXPRESS = "American Express";
    public static final String MASTERCARD = "Master Card";
    public static final String VISA = "Visa";
    public static final String UNKNOWN_CARD = "Unknown";

    /**
     * Find the card type for the entered card number
     * @param cardNumber
     */
    void getCardType(int cardNumber);

    /**
     * Called when the save button is clicked
     */
    void onSaveButtonClicked();

    /**
     * receive the users card details
     * @param cardholderName
     * @param cardNumber
     * @param expMonth
     * @param expYear
     * @param cvv
     */
    void receiveCardDetails(String cardholderName, String cardNumber, String expMonth, String expYear, String cvv);
}
