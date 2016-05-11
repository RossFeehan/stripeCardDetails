package ross.feehan.com.stripecarddetails.Shared; /*
 * Created by Ross Feehan on 11/05/2016.
 */

import retrofit.RestAdapter;
import ross.feehan.com.stripecarddetails.BuildConfig;

public class RetrofitSetup {

    private static final String BASE_URL_CLIENT = "www.replacewithapiurl.com/api";

    public ApiService getService() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL_CLIENT)
                .setLogLevel(getLoggingLevel())
                .build();

        return restAdapter.create(ApiService.class);
    }

    private RestAdapter.LogLevel getLoggingLevel() {

        if (BuildConfig.DEBUG) {
            return RestAdapter.LogLevel.FULL;
        } else {
            return RestAdapter.LogLevel.NONE;
        }
    }
}
