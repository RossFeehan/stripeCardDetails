package ross.feehan.com.stripecarddetails.Features.AddCardDetails; /*
 * Created by Ross Feehan on 11/05/2016.
 */

import ross.feehan.com.stripecarddetails.DataTypes.SuccessOrErrorResponse;
import ross.feehan.com.stripecarddetails.Shared.DataInterface;

public interface AddCardDetailsDataInterface extends DataInterface {

    void addCardDetails(String stripeToken, Callback<SuccessOrErrorResponse> callback);
}

