package ross.feehan.com.stripecarddetails.Features.AddCardDetails; /*
 * Created by Ross Feehan on 11/05/2016.
 */

import retrofit.RetrofitError;
import ross.feehan.com.stripecarddetails.DataTypes.SuccessOrErrorResponse;
import ross.feehan.com.stripecarddetails.Shared.ApiService;
import ross.feehan.com.stripecarddetails.Shared.DataInterface;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AddCardDetailsDataImpl implements AddCardDetailsDataInterface{

    private ApiService apiService;

    //CONSTRUCTOR
    public AddCardDetailsDataImpl(ApiService apiService){
        this.apiService = apiService;
    }

    //INTERFACE METHODS
    //AddCardDetailsDataInterface METHODS
    @Override
    public void addCardDetails(String stripeToken, final Callback<SuccessOrErrorResponse> callback) {
        //call the add card details rest full api
        //use reactive x to notify when the call is made
        apiService.addCardDetails(stripeToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SuccessOrErrorResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        RetrofitError rError = (RetrofitError) e;
                        if(rError.getKind().toString().equals(DataInterface.NETWORKERROR)) {
                            callback.onNoNetworkFailure();
                        }
                        else{
                            callback.onFailure();
                        }
                    }

                    @Override
                    public void onNext(SuccessOrErrorResponse response) {
                        callback.onResponse(response);
                    }
                });
    }
}
