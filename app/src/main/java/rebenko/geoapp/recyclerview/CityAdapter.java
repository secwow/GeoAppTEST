package rebenko.geoapp.recyclerview;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import rebenko.geoapp.AppGeo;
import rebenko.geoapp.R;
import rebenko.geoapp.activities.CityActivity;
import rebenko.geoapp.activities.CountryActivity;
import rebenko.geoapp.activities.DetailCityActivity;
import rebenko.geoapp.model.City;
import rebenko.geoapp.model.Country;


/**
 * Created by User on 29.06.2017.
 */
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    List<City> cities;
    public CityAdapter(List<City> cities){
        this.cities = cities;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView cityName;

        public ViewHolder(View v) {
            super(v);
            cityName = (TextView) v.findViewById(R.id.city_name);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item  = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item,parent, false);
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView = (TextView)view.findViewById(R.id.city_name);
                String result = textView.getText().toString();
                Intent intent = new Intent(parent.getContext(), DetailCityActivity.class);
                intent.putExtra("country", CityActivity.getCountry());
                intent.putExtra("city", result);
                parent.getContext().startActivity(intent);
            }
        });
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.cityName.setText( cities.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }


}