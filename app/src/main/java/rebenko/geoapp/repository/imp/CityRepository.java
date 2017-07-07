package rebenko.geoapp.repository.imp;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import rebenko.geoapp.model.City;
import rebenko.geoapp.model.Country;
import rebenko.geoapp.repository.ICityRepository;

/**
 * Created by User on 06.07.2017.
 */
public class CityRepository implements ICityRepository {
    @Override
    public void findAll(String name, onGetCityByCountryCallback callback) {

        Realm realmConnection = Realm.getDefaultInstance();
        List<City> cities = realmConnection.where(Country.class).equalTo("name",name).findFirst().getCities();

        if(callback!=null){
            callback.onSuccess(cities);
        }
        realmConnection.close();


    }
}
