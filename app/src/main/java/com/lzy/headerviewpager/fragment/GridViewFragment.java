package com.lzy.headerviewpager.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.headerviewpager.C_Activity;
import com.lzy.headerviewpager.HTML_Activity;
import com.lzy.headerviewpager.Java_Activity;
import com.lzy.headerviewpager.Music_Activity;
import com.lzy.headerviewpager.PHP_Activity;
import com.lzy.headerviewpager.R;
import com.lzy.headerviewpager.fragment.base.HeaderViewPagerFragment;
import com.lzy.headerviewpager.webview_shequ;
import com.lzy.headerviewpager.webview_more;
import com.lzy.headerviewpager.webview_video;

import java.util.ArrayList;

public class GridViewFragment extends HeaderViewPagerFragment {

    private GridView gridView;

    public static GridViewFragment newInstance() {
        return new GridViewFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        gridView = (GridView) inflater.inflate(R.layout.fragment_gridview, container, false);
        gridView.setAdapter(new MyAdapter(this.getContext()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    startActivity(new Intent(getContext(), Java_Activity.class));
                }
                if (position==1){
                    startActivity(new Intent(getContext(), PHP_Activity.class));
                }
                if (position==2){
                    startActivity(new Intent(getContext(), HTML_Activity.class));
                }
                if (position==3){
                    startActivity(new Intent(getContext(), C_Activity.class));
                }
                if (position==4){
                    startActivity(new Intent(getContext(), Music_Activity.class));
                }
                if (position==5){
                    startActivity(new Intent(getContext(),webview_video.class));
                }
                if (position==6){
                    startActivity(new Intent(getContext(),webview_shequ.class));
                }
                if (position==7){
                    startActivity(new Intent(getContext(),webview_more.class));
                }
            }
        });
        return gridView;
    }

    @Override
    public View getScrollableView() {
        return gridView;
    }

    public class MyAdapter extends BaseAdapter {
        private String[] names = {"Java", "PHP", "HTML", "C++", "音乐", "视频", "社区", "更多"};
        private int[] images = {R.mipmap.java, R.mipmap.php, R.mipmap.html, R.mipmap.c, R.mipmap.music, R.mipmap.video, R.mipmap.keep, R.mipmap.title8_bg};
        private ArrayList<String> strings;
        private Context context;
        public MyAdapter(Context context) {
            this.context = context;

        }

        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public String getItem(int position) {
            return names[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
//            if (convertView == null) {
//                convertView = View.inflate(getActivity(), android.R.layout.simple_list_item_1, null);
//            }
//            ImageView imageView = (ImageView) convertView;
//            TextView textView = (TextView) convertView;
//            textView.setGravity(Gravity.CENTER);
//            textView.setTextColor(Color.WHITE);
//            ViewGroup.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
//            textView.setLayoutParams(params);
//            textView.setText(getItem(position));
//            textView.setBackgroundColor(ColorUtil.generateBeautifulColor());
//            return convertView;
            if (convertView == null) {
                convertView = View.inflate(getActivity(), R.layout.gridview_item, null);
            }
//            LayoutInflater inflater = LayoutInflater.from(context);
//            View view = inflater.inflate(R.layout.gridview_item, null);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_img);
            TextView textView = (TextView) convertView.findViewById(R.id.tv_name);
            imageView.setImageResource(images[position]);
            textView.setText(names[position]);
            return convertView;
        }
    }
}
