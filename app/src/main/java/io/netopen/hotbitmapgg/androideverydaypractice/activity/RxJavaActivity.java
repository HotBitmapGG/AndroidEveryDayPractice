package io.netopen.hotbitmapgg.androideverydaypractice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.Bind;
import butterknife.OnClick;
import io.netopen.hotbitmapgg.androideverydaypractice.R;
import io.netopen.hotbitmapgg.androideverydaypractice.base.AbsBaseActivity;
import io.netopen.hotbitmapgg.androideverydaypractice.rxjava.RxJavaSampleActivity;
import io.netopen.hotbitmapgg.androideverydaypractice.widget.other.ItemArrowCardView;

public class RxJavaActivity extends AbsBaseActivity
{

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.btn_2)
    ItemArrowCardView mRxjavaSample;


    @Override
    public int getLayoutId()
    {

        return R.layout.activity_rxjava;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {

    }

    @Override
    public void initToolBar()
    {

        mToolbar.setTitle("RxJava相关");
        mToolbar.setNavigationIcon(R.drawable.back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {

                onBackPressed();
            }
        });
    }


    @OnClick(R.id.btn_2)
    void startRxJavaSample()
    {

        startActivity(new Intent(this, RxJavaSampleActivity.class));
    }
}
