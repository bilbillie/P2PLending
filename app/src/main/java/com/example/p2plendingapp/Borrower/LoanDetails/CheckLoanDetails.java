package com.example.p2plendingapp.Borrower.LoanDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.p2plendingapp.Borrower.MainDashboard;
import com.example.p2plendingapp.R;

public class CheckLoanDetails extends AppCompatActivity implements View.OnClickListener {

    Intent sIntent;
    Button goBackLoanDBt;
    FragmentManager manager;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_loan_details);

        goBackLoanDBt = findViewById(R.id.goBackLoanDBt);
        manager = getSupportFragmentManager();

        //Set up listeners
        goBackLoanDBt.setOnClickListener(this);
    }

    public void addDetailFragment(View view) {
        DetailsFragment detailsFragment = new DetailsFragment();
        transaction = manager.beginTransaction();
        transaction.add(R.id.detailsContainerLoanD, detailsFragment, "details_fragment");
        transaction.commit();
    }

    public void removeDetailFragment(View view) {
        DetailsFragment detailsFragment = (DetailsFragment) manager.findFragmentByTag("details_fragment");
        transaction = manager.beginTransaction();
        transaction.remove(detailsFragment);
        transaction.commit();
    }

    public void showPaymentDialog(View view) {
        PaymentDialogFragment dialogFragment = new PaymentDialogFragment();
        manager = getSupportFragmentManager();
        dialogFragment.show(manager, "payment");
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.goBackLoanDBt) {
            returnToMainDashBoard();
        }
    }

    public void returnToMainDashBoard() {
        sIntent = new Intent(this, MainDashboard.class);
        startActivity(sIntent);
    }
}