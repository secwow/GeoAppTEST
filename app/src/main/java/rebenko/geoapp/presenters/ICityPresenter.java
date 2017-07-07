package rebenko.geoapp.presenters;

import rebenko.geoapp.repository.ICityRepository;
import rebenko.geoapp.repository.ICountryRepository;

/**
 * Created by User on 06.07.2017.
 */
public interface ICityPresenter extends BasePresenter {
    void getCityByCountry(String country);
}
