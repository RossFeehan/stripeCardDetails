package ross.feehan.com.stripecarddetails.Features.AddCardDetails;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ross.feehan.com.stripecarddetails.Shared.MessageFactory;
import ross.feehan.com.stripecarddetails.Shared.ValidateChecks;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

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

    private static final int MASTERCARD_IN_BETWEEN_PREFIX = 53;
    private static final int UNKNOWN_PREFIX = 1000;
    private static final String VALID_NAME = "FULL NAME";
    private static final String VALID_CARD_NUMBER = "4242424242424242";
    private static final String VALID_CARD_EXP_MONTH = "03";
    private static final String VALID_CARD_EXP_YEAR = "19";
    private static final String VALID_CARD_CVV_NUMBER = "111";

    @Before
    public void setup(){
        logic = new AddCardDetailsLogicImpl(view, data, messageFactory, validateChecks);
        mockResponse();
    }

    private void mockResponse() {

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

}