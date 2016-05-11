package ross.feehan.com.stripecarddetails.Features.GetCardDetails; /*
 * Created by Ross Feehan on 11/05/2016.
 */

import ross.feehan.com.stripecarddetails.DataTypes.Card;
import ross.feehan.com.stripecarddetails.Shared.DataInterface;

public interface GetCardDetailsDataInterface extends DataInterface {

    /**
     * Call to back end to get card details for user if they have any
     * @param token
     * @param callback
     */
    void getCardDetails(String token, Callback<Card> callback);
}

