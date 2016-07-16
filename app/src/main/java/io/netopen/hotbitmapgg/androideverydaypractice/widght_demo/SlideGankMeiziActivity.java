package io.netopen.hotbitmapgg.androideverydaypractice.widght_demo;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import io.netopen.hotbitmapgg.androideverydaypractice.R;
import io.netopen.hotbitmapgg.androideverydaypractice.base.AbsBaseActivity;
import io.netopen.hotbitmapgg.androideverydaypractice.model.GankMeiziInfo;
import io.netopen.hotbitmapgg.androideverydaypractice.model.GankMeiziResult;
import io.netopen.hotbitmapgg.androideverydaypractice.network.RetrofitHelper;
import io.netopen.hotbitmapgg.androideverydaypractice.widget.slidecard.CardDataItem;
import io.netopen.hotbitmapgg.androideverydaypractice.widget.slidecard.CardSlidePanel;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class SlideGankMeiziActivity extends AbsBaseActivity
{

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.image_slide_panel)
    CardSlidePanel mCardSlidePanel;

    @Bind(R.id.root_layout)
    RelativeLayout mRootLayout;

    private int pageNum = 100;

    private int page = 1;

    //滑动的卡片item集合
    private List<CardDataItem> dataList = new ArrayList<>();

    //Gank妹子集合
    private List<GankMeiziInfo> meizis = new ArrayList<>();

    @Override
    public int getLayoutId()
    {

        return R.layout.activity_slide_card;
    }

    @Override
    public void initViews(Bundle savedInstanceState)
    {

        getGankMeizi();

        mCardSlidePanel.setCardSwitchListener(new CardSlidePanel.CardSwitchListener()
        {

            @Override
            public void onShow(int index)
            {

            }

            @Override
            public void onCardVanish(int index, int type)
            {

            }

            @Override
            public void onItemClick(View cardImageView, int index)
            {

            }
        });
    }

    private void getGankMeizi()
    {

        RetrofitHelper.getGankMeiziApi().getGankMeizi(pageNum, page)
                .doOnSubscribe(new Action0()
                {

                    @Override
                    public void call()
                    {

                        showProgress();
                    }
                })
                .map(new Func1<GankMeiziResult,List<GankMeiziInfo>>()
                {

                    @Override
                    public List<GankMeiziInfo> call(GankMeiziResult gankMeiziResult)
                    {

                        return gankMeiziResult.gankMeizis;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<GankMeiziInfo>>()
                {

                    @Override
                    public void call(List<GankMeiziInfo> gankMeiziInfos)
                    {
                        meizis.addAll(gankMeiziInfos);
                        finishTask();
                    }
                }, new Action1<Throwable>()
                {

                    @Override
                    public void call(Throwable throwable)
                    {

                        showError();
                    }
                });
    }

    /**
     * 根据返回的妹子图片
     * 初始化卡片数据
     */
    private void finishTask()
    {

        hideProgress();
        CardDataItem dataItem;
        for (int i = 0; i < meizis.size(); i++)
        {
            dataItem = new CardDataItem();
            dataItem.detailed = meizis.get(i).createdAt;
            dataItem.imgUrl = meizis.get(i).url;
            dataList.add(dataItem);
        }

        mCardSlidePanel.fillData(dataList);
    }

    @Override
    public void initToolBar()
    {

        mToolbar.setTitle("仿探探滑动卡片效果");
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

    public void showProgress()
    {

        mRootLayout.setVisibility(View.GONE);
    }

    public void hideProgress()
    {

        mRootLayout.setVisibility(View.VISIBLE);
    }

    public void showError()
    {

        mRootLayout.setVisibility(View.GONE);
    }
}
