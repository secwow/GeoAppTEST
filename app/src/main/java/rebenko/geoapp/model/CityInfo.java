package rebenko.geoapp.model;

import io.realm.RealmObject;
import io.realm.annotations.Required;


public class CityInfo extends RealmObject {

    @Required
    String summary;
    @Required
    String title;
    @Required
    String wikipediaUrl;


    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWikipediaUrl() {
        return wikipediaUrl;
    }

    public void setWikipediaUrl(String wikipediaUrl) {
        this.wikipediaUrl = wikipediaUrl;
    }


}
