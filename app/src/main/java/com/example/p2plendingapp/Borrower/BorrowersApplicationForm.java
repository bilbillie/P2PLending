package com.example.p2plendingapp.Borrower;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.p2plendingapp.R;

public class BorrowersApplicationForm extends AppCompatActivity implements View.OnClickListener {
    Intent sIntent, lIntent;
    Button submitAppFormButton, declineAppFormButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrowers_application_form);

        submitAppFormButton = findViewById(R.id.submitAppFormButton);
        declineAppFormButton = findViewById(R.id.declineAppFormButton);

        //Set up listeners
        submitAppFormButton.setOnClickListener(this);
        declineAppFormButton.setOnClickListener(this);

    }


    public void displayLoanSummary() {
        lIntent = getIntent();
        if (lIntent != null) {
            Boolean agreedTerms = lIntent.getBooleanExtra("Agreed Terms", true);
            sIntent = new Intent(this, DisplaySummary.class);
            startActivity(sIntent);
        }
    }

    public void returnToMainDashBoard() {
        sIntent = new Intent(this, MainDashboard.class);
        startActivity(sIntent);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.declineAppFormButton) {
            returnToMainDashBoard();
        } else {
            displayLoanSummary();
        }
    }
}