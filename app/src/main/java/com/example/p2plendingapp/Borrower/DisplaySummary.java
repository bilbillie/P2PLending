package com.example.p2plendingapp.Borrower;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p2plendingapp.Borrower.LoanDetails.CheckLoanDetails;
import com.example.p2plendingapp.Database.p2pLendingDB;
import com.example.p2plendingapp.Model.Loan;
import com.example.p2plendingapp.R;

public class DisplaySummary extends AppCompatActivity implements View.OnClickListener {

    Button acceptDisSumButton, rejectDisSumButton;
    Intent sIntent, lIntent;
    p2pLendingDB db;
    Loan aLoan;
    TextView riskLvlDisSumResult, borrowPDisSumResult, borrowADisSumResult, dateAgreeDisSumResult, interestRADisSumResult,
            oFeeDisSumResult, oFeeADisSumResult, lFeeDisSumResult, uFeeDisSumResult, mInstallmentDisSumResult;
    CheckBox checkLoanConditions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_summary);

        acceptDisSumButton = findViewById(R.id.acceptDisSumButton);
        rejectDisSumButton = findViewById(R.id.rejectDisSumButton);

        //Set up the listeners
        acceptDisSumButton.setOnClickListener(this);
        rejectDisSumButton.setOnClickListener(this);

        db = new p2pLendingDB(this);
        aLoan = new Loan();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.acceptDisSumButton) {
            openCheckLoanDetails();
            //Get data sent by the borrowers app form
            lIntent = getIntent();
            String pOfBorrowing = lIntent.getStringExtra("purpose_borrowing");
            Boolean aTerms = lIntent.getBooleanExtra("agreed_terms", false);
            double bAmount = lIntent.getDoubleExtra("borrow_amount", 0);
            int bPeriod = lIntent.getIntExtra("borrow_period", 0);
            //Send loan data to the Loan table
            aLoan.setpOBorrowing(pOfBorrowing);
            aLoan.setoFee(Double.parseDouble(oFeeDisSumResult.getText().toString()));
            aLoan.setiRate(Double.parseDouble(interestRADisSumResult.getText().toString()));
            aLoan.setuPFee(Double.parseDouble(uFeeDisSumResult.getText().toString()));
            aLoan.setlPFee(Double.parseDouble(lFeeDisSumResult.getText().toString()));
            aLoan.setmPAmount(Double.parseDouble(mInstallmentDisSumResult.getText().toString()));
            aLoan.setaTerms(aTerms);
            aLoan.setlAmount(bAmount);
            aLoan.setpPeriod(bPeriod);
            aLoan.setsDOAgreement(dateAgreeDisSumResult.getText().toString());
            long numInserted = db.insertIntoLoanTb(aLoan);
            Toast.makeText(this, numInserted + " row(s) were inserted!", Toast.LENGTH_SHORT).show();
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