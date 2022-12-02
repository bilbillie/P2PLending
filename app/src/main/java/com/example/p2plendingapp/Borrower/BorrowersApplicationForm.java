package com.example.p2plendingapp.Borrower;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p2plendingapp.Database.p2pLendingDB;
import com.example.p2plendingapp.Model.Borrower;
import com.example.p2plendingapp.R;

public class BorrowersApplicationForm extends AppCompatActivity implements View.OnClickListener {
    Intent sIntent, lIntent;
    Button submitAppFormButton, declineAppFormButton;
    p2pLendingDB db;
    Borrower aBorrower;
    RadioGroup resStatusAppForm, maritalStatusAppForm, pStatusAppForm, pOfBorrowingAppForm;
    EditText cBankAccAppFormEt, borrowAAppFormEt, borrowPAPPFormEt, netIncomeAppFormEt, netIncomeSpouseAppFormEt, expensesAppFormEt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrowers_application_form);

        submitAppFormButton = findViewById(R.id.submitAppFormButton);
        declineAppFormButton = findViewById(R.id.declineAppFormButton);
        netIncomeAppFormEt = findViewById(R.id.netIncomeAppFormEt);
        netIncomeSpouseAppFormEt = findViewById(R.id.netIncomeSpouseAppFormEt);
        expensesAppFormEt = findViewById(R.id.expensesAppFormEt);
        resStatusAppForm = findViewById(R.id.resStatusAppForm);
        maritalStatusAppForm = findViewById(R.id.maritalStatusAppForm);
        pStatusAppForm = findViewById(R.id.pStatusAppForm);

        pOfBorrowingAppForm = findViewById(R.id.pOfBorrowingAppForm);
        borrowAAppFormEt = findViewById(R.id.borrowAAppFormEt);
        borrowPAPPFormEt = findViewById(R.id.borrowPAPPFormEt);

        cBankAccAppFormEt = findViewById(R.id.cBankAccAppFormEt);

        //Set up listeners
        submitAppFormButton.setOnClickListener(this);
        declineAppFormButton.setOnClickListener(this);

        db = new p2pLendingDB(this);
        aBorrower = new Borrower();

    }

    public void displayLoanSummary() {
        lIntent = getIntent();
        if (lIntent != null) {
            Boolean agreedTerms = lIntent.getBooleanExtra("Agreed Terms", true);
            sIntent = new Intent(this, DisplaySummary.class);
            //Continue passing the agreedTerms
            sIntent.putExtra("agreed_terms", agreedTerms);

            //Get the id of every radio button inside purpose of borrowing radio group
            //Pass the chosen value to the display summary activity
            int pOfBorrowingId = pOfBorrowingAppForm.getCheckedRadioButtonId();
            if (pOfBorrowingId == R.id.goodOptionAppForm) {
                sIntent.putExtra("purpose_borrowing", "buy a good");
            } else if (pOfBorrowingId == R.id.debtOptionAppForm) {
                sIntent.putExtra("purpose_borrowing", "pay debt");
            } else if (pOfBorrowingId == R.id.housingOptionAppForm) {
                sIntent.putExtra("purpose_borrowing", "buy house/apartment");
            } else if (pOfBorrowingId == R.id.carOptionAppForm) {
                sIntent.putExtra("purpose_borrowing", "buy a car");
            } else {
                sIntent.putExtra("purpose_borrowing", "other");
            }

            //Pass the following data to the display summary activity
            double borrowA= Double.parseDouble(borrowAAppFormEt.getText().toString());
            if (borrowA > 25000 || borrowA < 1000) {
                Toast.makeText(this, " Please, insert a borrow amount greater that or equal to $1000 and lower than or equal to $25000", Toast.LENGTH_SHORT).show();
            } else {
                sIntent.putExtra("borrow_amount", borrowA);
            }
            sIntent.putExtra("borrow_period", Integer.parseInt(borrowPAPPFormEt.getText().toString()));

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
            //Display loan summary
            displayLoanSummary();
            //Send borrower data to the borrower table
            aBorrower.setHmExp(Double.parseDouble(expensesAppFormEt.getText().toString()));
            aBorrower.setmNetIncome(Double.parseDouble(netIncomeAppFormEt.getText().toString()));
            aBorrower.setmSNetIncome(Double.parseDouble(netIncomeSpouseAppFormEt.getText().toString()));
            //Get the id of every radio button inside residency status radio group
            //Assign the respective value to the Borrower object
            int resStatusId = resStatusAppForm.getCheckedRadioButtonId();
            if (resStatusId == R.id.tOptionAppForm) {
                aBorrower.setrStatus("temporary");
            } else {
                aBorrower.setrStatus("permanent");
            }
            //Get the id of every radio button inside marital status radio group
            //Assign the respective value to the Borrower object
            int mStatusId = maritalStatusAppForm.getCheckedRadioButtonId();
            if (mStatusId == R.id.sOptionAppForm) {
                aBorrower.setmStatus("single");
            } else {
                aBorrower.setmStatus("married");
            }
            //Get the id of every radio button inside property status radio group
            //Assign the respective value to the Borrower object
            int pStatusId = pStatusAppForm.getCheckedRadioButtonId();
            if (pStatusId == R.id.rOptionAppForm) {
                aBorrower.setpStatus("rent");
            } else {
                aBorrower.setpStatus("own");
            }
            long numInserted = db.insertIntoBorrowersTb(aBorrower);
            Toast.makeText(this, numInserted + " row(s) were inserted!", Toast.LENGTH_SHORT).show();

        }
    }

}