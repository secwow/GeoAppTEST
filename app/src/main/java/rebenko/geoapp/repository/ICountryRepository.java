package rebenko.geoapp.repository;

import android.content.Context;

import java.util.List;

import rebenko.geoapp.model.Country;

/**
 * Created by User on 06.07.2017.
 */
public interface ICountryRepository {

    interface onGetAllCountryCallback{
        void onSuccess(List<Country> countries);
        void onError(String message);
    }
    interface onAddAllCallback{
        void onSuccess();
        void onError(String message);
    }

    void findAll(onGetAllCountryCallback callback);
    void primaryInit(onGetAllCountryCallback callback);
    void addAll(List<Country> countries, onAddAllCallback callback);
}
