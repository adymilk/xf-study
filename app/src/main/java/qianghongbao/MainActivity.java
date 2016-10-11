package qianghongbao;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.headerviewpager.R;


public class MainActivity extends Activity
{
	String str2;
	String data;
//	private boolean jia = false;
	private android.support.v7.app.AlertDialog alertDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hongbao);
		//透明状态栏
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		//透明导航栏
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

//		final TextView textView_jia = (TextView) findViewById(R.id.tv_jia);
		ImageView back = (ImageView) findViewById(R.id.imgv_back);
		TextView how_to_use = (TextView) findViewById(R.id.how_to_use);
		final EditText userInputData = (EditText) findViewById(R.id.trueText);
		final Button copytext = (Button) findViewById(R.id.copyText);
		final ClipboardManager clip = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);




		//左上角返回上一级监听
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				startActivity(new Intent(MainActivity.this,PagerHeaderActivity.class));
				finish();
			}
		});
		//生成假红包按钮监听
		copytext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				data = userInputData.getText().toString();
				//将输入法隐藏，userInputData 代表输入框
				InputMethodManager imm =(InputMethodManager)getSystemService(
						Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(userInputData.getWindowToken(), 0);
				if (TextUtils.isEmpty(data)){
					Toast.makeText(MainActivity.this,"请输入红包口令",Toast.LENGTH_SHORT).show();
				}else{
					String str1 = "\024\n";
					str2 = data +str1;

					clip.setText(str2); // 复制
					AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
					builder.setTitle("成功！");
					builder.setMessage("假红包口令已经复制到您的剪切板,快去发个口令红包试试吧！");
					builder.setPositiveButton("走起GO！", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//开启本地QQ客户端
							Intent intent = new Intent();
							ComponentName cmp = new ComponentName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.SplashActivity");
							intent.setAction(Intent.ACTION_MAIN);
							intent.addCategory(Intent.CATEGORY_LAUNCHER);
							intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							intent.setComponent(cmp);
							startActivityForResult(intent, 0);
							Toast.makeText(MainActivity.this, "去发口令红包处粘贴假口令", Toast.LENGTH_LONG).show();
						}
					});
					builder.show();
				}
			}
		});
		//如何使用
		how_to_use.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setTitle("如何使用？");
				builder.setMessage("1、假红包(仅对安卓系统有效)：仅对口令红包有效,点击生成口令后去qq发红包-口令红包-粘贴口令即可\n" +
						"2、自动抢红包服务：开启服务后可以自动领取QQ和微信的红包;(有锁屏密码状态除外)\n" );
				builder.setPositiveButton("好的", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				builder.show();

			}
		});


		findViewById(R.id.start_button).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				open();
			}
		});
	}

	private void open()
	{
		try
		{
			Intent intent = new Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS);
			startActivity(intent);
			Toast.makeText(this, "找到“小枫助手”，然后开启服务即可", Toast.LENGTH_LONG).show();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}




}
