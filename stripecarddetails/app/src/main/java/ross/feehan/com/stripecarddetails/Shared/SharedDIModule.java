package ross.feehan.com.stripecarddetails.Shared; /*
 * Created by Ross Feehan on 11/05/2016.
 */

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(injects = StripeCardDetailsApplication.class, library = true)
public class SharedDIModule {

    private Context ctx;

    public SharedDIModule(Context ctx){
        this.ctx = ctx;
    }

    @Provides @Singleton
    public ValidateChecks provideValidateChecks(){
        return new ValidateChecks();
    }

    @Provides @Singleton
    public MessageFactory provideMessageFactory(){
        return new MessageFactory(ctx);
    }

    @Provides @Singleton
    public ApiService provideApiService() {
        RetrofitSetup retrofitSetup = new RetrofitSetup();
        return retrofitSetup.getService();
    }
}