package rebenko.geoapp.repository;

import java.util.List;

import rebenko.geoapp.model.City;
import rebenko.geoapp.model.CityInfo;

/**
 * Created by User on 06.07.2017.
 */
public interface ICityInfoRepository  {
    public interface onGetInfoCityCallback{
        void onSuccess(CityInfo cityInfo);
        void onError(String message);
    }
    void findInfo(String country, String city, onGetInfoCityCallback callback);
}
