package io.netopen.hotbitmapgg.androideverydaypractice.activity;

import android.os.Bundle;

import butterknife.Bind;
import io.netopen.hotbitmapgg.androideverydaypractice.R;
import io.netopen.hotbitmapgg.androideverydaypractice.base.AbsBaseActivity;
import io.netopen.hotbitmapgg.androideverydaypractice.widget.other.SlackAnimView;

/**
 * Created by hcc on 16/7/27 21:11
 * 100332338@qq.com
 */
public class PathDemoActivity extends AbsBaseActivity
{


    @Bind(R.id.slack_view)
    SlackAnimView slackView;

    @Override
    public int getLayoutId()
    {

        return R.layout.activity_path_demo;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {

        slackView.reset();
        slackView.start();
    }

    @Override
    public void initToolBar()
    {

    }
}
