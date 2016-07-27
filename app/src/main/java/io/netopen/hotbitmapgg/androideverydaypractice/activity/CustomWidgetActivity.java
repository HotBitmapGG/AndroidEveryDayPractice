package io.netopen.hotbitmapgg.androideverydaypractice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.Bind;
import butterknife.OnClick;
import io.netopen.hotbitmapgg.androideverydaypractice.R;
import io.netopen.hotbitmapgg.androideverydaypractice.base.AbsBaseActivity;
import io.netopen.hotbitmapgg.androideverydaypractice.widght_demo.BezaerActivity;
import io.netopen.hotbitmapgg.androideverydaypractice.widght_demo.CircleProgressBarActivity;
import io.netopen.hotbitmapgg.androideverydaypractice.widght_demo.LoveActivity;
import io.netopen.hotbitmapgg.androideverydaypractice.widght_demo.SlideGankMeiziActivity;
import io.netopen.hotbitmapgg.androideverydaypractice.widght_demo.TableActivity;


public class CustomWidgetActivity extends AbsBaseActivity
{

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public int getLayoutId()
    {

        return R.layout.activity_widget;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {

        mToolbar.setTitle("自定义控件相关");
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

    @Override
    public void initToolBar()
    {

    }

    @OnClick(R.id.widget_1)
    void startLoveAnimLayout()
    {

        startActivity(new Intent(this, LoveActivity.class));
    }

    @OnClick(R.id.widget_2)
    void startCircleProgress()
    {

        startActivity(new Intent(this, CircleProgressBarActivity.class));
    }

    @OnClick(R.id.widget_3)
    void startTableView()
    {

        startActivity(new Intent(this, TableActivity.class));
    }

    @OnClick(R.id.widget_4)
    void startBezaerDemo()
    {

        startActivity(new Intent(this, BezaerActivity.class));
    }

    @OnClick(R.id.widget_5)
    void startTanTan()
    {

        startActivity(new Intent(this, SlideGankMeiziActivity.class));
    }

    @OnClick(R.id.widget_6)
    void startPathDemo()
    {

        startActivity(new Intent(this, PathDemoActivity.class));
    }
}
