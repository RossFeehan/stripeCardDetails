package ross.feehan.com.stripecarddetails.Features.AddCardDetails; /*
 * Created by Ross Feehan on 11/05/2016.
 */

public interface AddCardDetailsViewInterface {

    /**
     * Display the card type to the user
     * @param cardType
     */
    void displayCardType(String cardType);

    /**
     * Fetch the card details from the edit texts
     */
    void getCardDetails();

    /**
     * Display message to user
     * @param message
     */
    void displayMessage(String message);

    /**
     * Called when the card has successfully been saved
     * @param message
     */
    void displayToast(String message);
}
