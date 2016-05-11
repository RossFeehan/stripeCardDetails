package ross.feehan.com.stripecarddetails.Features.AddCardDetails;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import ross.feehan.com.stripecarddetails.R;

public class StripeAddCardDetailsActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupUI();
    }

    private void setupUI() {
        setContentView(R.layout.stripe_add_card_details_layout);
        ButterKnife.bind(this);
        setUpToolbar();
    }

    private void setUpToolbar() {
        setSupportActionBar(toolbar);
    }

}
