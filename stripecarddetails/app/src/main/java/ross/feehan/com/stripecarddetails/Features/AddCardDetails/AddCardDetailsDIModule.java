package ross.feehan.com.stripecarddetails.Features.AddCardDetails; /*
 * Created by Ross Feehan on 11/05/2016.
 */

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ross.feehan.com.stripecarddetails.Shared.ApiService;
import ross.feehan.com.stripecarddetails.Shared.MessageFactory;
import ross.feehan.com.stripecarddetails.Shared.ValidateChecks;

@Module(injects = StripeAddCardDetailsActivity.class, complete = false)
public class AddCardDetailsDIModule {

    private AddCardDetailsViewInterface view;

    //CONSTRUCTOR
    public AddCardDetailsDIModule(AddCardDetailsViewInterface view){
        this.view = view;
    }

    @Provides @Singleton
    public AddCardDetailsDataInterface provideAddCardDetailsDataInterface(ApiService apiService){
        return new AddCardDetailsDataImpl(apiService);
    }

    @Provides @Singleton
    public AddCardDetailsLogicInterface provideAddCardDetailsLogicInterface(AddCardDetailsDataInterface data, MessageFactory messageFactory, ValidateChecks validateChecks){
        return new AddCardDetailsLogicImpl(view, data, messageFactory, validateChecks);
    }
}
