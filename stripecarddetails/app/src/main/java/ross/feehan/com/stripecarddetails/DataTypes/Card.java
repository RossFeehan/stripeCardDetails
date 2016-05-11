package ross.feehan.com.stripecarddetails.DataTypes; /*
 * Created by Ross Feehan on 11/05/2016.
 */

import java.util.List;

public class Card {

    private String brand;
    private String digits;
    private String cardHolderName;
    private String expYear;
    private String expMonth;
    List<String> error;

    //SETTERS
    public void setDigits(String digits) {
        this.digits = digits;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }

    public void setError(List<String> error) {
        this.error = error;
    }

    //GETTERS
    public String getDigits() {
        return digits;
    }

    public String getBrand() {
        return brand;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public String getExpYear() {
        return expYear;
    }

    public List<String> getError() {
        return error;
    }
}