package ross.feehan.com.stripecarddetails.Features.GetCardDetails; /*
 * Created by Ross Feehan on 11/05/2016.
 */

import ross.feehan.com.stripecarddetails.DataTypes.Card;

public interface GetCardDetailsViewInterface {

    /**
     * The user has a valid saved card, display the card details
     * @param card
     */
    void validCardDetails(Card card);

    /**
     * Called when the user does not have a valid card saved
     * @param message
     */
    void invalidCardDetails(String message);

    /**
     * Display a message to the user
     * @param message
     */
    void displayMessage(String message);
}
