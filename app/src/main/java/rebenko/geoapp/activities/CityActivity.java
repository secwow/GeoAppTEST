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
import rebenko.geoapp.activities.interfaces.ICityActivity;
import rebenko.geoapp.model.City;
import rebenko.geoapp.presenters.impl.CityPresenter;
import rebenko.geoapp.recyclerview.CityAdapter;


public class CityActivity extends AppCompatActivity implements ICityActivity{
    RecyclerView cityRecyclerView;
    static String country;
    CityPresenter presenter;
    ProgressDialog progress;

    public static String getCountry() {
        return country;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        cityRecyclerView = (RecyclerView)findViewById(R.id.cityView);

        cityRecyclerView.setHasFixedSize(true);
        country =  getIntent().getExtras().getString("country");

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        cityRecyclerView.setLayoutManager(mLayoutManager);
        presenter = new CityPresenter(this);
        progress = new ProgressDialog(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.subscribe();
        presenter.getCityByCountry(country);

    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unsubscribe();
    }

    @Override
    public void showCities(List<City> cityList) {
        CityAdapter adapter = new CityAdapter(cityList);
        cityRecyclerView.setAdapter(adapter);
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


}
