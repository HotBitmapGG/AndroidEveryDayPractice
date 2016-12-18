package io.netopen.hotbitmapgg.androideverydaypractice.interfaces;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.Bind;
import io.netopen.hotbitmapgg.androideverydaypractice.R;

/**
 * Created by hcc on 2016/11/16 15:08
 * 100332338@qq.com
 */

public class TwoActivity extends AppCompatActivity implements OnTextChange
{

    @Bind(R.id.text_change)
    TextView mTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        //第二个界面实现文本改变接口 回调textchange方法
        new OneActivity().setOnTextChange(this);
    }


    /**
     * 这个就是接口回调方法
     *
     * @param text
     */
    @Override
    public void textChange(String text)
    {

    }
}
