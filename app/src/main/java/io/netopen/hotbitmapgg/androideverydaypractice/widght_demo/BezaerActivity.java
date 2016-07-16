package io.netopen.hotbitmapgg.androideverydaypractice.widght_demo;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.Bind;
import io.netopen.hotbitmapgg.androideverydaypractice.R;
import io.netopen.hotbitmapgg.androideverydaypractice.base.AbsBaseActivity;

public class BezaerActivity extends AbsBaseActivity
{


    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public int getLayoutId()
    {

        return R.layout.activity_widget_bezaer;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {

    }

    @Override
    public void initToolBar()
    {

        mToolbar.setTitle("贝塞尔线练习");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        if (item.getItemId() == android.R.id.home)
        {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
