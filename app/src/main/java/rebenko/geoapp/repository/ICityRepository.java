package rebenko.geoapp.repository;

import java.util.List;

import rebenko.geoapp.model.City;

/**
 * Created by User on 06.07.2017.
 */
public interface ICityRepository {
    interface onGetCityByCountryCallback{
        void onSuccess(List<City> cityList);
        void onError(String message);
    }
    void findAll(String name, onGetCityByCountryCallback callback);
}
