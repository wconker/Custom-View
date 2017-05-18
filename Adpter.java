package com.android.magic.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.magic.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kanghui on 2017/5/18.
 */

public class Adpter extends BaseAdapter {

    private List<String> list;
    private Context context;

    public Adpter(Context context) {
        this.context = context;
        list = new ArrayList<>();
        list.add("首页");
        list.add("新闻");
        list.add("动漫");

        list.add("科技");

        list.add("设置");

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter, null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) view.findViewById(R.id.tv);
            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textView.setText(list.get(i).toString());

        return view;
    }

    class ViewHolder {

        private TextView textView;


    }
}
