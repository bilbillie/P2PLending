package com.example.p2plendingapp.Borrower;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.p2plendingapp.Borrower.LoanDetails.CheckLoanDetails;
import com.example.p2plendingapp.R;

public class DisplaySummary extends AppCompatActivity implements View.OnClickListener {

    Button acceptDisSumButton, rejectDisSumButton;
    Intent sIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_summary);

        acceptDisSumButton = findViewById(R.id.acceptDisSumButton);
        rejectDisSumButton = findViewById(R.id.rejectDisSumButton);

        //Set up the listeners
        acceptDisSumButton.setOnClickListener(this);
        rejectDisSumButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.acceptDisSumButton) {
            openCheckLoanDetails();
        } else {
            returnToMainDashBoard();
        }
    }

    public void openCheckLoanDetails() {
        sIntent = new Intent(this, CheckLoanDetails.class);
        startActivity(sIntent);
    }

    public void returnToMainDashBoard() {
        sIntent = new Intent(this, MainDashboard.class);
        startActivity(sIntent);
    }
}