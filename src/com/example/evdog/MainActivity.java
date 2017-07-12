package com.example.evdog;

import java.io.File;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToolClass.SetDir();	//设置根目录
        Log.i("EV_DOG","APP<<[看门狗唤醒...]路径:"+ToolClass.getEV_DIR()+File.separator+"logs");			
		//==========
		//Dog服务相关
		//==========
		//启动服务
		startService(new Intent(this,DogService.class)); 
		//方法一、
//		moveTaskToBack(true);//隐藏当前activity页面
		//方法二、
//		Intent home = new Intent(Intent.ACTION_MAIN);  
//		home.addCategory(Intent.CATEGORY_HOME);   
//		startActivity(home);   
		//方法三、在manifest中设置不显示主题 
//		<activity android:name="xxx" android:theme="@android:style/Theme.NoDisplay"> </activity>
    }
   
}
