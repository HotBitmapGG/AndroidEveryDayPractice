package io.netopen.hotbitmapgg.androideverydaypractice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.Bind;
import butterknife.OnClick;
import io.netopen.hotbitmapgg.androideverydaypractice.R;
import io.netopen.hotbitmapgg.androideverydaypractice.base.AbsBaseActivity;
import io.netopen.hotbitmapgg.androideverydaypractice.recycleview.RecycleViewDemoActivity;
import io.netopen.hotbitmapgg.androideverydaypractice.widght_demo.ScrollDemoActivity;


public class MdWidgetActivity extends AbsBaseActivity
{

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public int getLayoutId()
    {

        return R.layout.activity_md;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {

    }

    @Override
    public void initToolBar()
    {
        mToolbar.setTitle("MaterialDesignDemo相关");
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


    @OnClick(R.id.md_btn_1)
    void startRecycleViewDemo()
    {

        startActivity(new Intent(this, RecycleViewDemoActivity.class));
    }

    @OnClick(R.id.md_btn_2)
    void startScrollDemo()
    {

        startActivity(new Intent(this, ScrollDemoActivity.class));
    }
}
