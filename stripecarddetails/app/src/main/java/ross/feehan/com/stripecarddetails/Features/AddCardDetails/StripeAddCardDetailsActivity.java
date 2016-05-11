package ross.feehan.com.stripecarddetails.Features.AddCardDetails;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ross.feehan.com.stripecarddetails.R;
import ross.feehan.com.stripecarddetails.Shared.StripeCardDetailsApplication;

public class StripeAddCardDetailsActivity extends AppCompatActivity implements AddCardDetailsViewInterface{

    @Inject AddCardDetailsLogicInterface logic;

    @Bind(R.id.rootView) ViewGroup rootView;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.cardTypeHeaderTV) TextView cardTypeHeaderTV;
    @Bind(R.id.cardHolderNameET) EditText cardHolderNameET;
    @Bind(R.id.cardNumberET) EditText cardNumberET;
    @Bind(R.id.cardExpMonthET) EditText cardExpMonthET;
    @Bind(R.id.cardExpYearET) EditText cardExpYearET;
    @Bind(R.id.cardCvvNumberET) EditText cardCvvNumberET;

    private boolean gotCardType;
    private static final int NO_TEXT_IN_EDIT_TEXT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDependencies();
        setupUI();
    }

    private void initDependencies() {
        ((StripeCardDetailsApplication) getApplication()).getObjectGraph().plus(new AddCardDetailsDIModule(this)).inject(this);
    }

    private void setupUI() {
        setContentView(R.layout.stripe_add_card_details_layout);
        ButterKnife.bind(this);
        setUpToolbar();
        cardNumberET.addTextChangedListener(addTextWatcher());
    }

    private void setUpToolbar() {
        setSupportActionBar(toolbar);
    }

    //set a text watcher on the card number edit text
    //when numbers are entered, pass to logic to find the type of card entered
    private TextWatcher addTextWatcher() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(start == NO_TEXT_IN_EDIT_TEXT){
                    gotCardType = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!gotCardType){
                    if(s.length() > 0){
                        logic.getCardType(Integer.parseInt(cardNumberET.getText().toString()));
                    }
                }
            }
        };
        return textWatcher;
    }

    @OnClick(R.id.saveBtn)
    void onSaveButtonLayoutClicked(){
        logic.onSaveButtonClicked();
    }

    //INTERFACE METHODS
    //AddCardDetailsViewInterface METHODS
    @Override
    public void displayCardType(String cardType) {
        cardTypeHeaderTV.setText(cardType);
        gotCardType = true;
    }

    @Override
    public void getCardDetails() {
        String cardHolderName = cardHolderNameET.getText().toString();
        String cardNumber = cardNumberET.getText().toString();
        String cardExpMonth = cardExpMonthET.getText().toString();
        String cardExpYear = cardExpYearET.getText().toString();
        String cardCVV = cardCvvNumberET.getText().toString();

        logic.receiveCardDetails(cardHolderName, cardNumber, cardExpMonth, cardExpYear, cardCVV);
    }

    @Override
    public void displayMessage(String message) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void displayMessageAndCloseScreen(String message) {
        Toast.makeText(getApplication(), message, Toast.LENGTH_LONG).show();
        finish();
    }
}