package io.netopen.hotbitmapgg.androideverydaypractice.fragment;

import android.content.Intent;

import butterknife.OnClick;
import io.netopen.hotbitmapgg.androideverydaypractice.R;
import io.netopen.hotbitmapgg.androideverydaypractice.base.LazyFragment;
import io.netopen.hotbitmapgg.androideverydaypractice.widght_demo.BezaerActivity;
import io.netopen.hotbitmapgg.androideverydaypractice.widght_demo.CircleProgressBarActivity;
import io.netopen.hotbitmapgg.androideverydaypractice.widght_demo.LoveActivity;
import io.netopen.hotbitmapgg.androideverydaypractice.widght_demo.SlideGankMeiziActivity;
import io.netopen.hotbitmapgg.androideverydaypractice.widght_demo.TableActivity;


public class CustomWidgetFragment extends LazyFragment
{


    public static CustomWidgetFragment newInstance()
    {

        return new CustomWidgetFragment();
    }

    @Override
    public int getLayoutId()
    {

        return R.layout.fragment_widget;
    }

    @OnClick(R.id.widget_1)
    void startLoveAnimLayout()
    {

        startActivity(new Intent(getActivity(), LoveActivity.class));
    }

    @OnClick(R.id.widget_2)
    void startCircleProgress()
    {

        startActivity(new Intent(getActivity(), CircleProgressBarActivity.class));
    }

    @OnClick(R.id.widget_3)
    void startTableView()
    {

        startActivity(new Intent(getActivity(), TableActivity.class));
    }

    @OnClick(R.id.widget_4)
    void startBezaerDemo()
    {

        startActivity(new Intent(getActivity(), BezaerActivity.class));
    }

    @OnClick(R.id.widget_5)
    void startTanTan()
    {

        startActivity(new Intent(getActivity(), SlideGankMeiziActivity.class));
    }


    @Override
    public void initViews()
    {

    }
}
