package ross.feehan.com.stripecarddetails.Features.AddCardDetails; /*
 * Created by Ross Feehan on 11/05/2016.
 */

import android.support.annotation.NonNull;
import android.util.Log;

import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.exception.AuthenticationException;

import ross.feehan.com.stripecarddetails.DataTypes.SuccessOrErrorResponse;
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
        view.getCardDetails();
    }

    @Override
    public void receiveCardDetails(String cardholderName, String cardNumber, String expMonth, String expYear, String cvv) {

        boolean validCardDetails = true;

        //CHECK THAT ALL THE USER INPUT DATA IS CORRECT BEFORE SENDING CARD DETAILS TO STRIPE
        if(!validateChecks.validString(cvv)){
            validCardDetails = false;
            view.displayMessage(messageFactory.invalidCardCvv());
        }
        if(!validateChecks.validString(expYear)){
            validCardDetails = false;
            view.displayMessage(messageFactory.invalidCardExpYear());
        }
        if(!validateChecks.validString(expMonth)){
            validCardDetails = false;
            view.displayMessage(messageFactory.invalidCardExpMonth());
        }
        if(!validateChecks.validString(cardNumber)){
            validCardDetails = false;
            view.displayMessage(messageFactory.invalidCardNumber());
        }
        if(!validateChecks.validString(cardholderName)){
            validCardDetails = false;
            view.displayMessage(messageFactory.invalidCardholderName());
        }

        if(validCardDetails){
            addCardToStripe(cardholderName, cardNumber, expMonth, expYear, cvv);
        }
    }

    //CLASS METHODS
    //Send the card details to stripe and receive a valid stripe token back,
    //this stripe token is sent to the backend to be saved
    private void addCardToStripe(String cardholderName, String cardNumber, String expMonth, String expYear, String cvv) {
        boolean validStripeCard = true;
        Card card = new Card(cardNumber, Integer.parseInt(expMonth), Integer.parseInt(expYear), cvv, cardholderName, null, null, null, null, null, null, null, null, null, null);

        if (!card.validateCVC()) {
            validStripeCard = false;
            view.displayMessage(messageFactory.invalidCardCvv());
        }
        if (!card.validateCard()) {
            validStripeCard = false;
            view.displayMessage(messageFactory.invalidCardDetails());
        }

        if (validStripeCard) {
            try {
                Stripe stripe = new Stripe("pk_test_dTRy1FeKivlPmuYrzhNec11I");
                stripe.createToken(card, new TokenCallback() {
                    @Override
                    public void onError(Exception error) {
                        view.displayMessage(messageFactory.invalidCardDetails());
                        Log.i("STRIPE", "FAILURE");
                    }

                    @Override
                    public void onSuccess(Token token) {
                        view.displayToast(messageFactory.cardAddedToStripe());
                        Log.i("STRIPE", token.toString());

                        saveStripeTokenToBackend(token);
                    }
                });
            } catch (AuthenticationException e) {
                e.printStackTrace();
                view.displayMessage(messageFactory.cardNotAddedToStripe());
            }
        }
    }

    //Adds the stripe token to our backend so the users card can be used over and over
    public void saveStripeTokenToBackend(Token token) {
        data.addCardDetails(token.getId(), new AddCardDetailsDataInterface.Callback<SuccessOrErrorResponse>() {
            @Override
            public void onResponse(@NonNull SuccessOrErrorResponse response) {
                if(response.getSuccess() != null){
                    view.displayToast(response.getSuccess().get(0));
                }
                else{
                    view.displayMessage(response.getError().get(0));
                }
            }

            @Override
            public void onFailure() {
                view.displayMessage(messageFactory.somethingWentWrongError());
            }

            @Override
            public void onNoNetworkFailure() {
                view.displayMessage(messageFactory.noInternetConnection());
            }
        });
    }
}