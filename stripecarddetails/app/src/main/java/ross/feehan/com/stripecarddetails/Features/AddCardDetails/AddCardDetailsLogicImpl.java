package ross.feehan.com.stripecarddetails.Features.AddCardDetails; /*
 * Created by Ross Feehan on 11/05/2016.
 */

import ross.feehan.com.stripecarddetails.Shared.MessageFactory;
import ross.feehan.com.stripecarddetails.Shared.ValidateChecks;

public class AddCardDetailsLogicImpl implements AddCardDetailsLogicInterface{

    private AddCardDetailsViewInterface view;
    private AddCardDetailsDataInterface data;
    private MessageFactory messageFactory;
    private ValidateChecks validateChecks;

    //CONSTRUCTOR
    public AddCardDetailsLogicImpl(AddCardDetailsViewInterface view, AddCardDetailsDataInterface data,
                                   MessageFactory messageFactory, ValidateChecks validateChecks){
        this.view = view;
        this.data = data;
        this.messageFactory = messageFactory;
        this.validateChecks = validateChecks;
    }

    //INTERFACE METHODS
    //AddCardDetailsLogicInterface METHODS
    @Override
    public void getCardType(int cardNumber) {
        if(cardNumber == FIRST_AMERICAN_EXPRESS_PREFIX || cardNumber == SECOND_AMERICAN_EXPRESS_PREFIX){
            view.displayCardType(AMERICAN_EXPRESS);
        }
        else if(cardNumber >= MASTERCARD_START_PREFIX && cardNumber <= MASTERCARD_END_PREFIX){
            view.displayCardType(MASTERCARD);
        }
        else if(cardNumber == VISA_PREFIX){
            view.displayCardType(VISA);
        }
        else if(String.valueOf(cardNumber).length() >= UNKNOWN_CARD_PREFIX_LENGTH){
            view.displayCardType(UNKNOWN_CARD);
        }
    }

    @Override
    public void onSaveButtonClicked() {

    }

    @Override
    public void receiveCardDetails(String cardholderName, String cardNumber, String expMonth, String expYear, String cvv) {

    }
}
