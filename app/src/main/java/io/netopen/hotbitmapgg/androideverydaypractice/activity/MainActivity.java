package io.netopen.hotbitmapgg.androideverydaypractice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.OnClick;
import io.netopen.hotbitmapgg.androideverydaypractice.R;
import io.netopen.hotbitmapgg.androideverydaypractice.base.AbsBaseActivity;

public class MainActivity extends AbsBaseActivity
{

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public int getLayoutId()
    {

        return R.layout.activity_main;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {

    }

    @Override
    public void initToolBar()
    {

        mToolbar.setTitle(R.string.app_name);
    }


    @OnClick(R.id.main_btn_1)
    void startCustomWidgetActivity()
    {

        startActivity(new Intent(MainActivity.this, CustomWidgetActivity.class));
    }


    @OnClick(R.id.main_btn_2)
    void startMdWidgetActivity()
    {

        startActivity(new Intent(MainActivity.this, MdWidgetActivity.class));
    }


    @OnClick(R.id.main_btn_3)
    void startRxJavaActivity()
    {

        startActivity(new Intent(MainActivity.this, RxJavaActivity.class));
    }
}
