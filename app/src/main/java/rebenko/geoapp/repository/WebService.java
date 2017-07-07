package rebenko.geoapp.repository;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class WebService {

    private class ForceCacheInterceptor implements Interceptor {
        Context context;

        ForceCacheInterceptor(Context context) {
            this.context = context;
        }

        public boolean isOnline(Context ctx) {
            if (ctx == null)
                return false;

            ConnectivityManager cm =
                    (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnectedOrConnecting();
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
            if (!isOnline(context)) {
                builder.cacheControl(CacheControl.FORCE_CACHE);
            }
            return chain.proceed(builder.build());
        }
    }

    private static Context context;
    private static WebService service;
    private static final String INFORMATION_URL = "https://raw.githubusercontent.com/David-Haim/CountriesToCitiesJSON/master/countriesToCities.json";
    private static final Map<String, String> countries = new HashMap<>();
    private static OkHttpClient client;

    static {
        for (String iso : Locale.getISOCountries()) {
            Locale locale = new Locale("", iso);
            // Get country name for each code.
            String name = locale.getDisplayCountry(Locale.ENGLISH);
            countries.put(name, iso);

        }
    }

    private WebService(Context context) {
        Cache cache = new Cache(context.getCacheDir(), 1024 * 1024 * 10);

        client = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(new ForceCacheInterceptor(context))
                .build();
    }

    public synchronized static WebService getInstace(Context ctx) {

        if (service == null) {
            context = ctx;
            service = new WebService(ctx);
        }
        return service;
    }

    private synchronized String countrySearch(String country) {
        return countries.get(country);
    }

    public synchronized String getCountriesInformation() {

        Cache cache = new Cache(context.getCacheDir(), 1024 * 1024 * 10);
        OkHttpClient clientCountry = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(new ForceCacheInterceptor(context))
                .build();

        Request request = new Request.Builder()
                .url(INFORMATION_URL)
                .build();

        Response response;

        try {

            response = clientCountry.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public synchronized String getInformationAboutCity(String country, String cityName) {
        String searchedCountry = countrySearch(country);

        String search_url = String.format("http://api.geonames.org/wikipediaSearchJSON?q=%s&maxRows=10&country=%s&username=secwow",
                cityName.toLowerCase().trim(), searchedCountry.toLowerCase().trim());

        Request request = new Request.Builder()
                .url(search_url)
                .build();
        Response response;
        try {
            response = client.newCall(request).execute();


            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
