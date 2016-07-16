package io.netopen.hotbitmapgg.androideverydaypractice.fragment;

import android.content.Intent;

import butterknife.OnClick;
import io.netopen.hotbitmapgg.androideverydaypractice.R;
import io.netopen.hotbitmapgg.androideverydaypractice.base.LazyFragment;
import io.netopen.hotbitmapgg.androideverydaypractice.recycleview.RecycleViewDemoActivity;
import io.netopen.hotbitmapgg.androideverydaypractice.widght_demo.ScrollDemoActivity;


public class MdWidgetFragment extends LazyFragment
{

    public static MdWidgetFragment newInstance()
    {

        return new MdWidgetFragment();
    }

    @Override
    public int getLayoutId()
    {

        return R.layout.fragment_md;
    }

    @Override
    public void initViews()
    {

    }

    @OnClick(R.id.md_btn_1)
    void startRecycleViewDemo()
    {

        startActivity(new Intent(getActivity(), RecycleViewDemoActivity.class));
    }

    @OnClick(R.id.md_btn_2)
    void startScrollDemo()
    {

        startActivity(new Intent(getActivity(), ScrollDemoActivity.class));
    }
}
