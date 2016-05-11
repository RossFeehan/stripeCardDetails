package ross.feehan.com.stripecarddetails.DataTypes; /*
 * Created by Ross Feehan on 11/05/2016.
 */

import java.util.List;

public class SuccessOrErrorResponse {

    private List<String> success;
    private List<String> error;

    //SETTERS
    public void setSuccess(List<String> success) {
        this.success = success;
    }

    public void setError(List<String> error) {
        this.error = error;
    }

    //GETTERS
    public List<String> getSuccess() {
        return success;
    }

    public List<String> getError() {
        return error;
    }
}
