package ross.feehan.com.stripecarddetails.Features.AddCardDetails;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ross.feehan.com.stripecarddetails.Shared.MessageFactory;
import ross.feehan.com.stripecarddetails.Shared.ValidateChecks;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/*
* Created by Ross Feehan on 11/05/2016.
*/

/**
 * Single Responsibility:
 *
 * Tests for {@link AddCardDetailsLogicImpl}
 */

@RunWith(MockitoJUnitRunner.class)
public class AddCardDetailsLogicImplTest {

    AddCardDetailsLogicImpl logic;
    @Mock AddCardDetailsViewInterface view;
    @Mock AddCardDetailsDataInterface data;
    @Mock MessageFactory messageFactory;
    @Mock ValidateChecks validateChecks;

    //CARD DETAILS
    private static final int MASTERCARD_IN_BETWEEN_PREFIX = 53;
    private static final int UNKNOWN_PREFIX = 1000;
    private static final String VALID_NAME = "FULL NAME";
    private static final String VALID_CARD_NUMBER = "4242424242424242";
    private static final String VALID_CARD_EXP_MONTH = "03";
    private static final String VALID_CARD_EXP_YEAR = "19";
    private static final String VALID_CARD_CVV_NUMBER = "111";

    //MESSAGES
    private static final String INVALID_NAME_ERROR = "INVALID_NAME_ERROR";
    private static final String INVALID_CARD_NUMBER_ERROR = "INVALID_CARD_NUMBER_ERROR";
    private static final String INVALID_CARD_EXP_MONTH_ERROR = "INVALID_CARD_EXP_MONTH_ERROR";
    private static final String INVALID_CARD_EXP_YEAR_ERROR = "INVALID_CARD_EXP_YEAR_ERROR";
    private static final String INVALID_CARD_CVV_NUMBER_ERROR = "INVALID_CARD_CVV_NUMBER_ERROR";

    @Before
    public void setup(){
        logic = new AddCardDetailsLogicImpl(view, data, messageFactory, validateChecks);
        mockResponse();
    }

    private void mockResponse() {
        //messages
        when(messageFactory.invalidCardholderName()).thenReturn(INVALID_NAME_ERROR);
        when(messageFactory.invalidCardNumber()).thenReturn(INVALID_CARD_NUMBER_ERROR);
        when(messageFactory.invalidCardExpMonth()).thenReturn(INVALID_CARD_EXP_MONTH_ERROR);
        when(messageFactory.invalidCardExpYear()).thenReturn(INVALID_CARD_EXP_YEAR_ERROR);
        when(messageFactory.invalidCardCvv()).thenReturn(INVALID_CARD_CVV_NUMBER_ERROR);
    }

    //SCENARIO -- CARD NUMBER WITH FIRST AMERICAN EXPRESS PREFIX IS ADDED, RETURN AMERICAN EXPRESS
    @Test
    public void when_cardNumberWithFirstAmericanExpressPrefixIsAdded_then_returnAmericanExpressToView(){
        //WHEN
        logic.getCardType(AddCardDetailsLogicInterface.FIRST_AMERICAN_EXPRESS_PREFIX);
        //THEN
        verify(view).displayCardType(AddCardDetailsLogicInterface.AMERICAN_EXPRESS);
    }

    //SCENARIO -- CARD NUMBER WITH SECOND AMERICAN EXPRESS PREFIX IS ADDED, RETURN AMERICAN EXPRESS
    @Test
    public void when_cardNumberWithSecondAmericanExpressPrefixIsAdded_then_returnAmericanExpressToView(){
        //WHEN
        logic.getCardType(AddCardDetailsLogicInterface.SECOND_AMERICAN_EXPRESS_PREFIX);
        //THEN
        verify(view).displayCardType(AddCardDetailsLogicInterface.AMERICAN_EXPRESS);
    }

    //SCENARIO -- CARD NUMBER WITH MASTER CARD START NUMBER PREFIX IS ADDED, RETURN MASTER CARD
    @Test
    public void when_cardNumberWithStartingNumberOfMasterCardPrefixIsAdded_then_returnMasterCardToView(){
        //WHEN
        logic.getCardType(AddCardDetailsLogicInterface.MASTERCARD_START_PREFIX);
        //THEN
        verify(view).displayCardType(AddCardDetailsLogicInterface.MASTERCARD);
    }

    //SCENARIO -- CARD NUMBER WITH MASTER CARD END NUMBER PREFIX IS ADDED, RETURN MASTER CARD
    @Test
    public void when_cardNumberWithEndingNumberOfMasterCardPrefixIsAdded_then_returnMasterCardToView(){
        //WHEN
        logic.getCardType(AddCardDetailsLogicInterface.MASTERCARD_END_PREFIX);
        //THEN
        verify(view).displayCardType(AddCardDetailsLogicInterface.MASTERCARD);
    }

