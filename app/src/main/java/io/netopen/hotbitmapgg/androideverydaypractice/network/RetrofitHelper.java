package io.netopen.hotbitmapgg.androideverydaypractice.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitHelper
{

    public static final String BASE_GANK_URL = "http://gank.io/api/";


    /**
     * Gank妹子Api
     *
     * @return
     */
    public static GankMeiziApi getGankMeiziApi()
    {

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_GANK_URL)
                .client(new OkHttpClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GankMeiziApi gankMeiziApi = mRetrofit.create(GankMeiziApi.class);

        return gankMeiziApi;
    }
}
