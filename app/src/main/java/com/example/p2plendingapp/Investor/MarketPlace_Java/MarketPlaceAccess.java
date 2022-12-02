package com.example.p2plendingapp.Investor.MarketPlace_Java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.p2plendingapp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MarketPlaceAccess extends AppCompatActivity {

    ListView listView;
    ListAdapterClass adapter;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_place_access);
        listView = findViewById(R.id.iOpportunitiesList);

        //Set the Adapter
        setAdapter();

        // Upon item click, checkbox will be set to checked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                populateAndGetList().get(position).setChecked(true);
                adapter.notifyDataSetChanged();
            }
        });

        //Set manager
        manager = getSupportFragmentManager();
    }


    public ArrayList<Opportunities> populateAndGetList() {
        ArrayList<Opportunities> iOpportunities = new ArrayList<Opportunities>();
        iOpportunities.add(new Opportunities(1, "3300", 1000, 6, "High Risk", 100, false));
        iOpportunities.add(new Opportunities(2, "3300", 1000, 6, "High Risk", 100, false));
        iOpportunities.add(new Opportunities(3, "3300", 1000, 6, "High Risk", 100, true));
        iOpportunities.add(new Opportunities(4, "3300", 1000, 6, "High Risk", 100, false));
        iOpportunities.add(new Opportunities(5, "3300", 1000, 6, "High Risk", 100, true));
        return iOpportunities;
    }


    public void setAdapter() {
        //Setting the adapter
        adapter = new ListAdapterClass(this, populateAndGetList());
        listView.setAdapter(adapter);
    }

    public void showDealDialog(View view) {
        DealDialogFragment dealDialogFragment = new DealDialogFragment();
        manager = getSupportFragmentManager();
        dealDialogFragment.show(manager, "deal");

    }
}