    //SCENARIO -- CARD NUMBER IS IN BETWEEN OF MASTER CARD PREFIX IS ADDED, RETURN MASTER CARD
    @Test
    public void when_cardNumberIsInBetweenOfMasterCardPrefixIsAdded_then_returnMasterCardToView(){
        //WHEN
        logic.getCardType(MASTERCARD_IN_BETWEEN_PREFIX);
        //THEN
        verify(view).displayCardType(AddCardDetailsLogicInterface.MASTERCARD);
    }

    //SCENARIO -- CARD NUMBER WITH 4 PREFIX IS ADDED, RETURN VISA
    @Test
    public void when_cardNumberIsVisaPrefixIsAdded_then_returnVisaToView(){
        //WHEN
        logic.getCardType(AddCardDetailsLogicInterface.VISA_PREFIX);
        //THEN
        verify(view).displayCardType(AddCardDetailsLogicInterface.VISA);
    }

    //SCENARIO -- CARD NUMBER WITH PREFIX 100 IS ADDED, RETURN UNKNOWN
    @Test
    public void when_cardNumberPrefixIsUnknown_then_returnUnknownToView(){
        //WHEN
        logic.getCardType(UNKNOWN_PREFIX);
        //THEN
        verify(view).displayCardType(AddCardDetailsLogicInterface.UNKNOWN_CARD);
    }

    //SCENARIO -- WHEN SAVE BUTTON IS CLICKED, GET ENTERED CARD DETAILS FROM VIEW
    @Test
    public void when_saveButtonIsClicked_then_getEnteredCardDetailsFromView(){
        //WHEN
        logic.onSaveButtonClicked();
        //THEN
        verify(view).getCardDetails();
    }

    //SCENARIO -- WHEN LOGIC RECEIVES CARD DETAILS AND CARD HOLDER NAME IS NOT VALID, DISPLAY ERROR MESSAGE IN VIEW
    @Test
    public void when_invalidCardHolderName_then_displayErrorMessageInView(){
        //WHEN
        logic.receiveCardDetails(" ", VALID_CARD_NUMBER, VALID_CARD_EXP_MONTH, VALID_CARD_EXP_YEAR, VALID_CARD_CVV_NUMBER);
        //THEN
        verify(view).displayMessage(messageFactory.invalidCardholderName());
    }

    //SCENARIO -- WHEN LOGIC RECEIVES CARD DETAILS AND CARD NUMBER IS NOT VALID, DISPLAY ERROR MESSAGE IN VIEW
    @Test
    public void when_invalidCardNumber_then_displayErrorMessageInView(){
        //WHEN
        logic.receiveCardDetails(VALID_NAME, " ", VALID_CARD_EXP_MONTH, VALID_CARD_EXP_YEAR, VALID_CARD_CVV_NUMBER);
        //THEN
        verify(view).displayMessage(messageFactory.invalidCardNumber());
    }

    //SCENARIO -- WHEN LOGIC RECEIVES CARD DETAILS AND CARD EXP MONTH IS NULL, DISPLAY ERROR MESSAGE IN VIEW
    @Test
    public void when_invalidCardExpMonth_then_displayErrorMessageInView(){
        //WHEN
        logic.receiveCardDetails(VALID_NAME, VALID_CARD_NUMBER, " ", VALID_CARD_EXP_YEAR, VALID_CARD_CVV_NUMBER);
        //THEN
        verify(view).displayMessage(messageFactory.invalidCardExpMonth());
    }

    //SCENARIO -- WHEN LOGIC RECEIVES CARD DETAILS AND CARD EXP YEAR IS NULL, DISPLAY ERROR MESSAGE IN VIEW
    @Test
    public void when_invalidCardExpYear_then_displayErrorMessageInView(){
        //WHEN
        logic.receiveCardDetails(VALID_NAME, VALID_CARD_NUMBER, VALID_CARD_EXP_MONTH, " ", VALID_CARD_CVV_NUMBER);
        //THEN
        verify(view).displayMessage(messageFactory.invalidCardExpYear());
    }

    //SCENARIO -- WHEN LOGIC RECEIVES CARD DETAILS AND CARD CVV IS NULL, DISPLAY ERROR MESSAGE IN VIEW
    @Test
    public void when_invalidCardCvv_then_displayErrorMessageInView(){
        //WHEN
        logic.receiveCardDetails(VALID_NAME, VALID_CARD_NUMBER, VALID_CARD_EXP_MONTH, VALID_CARD_EXP_YEAR, " ");
        //THEN
        verify(view).displayMessage(messageFactory.invalidCardCvv());
    }

    //TESTING BACKEND CONNECTION
    //SCENARIO - SUCCESSFULLY ADD CARD TO OUR BACKEND

    //SCENARIO -- ERROR WHILE ADD CARD TO OUR BACKEND

    //SCENARIO -- EXCEPTION ERROR WHILE ADD CARD TO BACKEND

    //SCENARIO -- NETWORK ERROR WHILE ADD CARD TO BACKEND
}