package io.netopen.hotbitmapgg.androideverydaypractice.widght_demo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.Bind;
import io.netopen.hotbitmapgg.androideverydaypractice.R;
import io.netopen.hotbitmapgg.androideverydaypractice.base.AbsBaseActivity;

/**
 * 安卓实现几种滑动的演示界面
 */
public class ScrollDemoActivity extends AbsBaseActivity
{

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public int getLayoutId()
    {

        return R.layout.activity_scroll_demo;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {

    }

    @Override
    public void initToolBar()
    {

        mToolbar.setNavigationIcon(R.drawable.back);
        mToolbar.setTitle("滑动实现演示");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {

                onBackPressed();
            }
        });
    }
}
