package ross.feehan.com.stripecarddetails; /*
 * Created by Ross Feehan on 11/05/2016.
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ross.feehan.com.stripecarddetails.Features.AddCardDetails.AddCardDetailsLogicImplTest;
import ross.feehan.com.stripecarddetails.Shared.ValidateChecksTest;

/**
 * Runs all unit tests in the project
 */
@RunWith(Suite.class)
@Suite.SuiteClasses ({
        AddCardDetailsLogicImplTest.class,
        ValidateChecksTest.class
})
public class AllUnitTests {
}
