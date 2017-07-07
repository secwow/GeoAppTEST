package rebenko.geoapp.presenters;

import java.util.List;

import rebenko.geoapp.model.City;
import rebenko.geoapp.model.Country;
import rebenko.geoapp.repository.ICountryRepository;
import rebenko.geoapp.repository.imp.CountryRepository;

/**
 * Created by User on 06.07.2017.
 */
public interface ICountryPresenter  extends BasePresenter{
    void init();
    void addAll(List<Country> cityList);
    void findAll();

}
