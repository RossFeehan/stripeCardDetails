package ross.feehan.com.stripecarddetails.Features.AddCardDetails; /*
 * Created by Ross Feehan on 11/05/2016.
 */

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
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
    public AddCardDetailsLogicInterface provideAddCardDetailsLogicInterface(MessageFactory messageFactory, ValidateChecks validateChecks){
        return new AddCardDetailsLogicImpl(view, messageFactory, validateChecks);
    }
}
