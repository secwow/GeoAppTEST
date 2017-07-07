package rebenko.geoapp;

import android.app.Application;

import com.facebook.stetho.Stetho;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by User on 06.07.2017.
 */
public class AppGeo extends Application {
    private static AppGeo instance;
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("geoapp.realm")
                .schemaVersion(1)
                .modules(new RealmModule())
                .build();
        Realm.setDefaultConfiguration(config);
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        });

        instance = this;
    }

    public static AppGeo getInstance(){
        return instance;
    }
}
