package rebenko.geoapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import rebenko.geoapp.R;
import rebenko.geoapp.activities.interfaces.ICityInfoActivity;
import rebenko.geoapp.model.CityInfo;
import rebenko.geoapp.presenters.impl.InfoCityPresenter;

public class DetailCityActivity extends AppCompatActivity implements ICityInfoActivity {

    TextView description;
    TextView title;
    TextView wikiURL;
    String city, country;
    ProgressDialog progress;
    InfoCityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_city);
        description = (TextView)findViewById(R.id.city_info);
        title = (TextView)findViewById(R.id.city_title);
        wikiURL = (TextView)findViewById(R.id.wiki_url);
        Intent intent = getIntent();
        city = intent.getExtras().getString("city");
        country = intent.getExtras().getString("country");
        progress = new ProgressDialog(this);
        presenter = new InfoCityPresenter(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        presenter.subscribe();
        presenter.getInfoAboutCity(country, city);

    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unsubscribe();
    }

    @Override
    public void showData(CityInfo cityInfo) {

            description.setText(cityInfo.getSummary());
            title.setText(cityInfo.getTitle());
            wikiURL.setText(cityInfo.getWikipediaUrl());

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
