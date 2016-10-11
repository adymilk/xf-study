package com.lzy.headerviewpager.fragment;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lzy.headerviewpager.LightActivity;
import com.lzy.headerviewpager.LoginActivity;
import com.lzy.headerviewpager.R;
import com.lzy.headerviewpager.fragment.base.HeaderViewPagerFragment;
import com.lzy.headerviewpager.qqshuaping;
import com.lzy.headerviewpager.webview_CSDN;
import com.lzy.headerviewpager.webview_download;
import com.lzy.headerviewpager.webview_updata;


public class ScrollViewFragment extends HeaderViewPagerFragment {

    private View scrollView;

    public static ScrollViewFragment newInstance() {
        return new ScrollViewFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        scrollView = inflater.inflate(R.layout.fragment_scrollview, container, false);
        LinearLayout views = (LinearLayout) scrollView.findViewById(R.id.container);
//        for (int i = 0; i < 10; i++) {
//            View view = new View(getActivity());
//            view.setBackgroundColor(ColorUtil.generateBeautifulColor());
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
//            view.setLayoutParams(params);
//            views.addView(view);

//        }

        View view = inflater.inflate(R.layout.me_layout,null);

        //登录注册

        LinearLayout login  = (LinearLayout) view.findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });
        ImageView hongbao = (ImageView) view.findViewById(R.id.imageView1);
        ImageView QQchat = (ImageView) view.findViewById(R.id.imageView2);
        ImageView light = (ImageView) view.findViewById(R.id.imageView3);
        ImageView shuaping = (ImageView) view.findViewById(R.id.imageView4);
        LinearLayout join_group = (LinearLayout) view.findViewById(R.id.join_group);
        LinearLayout my_csdn = (LinearLayout) view.findViewById(R.id.my_csdn);
        LinearLayout download = (LinearLayout) view.findViewById(R.id.download);
        LinearLayout about = (LinearLayout) view.findViewById(R.id.about);
        LinearLayout zhifubao = (LinearLayout) view.findViewById(R.id.zhifubao);
        LinearLayout share = (LinearLayout) view.findViewById(R.id.share);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(),"请加入官方群查看",Toast.LENGTH_LONG).show();
                aboutDialog();
            }
        });
        zhifubao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Uri uri =Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=924114103");
//                startActivity(new Intent(Intent.ACTION_VIEW,uri));
//                Toast.makeText(getActivity(),"恭喜发财，红包拿来！",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getContext(), webview_updata.class));
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开启本地QQ客户端
                Intent intent = new Intent();
                ComponentName cmp = new ComponentName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.SplashActivity");
                intent.setAction(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setComponent(cmp);
                startActivityForResult(intent, 0);
                Toast.makeText(getActivity(),"用qq把软件发给你的朋友吧",Toast.LENGTH_LONG).show();
            }
        });
        //源码下载
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Uri uri = Uri.parse("http://pan.baidu.com/s/1geINrtp");
//                startActivity(new Intent(Intent.ACTION_VIEW,uri));
                startActivity(new Intent(getContext(),webview_download.class));
            }
        });
        //我的CSDN
        my_csdn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Uri uri = Uri.parse("http://blog.csdn.net/qq_28590879");
//                startActivity(new Intent(Intent.ACTION_VIEW,uri));
                startActivity(new Intent(getContext(),webview_CSDN.class));
            }
        });
        //加入QQ群
        join_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joinQQGroup("0D6Myy3GI3004bJH4I7mpDHtmj5-zI2Z");
            }
        });

        hongbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), qianghongbao.MainActivity.class));
            }
        });
        QQchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), com.lzy.headerviewpager.QQchat.class));
            }
        });
        light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LightActivity.class));
            }
        });
        shuaping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), qqshuaping.class));
            }
        });

        return view;
    }

    @Override
    public View getScrollableView() {
        return scrollView;
    }

    /****************
     *
     * 发起添加群流程。群号：软件及游戏破解交流群(390453986) 的 key 为： 0D6Myy3GI3004bJH4I7mpDHtmj5-zI2Z
     * 调用 joinQQGroup(0D6Myy3GI3004bJH4I7mpDHtmj5-zI2Z) 即可发起手Q客户端申请加群 软件及游戏破解交流群(390453986)
     *
     * @param key 由官网生成的key
     * @return 返回true表示呼起手Q成功，返回fals表示呼起失败
     ******************/
    public boolean joinQQGroup(String key) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
        // 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，返回手Q主界面，不设置，按返回会返回到呼起产品界面    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            startActivity(intent);
            return true;
        } catch (Exception e) {
            // 未安装手Q或安装的版本不支持
            return false;
        }



    }




    //弹出关于软件对话框方法
    private void aboutDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("关于");
        final WebView webView = new WebView(getActivity());
        String about = "Google(中国)应用程序开发.</p>" +
                "<p>写程序就像是打lol,再陌生的英雄玩多了自然技术就很吊！五杀还算神马，温故而知新,你就是王者！。" +
                "<p>制作人：小枫</p>" +
                "<p>完成时间：2016年04月26日</p>" +
                "<p>交流建议 <a href='mqqwpa://im/chat?chat_type=wpa&uin=924114103'>直接点我QQ:924114103</a></p>";
        webView.setBackgroundColor(Color.TRANSPARENT);
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        webView.loadData(about, "text/html; charset=UTF-8", null);//这种写法可以正确解码
        //webView.loadData(about, "text/html", "UTF-8");
        alert.setView(webView, 32, 0, 32, 0);
        alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        alert.show();
    }




}
