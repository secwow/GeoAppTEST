package rebenko.geoapp;

/**
 * Created by User on 29.06.2017.
 */
public class Schema {

    public interface Country {
        String NAME = "name";
        String CITIES = "name";
    }

    public interface City{
        String NAME = "name";;
    }
    public interface CityInfo{
        String SUMMARY = "summary";
        String WIKI_URL = "wikipediaUrl";
        String TITLE = "title";
    }
}
