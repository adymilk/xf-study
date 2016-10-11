package com.lzy.headerviewpager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class QQchat extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.qqchat);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        ImageView imageView = (ImageView) findViewById(R.id.img_menu);
        //监听左上角返回动作
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button btn = (Button) findViewById(R.id.btn_chat);
        //拿到控件，并设置监听
        final EditText editText = (EditText) findViewById(R.id.et_keycode);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //拿到EditText用户输入的数据
                String data = editText.getText().toString();
                //将输入法隐藏，editText 代表输入框
                InputMethodManager imm =(InputMethodManager)getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                if (TextUtils.isEmpty(data)) {
                    //判断数据是否为空，用到的方法TextUtils.isEmpty()
                    Toast.makeText(QQchat.this, "请输入QQ号码!", Toast.LENGTH_SHORT).show();
                } else {
                    //开启QQ强制聊天代码
                    String url = "mqqwpa://im/chat?chat_type=wpa&uin=" + data;
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                }

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
