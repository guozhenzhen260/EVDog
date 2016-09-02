package com.example.evdog;

import java.io.File;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToolClass.SetDir();	//设置根目录
		ToolClass.Log(ToolClass.INFO,"EV_DOG","APP<<[看门狗唤醒...]路径:"+ToolClass.getEV_DIR()+File.separator+"logs","dog.txt");			
		//==========
		//Dog服务相关
		//==========
		//启动服务
		startService(new Intent(this,DogService.class));
		
		moveTaskToBack(true);//隐藏当前activity页面
    }
   
}
