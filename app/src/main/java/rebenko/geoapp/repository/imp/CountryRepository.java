package rebenko.geoapp.repository.imp;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import rebenko.geoapp.AppGeo;
import rebenko.geoapp.Parser;
import rebenko.geoapp.model.Country;
import rebenko.geoapp.repository.ICountryRepository;
import rebenko.geoapp.repository.WebService;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

/**
 * Created by User on 06.07.2017.
 */
public class CountryRepository implements ICountryRepository {



    @Override
    public void findAll(onGetAllCountryCallback callback) {
        Realm realmConnection = Realm.getDefaultInstance();
        List<Country> countries = realmConnection.where(Country.class).findAll();

        if(callback!=null){
            callback.onSuccess(countries);
        }

        realmConnection.close();

    }

    @Override
    public void primaryInit(onGetAllCountryCallback callback) {
        final Realm realmConnection = Realm.getDefaultInstance();
        if(realmConnection.isEmpty()){
            WebService webService = WebService.getInstace(AppGeo.getInstance());
            Observable.defer(() -> Observable.just(webService.getCountriesInformation()))
                    .map(Parser::countriesParser)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<List<Country>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(List<Country> countries) {
                            callback.onSuccess(countries);
                            realmConnection.executeTransaction(realm -> realmConnection.copyToRealmOrUpdate(countries));

                        }
                    });

        }
        else {
            findAll(callback);
        }
        realmConnection.close();
    }

    @Override
    public void addAll(List<Country> countries, onAddAllCallback callback) {

        Realm realmConnection = Realm.getDefaultInstance();
        realmConnection.executeTransaction(realm -> realm.copyToRealmOrUpdate(countries));


        if(callback!=null){
            callback.onSuccess();
        }
        realmConnection.close();

    }
}
