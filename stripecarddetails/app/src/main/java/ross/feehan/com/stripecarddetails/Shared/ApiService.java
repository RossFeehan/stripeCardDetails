package ross.feehan.com.stripecarddetails.Shared; /*
 * Created by Ross Feehan on 11/05/2016.
 */

import retrofit.http.POST;
import retrofit.http.Query;
import ross.feehan.com.stripecarddetails.DataTypes.SuccessOrErrorResponse;
import rx.Observable;

public interface ApiService {

    /**POST STRIPE TOKEN FOR CREDIT CARD */
    @POST("/fakeapi/wontwork/replacewithreal/addcarddetails")
    Observable<SuccessOrErrorResponse> addCardDetails(@Query("stripe_token") String stripeToken);
}
