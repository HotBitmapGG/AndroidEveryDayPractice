package io.netopen.hotbitmapgg.androideverydaypractice;

import android.app.Application;
import android.content.Context;


public class StudyApp extends Application
{

    public static Context mAppContext;

    @Override
    public void onCreate()
    {

        super.onCreate();
        mAppContext = this;
//        // 配置Realm数据库
//        RealmConfiguration configuration = new RealmConfiguration.Builder(this).deleteRealmIfMigrationNeeded().schemaVersion(6).migration(new RealmMigration()
//        {
//
//            @Override
//            public void migrate(DynamicRealm realm, long oldVersion, long newVersion)
//            {
//
//            }
//        }).build();
//
//        Realm.setDefaultConfiguration(configuration);
    }


    public static Context getContext()
    {

        return mAppContext;
    }
}
