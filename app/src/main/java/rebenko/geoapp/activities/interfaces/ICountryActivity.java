package rebenko.geoapp.activities.interfaces;

import java.util.List;

import rebenko.geoapp.model.Country;

/**
 * Created by User on 06.07.2017.
 */
public interface ICountryActivity extends Loader {

    void showContries(List<Country> countries);
}
