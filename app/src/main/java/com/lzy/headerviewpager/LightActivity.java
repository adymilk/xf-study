package com.lzy.headerviewpager;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;


/**
 * Created by Administrator on 2016/02/14.
 */
public class LightActivity extends Activity {
    private ImageButton imageButton;
    private Camera camera;

    private boolean isopen = false;//默认是关闭的
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        setContentView(R.layout.main_light);

       imageButton = (ImageButton)findViewById(R.id.light_switch);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                * 算法好神奇,'!'表示'非'。他是个逻辑判断。只有两种结果，要么是，要么非！'!isopen'就是说
                * 当不是isopen 的状态的时候就执行。
                * */
                if (!isopen){
                    imageButton.setBackgroundResource(R.mipmap.flashlight_02);
                    Toast.makeText(LightActivity.this,"手电筒已打开",Toast.LENGTH_SHORT).show();
                    camera = Camera.open();
                    Camera.Parameters params = camera.getParameters();
                    params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    camera.setParameters(params);
                    camera.startPreview(); // 开始亮灯
                    isopen = true;
                }else {
                    imageButton.setBackgroundResource(R.mipmap.flashlight_01);
                    Toast.makeText(LightActivity.this,"手电筒已关闭",Toast.LENGTH_SHORT).show();
                    camera.stopPreview(); // 关掉亮灯
                    camera.release(); // 关掉照相机
                    isopen = false;
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("当前状态是onStart");
    }



    @Override
    protected void onResume() {
        super.onResume();
        if (!isopen){
            imageButton.setBackgroundResource(R.mipmap.flashlight_02);
            Toast.makeText(LightActivity.this,"手电筒已打开",Toast.LENGTH_SHORT).show();
            camera = Camera.open();
            Camera.Parameters params = camera.getParameters();
            params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(params);
            camera.startPreview(); // 开始亮灯
            isopen = true;
        }

        System.out.println("当前状态是onResume");


    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("当前状态是onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("当前状态是onRestart");

    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("当前状态是onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isopen){
            camera.stopPreview();
            camera.release();
        }
        Toast.makeText(this,"手电筒已关闭",0).show();
        finish();
        System.out.println("当前状态是onDestroy");
    }
}
