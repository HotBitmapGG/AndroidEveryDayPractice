package io.netopen.hotbitmapgg.androideverydaypractice.fragment;

import android.content.Intent;

import butterknife.Bind;
import butterknife.OnClick;
import io.netopen.hotbitmapgg.androideverydaypractice.R;
import io.netopen.hotbitmapgg.androideverydaypractice.base.LazyFragment;
import io.netopen.hotbitmapgg.androideverydaypractice.rxjava.RxJavaSampleActivity;
import io.netopen.hotbitmapgg.androideverydaypractice.widget.other.ItemArrowCardView;

public class RxjavaDemoFragment extends LazyFragment
{

    @Bind(R.id.btn_2)
    ItemArrowCardView mRxjavaSample;



    public static RxjavaDemoFragment newInstance()
    {

        return new RxjavaDemoFragment();
    }

    @Override
    public int getLayoutId()
    {

        return R.layout.fragment_rxjava_demo;
    }

    @Override
    public void initViews()
    {

    }


    @OnClick(R.id.btn_2)
    void startRxJavaSample()
    {

        startActivity(new Intent(getActivity(), RxJavaSampleActivity.class));
    }
}
