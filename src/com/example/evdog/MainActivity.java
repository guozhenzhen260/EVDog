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
        ToolClass.SetDir();	//���ø�Ŀ¼
        Log.i("EV_DOG","APP<<[���Ź�����...]·��:"+ToolClass.getEV_DIR()+File.separator+"logs");			
		//==========
		//Dog�������
		//==========
		//��������
		startService(new Intent(this,DogService.class)); 
		//����һ��
//		moveTaskToBack(true);//���ص�ǰactivityҳ��
		//��������
//		Intent home = new Intent(Intent.ACTION_MAIN);  
//		home.addCategory(Intent.CATEGORY_HOME);   
//		startActivity(home);   
		//����������manifest�����ò���ʾ���� 
//		<activity android:name="xxx" android:theme="@android:style/Theme.NoDisplay"> </activity>
    }
   
}
