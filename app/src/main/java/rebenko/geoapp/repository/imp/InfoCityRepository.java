package rebenko.geoapp.repository.imp;

import android.util.Log;

import rebenko.geoapp.AppGeo;
import rebenko.geoapp.Parser;
import rebenko.geoapp.model.CityInfo;
import rebenko.geoapp.repository.ICityInfoRepository;
import rebenko.geoapp.repository.WebService;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by User on 06.07.2017.
 */
public class InfoCityRepository implements ICityInfoRepository {
    @Override
    public void findInfo(String country, String city, onGetInfoCityCallback callback) {

        WebService service = WebService.getInstace(AppGeo.getInstance());

        Observable.defer(() -> Observable.just(Parser.infoCityParser(service.getInformationAboutCity(country,city))))
                .map(a->{Log.d("d",a.getTitle()+a.getTitle()+a.getSummary()); return  a;})
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CityInfo>() {
                    @Override
                    public void onCompleted() {
                        Log.d("in", "complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(CityInfo cityInfo) {
                        callback.onSuccess(cityInfo);
                    }
                });
    }
}
