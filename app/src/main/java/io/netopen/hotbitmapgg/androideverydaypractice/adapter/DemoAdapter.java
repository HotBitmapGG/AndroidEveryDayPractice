package io.netopen.hotbitmapgg.androideverydaypractice.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import io.netopen.hotbitmapgg.androideverydaypractice.R;

/**
 * Created by hcc on 2016/11/25 22:24
 * 100332338@qq.com
 */

public class DemoAdapter extends BaseAdapter
{

    Context context;

    @Override
    public int getCount()
    {

        return 0;
    }

    @Override
    public Object getItem(int position)
    {

        return null;
    }

    @Override
    public long getItemId(int position)
    {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
         ViewHolder viewHolder = null;
         if(convertView == null)      {
             convertView = View.inflate(context, R.layout.item_test_1, null);
             viewHolder = new ViewHolder();
             viewHolder.mTextView= (TextView) convertView.findViewById(R.id.tab_layout);

             convertView.setTag(viewHolder);


         }
        else
         {
             viewHolder = (ViewHolder) convertView.getTag();
         }


        return convertView;
    }

    private static class ViewHolder
    {
        public TextView mTextView;

        public ImageView mImageView;
    }
}
