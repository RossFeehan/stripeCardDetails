package ross.feehan.com.stripecarddetails.Features.GetCardDetails; /*
 * Created by Ross Feehan on 11/05/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ross.feehan.com.stripecarddetails.Features.AddCardDetails.AddCardDetailsActivity;
import ross.feehan.com.stripecarddetails.R;

public class GetCardDetailsActivity extends AppCompatActivity{

    @Bind(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDependencies();
        setupUI();
    }

    private void initDependencies() {

    }

    private void setupUI() {
        setContentView(R.layout.card_details);
        ButterKnife.bind(this);
        setUpToolbar();
    }

    private void setUpToolbar() {
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.addCardDetailsFAB)
    void onAddCardDetailsFABClicked(){
        Intent addCardIntent = new Intent(getApplication(), AddCardDetailsActivity.class);
        startActivity(addCardIntent);
    }
}
