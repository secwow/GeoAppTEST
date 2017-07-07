package rebenko.geoapp.presenters.impl;

import java.util.List;

import rebenko.geoapp.activities.CityActivity;
import rebenko.geoapp.model.City;
import rebenko.geoapp.presenters.ICityPresenter;
import rebenko.geoapp.repository.ICityRepository;
import rebenko.geoapp.repository.imp.CityRepository;

/**
 * Created by User on 06.07.2017.
 */
public class CityPresenter implements ICityPresenter {
    ICityRepository.onGetCityByCountryCallback getCityByCountryCallback;
    CityActivity view;
    CityRepository repository;

    public CityPresenter(CityActivity view){
        this.view = view;
        repository = new CityRepository();
    }

    @Override
    public void getCityByCountry(String country) {
        view.showLoading();
        repository.findAll(country, getCityByCountryCallback);

    }


    @Override
    public void subscribe() {
        getCityByCountryCallback = new ICityRepository.onGetCityByCountryCallback() {
            @Override
            public void onSuccess(List<City> cityList) {

                view.showCities(cityList);
                view.hideLoading();
            }

            @Override
            public void onError(String message) {
                view.hideLoading();
            }
        };
    }

    @Override
    public void unsubscribe() {
        getCityByCountryCallback = null;
    }
}
