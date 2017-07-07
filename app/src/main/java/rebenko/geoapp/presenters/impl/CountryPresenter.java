package rebenko.geoapp.presenters.impl;

import java.util.List;

import rebenko.geoapp.activities.CountryActivity;
import rebenko.geoapp.model.City;
import rebenko.geoapp.model.Country;
import rebenko.geoapp.presenters.ICountryPresenter;
import rebenko.geoapp.repository.ICountryRepository;
import rebenko.geoapp.repository.imp.CountryRepository;

/**
 * Created by User on 06.07.2017.
 */
public class CountryPresenter implements ICountryPresenter {
    CountryActivity view;
    CountryRepository repository;
    ICountryRepository.onAddAllCallback addAllCallback;
    ICountryRepository.onGetAllCountryCallback getAllCountryCallback;

    public CountryPresenter(CountryActivity view){
        this.view = view;
        repository = new CountryRepository();
    }

    @Override
    public void init(){
        view.showLoading();
        repository.primaryInit(getAllCountryCallback);
    }

    @Override
    public void addAll(List<Country> cityList) {
        repository.addAll(cityList, addAllCallback);
    }

    @Override
    public void findAll() {
        repository.findAll(getAllCountryCallback);
    }


    @Override
    public void subscribe() {
        addAllCallback = new ICountryRepository.onAddAllCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(String message) {

            }
        };
        getAllCountryCallback = new ICountryRepository.onGetAllCountryCallback() {
            @Override
            public void onSuccess(List<Country> countries) {

                view.showContries(countries);
                view.hideLoading();
            }

            @Override
            public void onError(String message) {

            }
        };
    }

    @Override
    public void unsubscribe() {
        addAllCallback =null;
        getAllCountryCallback=null;
    }
}
