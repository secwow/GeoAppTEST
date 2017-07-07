package rebenko.geoapp.presenters.impl;

import android.util.Log;

import rebenko.geoapp.activities.CountryActivity;
import rebenko.geoapp.activities.DetailCityActivity;
import rebenko.geoapp.model.CityInfo;
import rebenko.geoapp.presenters.ICityInfoPresenter;
import rebenko.geoapp.repository.ICityInfoRepository;
import rebenko.geoapp.repository.imp.CountryRepository;
import rebenko.geoapp.repository.imp.InfoCityRepository;

/**
 * Created by User on 06.07.2017.
 */
public class InfoCityPresenter implements ICityInfoPresenter {
    InfoCityRepository repository;
    DetailCityActivity view;
    ICityInfoRepository.onGetInfoCityCallback getInfoCityCallback;

    public InfoCityPresenter(DetailCityActivity view){
        this.view = view;
        repository = new InfoCityRepository();
    }


    @Override
    public void getInfoAboutCity(String country, String city) {
        view.showLoading();
        repository.findInfo(country, city, getInfoCityCallback);
    }

    @Override
    public void subscribe() {
        getInfoCityCallback = new ICityInfoRepository.onGetInfoCityCallback() {
            @Override
            public void onSuccess(CityInfo cityInfo) {

                view.showData(cityInfo);
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
        getInfoCityCallback = null;
    }
}
