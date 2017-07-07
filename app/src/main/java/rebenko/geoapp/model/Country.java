package rebenko.geoapp.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by User on 28.06.2017.
 */
public class Country extends RealmObject {


    @Required
    String name;

    RealmList<City> cities = new RealmList<City>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<City> getCities() {
        return cities;
    }
    public void setCities(RealmList<City> cities) {
        this.cities = cities;
    }

}
