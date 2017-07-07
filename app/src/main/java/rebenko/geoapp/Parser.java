package rebenko.geoapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import rebenko.geoapp.model.City;
import rebenko.geoapp.model.CityInfo;
import rebenko.geoapp.model.Country;

/**
 * Created by User on 29.06.2017.
 */
public class Parser {
    public static List<Country> countriesParser(String reponse) {
        List<Country> countries = new ArrayList<>();

        try {

            JSONObject object = new JSONObject(reponse.trim());
            Iterator<?> keys = object.keys();

            while (keys.hasNext()) {
                String key = (String) keys.next();
                if(key.isEmpty()) continue;
                Country country = new Country();
                country.setName(key);
                JSONArray jsonArray = (JSONArray) object.get(key);
                for (int i = 0; i < jsonArray.length(); i++) {
                    City city = new City();
                    city.setName((String) jsonArray.get(i));
                    country.getCities().add(city);
                }
                countries.add(country);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("size", String.valueOf(countries.size()));
        return countries;
    }

    public static CityInfo infoCityParser(String reponse) {

        CityInfo cityInfo = null;
        try {
            JSONObject object = new JSONObject(reponse);
            cityInfo = new CityInfo();
            JSONArray info = object.getJSONArray("geonames");


            JSONObject jsonObject = (JSONObject) info.get(0);
            cityInfo.setSummary((String) jsonObject.get(Schema.CityInfo.SUMMARY));
            cityInfo.setTitle((String) jsonObject.get(Schema.CityInfo.TITLE));
            cityInfo.setWikipediaUrl((String) jsonObject.get(Schema.CityInfo.WIKI_URL));
            Log.d("Start parsing", "loading...");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cityInfo;
    }
}

