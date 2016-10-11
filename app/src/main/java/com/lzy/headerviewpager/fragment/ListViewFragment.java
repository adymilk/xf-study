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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lzy.headerviewpager.R;
import com.lzy.headerviewpager.fragment.base.HeaderViewPagerFragment;
import com.lzy.headerviewpager.webview1;
import com.lzy.headerviewpager.webview2;


public class ListViewFragment extends HeaderViewPagerFragment {

    private ListView listView;

    public static ListViewFragment newInstance() {
        return new ListViewFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        listView = (ListView) inflater.inflate(R.layout.fragment_listview, container, false);
        listView.setAdapter(new MyAdapter(this.getContext()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "点击了条目" + position, Toast.LENGTH_SHORT).show();
                if (position%2==1){
//                    Uri uri = Uri.parse("http://geek.csdn.net/news/detail/64185");
                    startActivity(new Intent(getContext(), webview1.class));
                }else if (position%2==0){
//                    Uri uri = Uri.parse("http://geek.csdn.net/news/detail/68658");
//                    startActivity(new Intent(Intent.ACTION_VIEW,uri));
                    startActivity(new Intent(getContext(), webview2.class));

                }

            }
        });
        return listView;
    }

//    private void init(){
//        //初始化要显示图片数组集合
//        int[] icons = {R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
//        String[] titles = getResources().getStringArray(R.array.titles);
//    }

    @Override
    public View getScrollableView() {
        return listView;
    }

    public class MyAdapter extends BaseAdapter {
        int[] icons = {R.mipmap.listview_item_img1,
                R.mipmap.listview_item_img2,
                R.mipmap.listview_item_img3,
                R.mipmap.listview_item_img4,
                R.mipmap.listview_item_img5,
                R.mipmap.listview_item_img6,
                R.mipmap.listview_item_img7,
                R.mipmap.listview_item_img8,
                R.mipmap.listview_item_img9,
                R.mipmap.listview_item_img10,
                R.mipmap.listview_item_img11,
                R.mipmap.listview_item_img12,
                R.mipmap.listview_item_img13,
                R.mipmap.listview_item_img14,
                R.mipmap.listview_item_img15,
                R.mipmap.listview_item_img2,
                R.mipmap.listview_item_img3,
                R.mipmap.listview_item_img4,
                R.mipmap.listview_item_img5,
                R.mipmap.listview_item_img6,
                R.mipmap.listview_item_img7,
                R.mipmap.listview_item_img8,
                R.mipmap.listview_item_img9,
                R.mipmap.listview_item_img10,
                R.mipmap.listview_item_img11,
                R.mipmap.listview_item_img12,
                R.mipmap.listview_item_img13,
                R.mipmap.listview_item_img14,
                R.mipmap.listview_item_img15,
                };
        String[] titles = getResources().getStringArray(R.array.titles);

//        private ArrayList<String> strings;

        private Context context;
        public MyAdapter(Context context) {
            this.context = context;

            }

        @Override
        public int getCount() {
            return icons.length;
        }

        @Override
        public String getItem(int position) {
            return titles[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(getActivity(), android.R.layout.simple_list_item_1, null);
            }
//            TextView textView = (TextView) convertView;
//            textView.setGravity(Gravity.CENTER);
//            textView.setTextColor(Color.WHITE);
//            ViewGroup.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
//            textView.setLayoutParams(params);
//            textView.setText(getItem(position));
//            textView.setBackgroundColor(ColorUtil.generateBeautifulColor());
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.listview_item, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.lv_imageview);
            TextView textView = (TextView) view.findViewById(R.id.lv_textview);
            imageView.setImageResource(icons[position]);
            textView.setText(titles[position]);
            return view;
        }
    }
}
