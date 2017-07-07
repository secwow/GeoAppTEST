package rebenko.geoapp;

import rebenko.geoapp.model.City;
import rebenko.geoapp.model.Country;

/**
 * Created by User on 06.07.2017.
 */
@io.realm.annotations.RealmModule (classes = {City.class, Country.class})
public class RealmModule {
}
