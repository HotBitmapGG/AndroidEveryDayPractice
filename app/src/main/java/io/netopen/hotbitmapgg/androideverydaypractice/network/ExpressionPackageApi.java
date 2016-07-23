package io.netopen.hotbitmapgg.androideverydaypractice.network;


import java.util.List;

import io.netopen.hotbitmapgg.androideverydaypractice.model.ExpressionPackage;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ExpressionPackageApi
{

    @GET("search")
    Observable<List<ExpressionPackage>> search(@Query("q") String query);
}
