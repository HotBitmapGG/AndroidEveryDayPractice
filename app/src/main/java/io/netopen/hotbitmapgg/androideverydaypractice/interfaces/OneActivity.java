package io.netopen.hotbitmapgg.androideverydaypractice.interfaces;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import io.netopen.hotbitmapgg.androideverydaypractice.R;

/**
 * Created by hcc on 2016/11/16 15:03
 * 100332338@qq.com
 */

public class OneActivity extends AppCompatActivity
{

    @Bind(R.id.btn)
    Button mButton;

    //把这个接口当做OneActivity的一个变量
    private OnTextChange onTextChange;

    //重写set方法
    public void setOnTextChange(OnTextChange onTextChange)
    {

        this.onTextChange = onTextChange;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);


        mButton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                //点击这个按钮回调第二个activity里的文字改变方法
                if(onTextChange != null)
                    onTextChange.textChange("接口回调改变的内容");

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        super.onActivityResult(requestCode, resultCode, data);
    }
}
