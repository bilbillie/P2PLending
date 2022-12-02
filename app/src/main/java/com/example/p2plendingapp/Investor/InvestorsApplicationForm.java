package com.example.p2plendingapp.Investor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.p2plendingapp.Borrower.DisplaySummary;
import com.example.p2plendingapp.Borrower.MainDashboard;
import com.example.p2plendingapp.Investor.MarketPlace_Java.MarketPlaceAccess;
import com.example.p2plendingapp.R;

public class InvestorsApplicationForm extends AppCompatActivity implements View.OnClickListener {

    Button submitIAppFormButton, declineIAppFormButton;
    Intent sIntent, lIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investors_application_form);

        submitIAppFormButton = findViewById(R.id.submitIAppFormButton);
        declineIAppFormButton = findViewById(R.id.declineIAppFormButton);

        //Set up listeners
        submitIAppFormButton.setOnClickListener(this);
        declineIAppFormButton.setOnClickListener(this);

    }

    public void exploreMarketPlace() {
        lIntent = getIntent();
        if (lIntent != null) {
            Boolean agreedTerms = lIntent.getBooleanExtra("Agreed Terms", true);
            sIntent = new Intent(this, MarketPlaceAccess.class);
            startActivity(sIntent);
        }
    }

    public void returnToMainDashBoardI() {
        sIntent = new Intent(getApplicationContext(), MainDashboardInvestor.class);
        startActivity(sIntent);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.declineIAppFormButton) {
            returnToMainDashBoardI();
        } else {
            exploreMarketPlace();
        }
    }
}