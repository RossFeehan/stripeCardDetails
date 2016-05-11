package ross.feehan.com.stripecarddetails.Shared;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

/*
* Created by Ross Feehan on 11/05/2016.
*
* Code Version Created in -
*
* Ross Feehan on 11/05/2016
* 
* Copyright Fiat Accompli
*/
public class ValidateChecksTest {

    ValidateChecks validate;

    //String test
    public static final String VALID_STRING = "123456";
    public static final String EMPTY_STRING = " ";
    public static final String INVALID_NULL_STRING = " ";

    @Before
    public void setup(){
        validate = new ValidateChecks();
    }

    //STRING TESTS
    @Test
    public void validString_isAccepted(){
        assertEquals(true, validate.validString(VALID_STRING));
    }

    @Test
    public void emptyString_isNotAccepted(){
        assertEquals(false, validate.validString(EMPTY_STRING));
    }

    @Test
    public void nullString_isNotAccepted(){
        assertEquals(false, validate.validString(INVALID_NULL_STRING));
    }

}