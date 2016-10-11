package com.lzy.headerviewpager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * Created by Administrator on 2016/02/29.
 */
public class qqshuaping extends Activity {
    //初始化腾讯分享类
//    protected Tencent mTencent;
//    public static final String APP_ID ="22222";
    String userInputData;
    String data1;
    String data_sum;
    String default_Data_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qq_shuaping);
//透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);


//        mTencent = Tencent.createInstance(APP_ID, this.getApplicationContext());
        ImageView imageView = (ImageView) findViewById(R.id.img_menu);
        final EditText editText_data = (EditText) findViewById(R.id.et_keycode);
        Button start_shuaping = (Button) findViewById(R.id.btn_chat);
        start_shuaping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInputData = editText_data.getText().toString();
                //将输入法隐藏，editText_data 代表输入框
                InputMethodManager imm =(InputMethodManager)getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText_data.getWindowToken(), 0);
                //判断数据是否为空，用到的方法TextUtils.isEmpty()
                if (TextUtils.isEmpty(userInputData)) {
                    Toast.makeText(qqshuaping.this, "请输入想要刷屏的内容", Toast.LENGTH_SHORT).show();
                } else {
                    String default_Data1="看\n";
                    String default_Data2="我\n";
                    String default_Data3="定\n";
                    String default_Data4="海\n";
                    String default_Data5="神\n";
                    String default_Data6="针\n";
                    data1 = userInputData+"\n" +default_Data1+default_Data2+default_Data3+default_Data4+default_Data5+default_Data6;
                    default_Data_end = "本\n刷\n屏\n神\n器\n由\n小\n枫\n制\n作\n,\n请\n勿\n用\n于\n非\n法\n途\n径\n";


                    for (int i = 0; i < 500; i++) {

                        data_sum +=data1;

                    }
                    shareQQ(qqshuaping.this);

                }

            }
        });


        //监听左上角返回动作
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    public void shareQQ(Context mContext) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, data_sum +"\n\n\n\n\n\n"+ default_Data_end);
        sendIntent.setType("text/plain");
        //sendIntent.setPackage("com.tencent.mobileqq");
        // List<ResolveInfo> list= getShareTargets(mContext);
        try {
            sendIntent.setClassName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.JumpActivity");
            Intent chooserIntent = Intent.createChooser(sendIntent, "选择分享途径");
            if (chooserIntent == null) {
                return;
            }
            mContext.startActivity(chooserIntent);
        } catch (Exception e) {
            mContext.startActivity(sendIntent);
        }


    }


}
