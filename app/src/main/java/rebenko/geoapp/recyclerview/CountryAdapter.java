package rebenko.geoapp.recyclerview;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import rebenko.geoapp.R;
import rebenko.geoapp.activities.CityActivity;
import rebenko.geoapp.activities.CountryActivity;
import rebenko.geoapp.activities.DetailCityActivity;
import rebenko.geoapp.model.City;
import rebenko.geoapp.model.Country;


/**
 * Created by User on 29.06.2017.
 */
public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    List<Country> countries;
    public CountryAdapter(List<Country> countries){
        this.countries = countries;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView countryName;

        public ViewHolder(View v) {
            super(v);
            countryName = (TextView) v.findViewById(R.id.countryName);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item  = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item,parent, false);
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView = (TextView)view.findViewById(R.id.countryName);
                String result = textView.getText().toString();
                Intent intent = new Intent(parent.getContext(), CityActivity.class);
                intent.putExtra("country", result);
                parent.getContext().startActivity(intent);
            }
        });

        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.countryName.setText( countries.get(position).getName());

    }


    @Override
    public int getItemCount() {
        return countries.size();
    }


}
