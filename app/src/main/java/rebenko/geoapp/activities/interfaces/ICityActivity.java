package rebenko.geoapp.activities.interfaces;

import java.util.List;

import rebenko.geoapp.model.City;

/**
 * Created by User on 06.07.2017.
 */
public interface ICityActivity extends Loader {
    void showCities(List<City> cityList);
}
