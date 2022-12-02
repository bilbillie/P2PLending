package com.example.p2plendingapp.Investor.InvestmentDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.p2plendingapp.Borrower.LoanDetails.DetailsFragment;
import com.example.p2plendingapp.R;

public class CheckInvestmentDetails extends AppCompatActivity {

    FragmentManager manager;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_investment_details);
        manager = getSupportFragmentManager();
    }

    public void addIDetailFragment(View view) {
        IDetailsFragment iDetailsFragment = new IDetailsFragment();
        transaction = manager.beginTransaction();
        transaction.add(R.id.detailsContainerInvestmentD, iDetailsFragment, "i_details_fragment");
        transaction.commit();
    }

    public void removeIDetailFragment(View view) {
        IDetailsFragment fragment = (IDetailsFragment) manager.findFragmentByTag("i_details_fragment");
        transaction = manager.beginTransaction();
        transaction.remove(fragment);
        transaction.commit();
    }
}