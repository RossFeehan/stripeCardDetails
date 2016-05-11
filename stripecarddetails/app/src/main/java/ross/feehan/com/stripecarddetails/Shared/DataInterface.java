package ross.feehan.com.stripecarddetails.Shared; /*
 * Created by Ross Feehan on 11/05/2016.
 */

import android.support.annotation.NonNull;

public interface DataInterface {

    public static final String NETWORKERROR = "NETWORK";

    interface Callback<T> {

        /** Called when response is received */
        void onResponse(@NonNull T response);

        /** Response has not been received from server */
        void onFailure();

        /** Response has not been received because of network error*/
        void onNoNetworkFailure();
    }
}
