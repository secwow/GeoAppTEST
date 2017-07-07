package rebenko.geoapp.activities;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import rebenko.geoapp.AppGeo;
import rebenko.geoapp.R;
import rebenko.geoapp.activities.interfaces.ICountryActivity;
import rebenko.geoapp.model.Country;
import rebenko.geoapp.presenters.impl.CountryPresenter;
import rebenko.geoapp.recyclerview.CountryAdapter;

public class CountryActivity extends AppCompatActivity implements ICountryActivity {
    RecyclerView countryRecyclerView;
    CountryPresenter presenter;
    static String country;
    ProgressDialog progress;
    public static String getCountry() {
        return country;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countryRecyclerView = (RecyclerView) findViewById(R.id.countryView);
        countryRecyclerView.setHasFixedSize(false);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        countryRecyclerView.setLayoutManager(mLayoutManager);
        presenter = new CountryPresenter(this);
        progress = new ProgressDialog(this);



    }
    @Override
    protected void onStart() {
        super.onStart();
        presenter.subscribe();
        presenter.init();

    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unsubscribe();
    }


    @Override
    public void showLoading() {
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
    }

    @Override
    public void hideLoading() {
        progress.dismiss();
    }

    @Override
    public void showContries(List<Country> countries) {
        CountryAdapter adapter = new CountryAdapter(countries);
        countryRecyclerView.setAdapter(adapter);
    }

}
