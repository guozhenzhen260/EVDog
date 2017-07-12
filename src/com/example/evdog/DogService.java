package com.example.evdog;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.ActivityManager;
import android.app.Service;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class DogService extends Service {
	ScheduledExecutorService timer = Executors.newScheduledThreadPool(1);
	private int allopen=1;//1表示一直打开应用,0表示关闭后不打开应用	
	private int watchfeed=0;//喂食,0时启动操作
	ActivityReceiver receiver;
	private int tempwatchfeed=0;//临时变量
	int restartNo=0,shutdownNo=0;
	int dogUart=0;
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	//8.创建activity的接收器广播，用来接收内容
	public class ActivityReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent)
		{
			// TODO Auto-generated method stub
			Bundle bundle=intent.getExtras();
			int isallopen=bundle.getInt("isallopen");
			int feed=bundle.getInt("watchfeed");
			Log.i("EV_DOG","看门狗isallopen="+isallopen+",feed="+feed);
			setAllopen(isallopen,feed);	
		}

	}
			
	public int getAllopen() {
		return allopen;
	}
	public void setAllopen(int isallopen,int feed) {
		this.allopen = isallopen;
		this.watchfeed+=feed;
		Log.i("EV_DOG","看门狗setAllopen="+allopen+",watchfeed="+watchfeed);
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i("EV_DOG","看门狗创建...");
		//9.注册接收器
		//localBroadreceiver = LocalBroadcastManager.getInstance(this);
		receiver=new ActivityReceiver();
		IntentFilter filter=new IntentFilter();
		filter.addAction("android.intent.action.watchdog");
		//localBroadreceiver.registerReceiver(receiver,filter);	
		registerReceiver(receiver,filter);			
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.i("EV_DOG","看门狗删除...");
		//解除注册接收器
		//localBroadreceiver.unregisterReceiver(receiver);
		unregisterReceiver(receiver);
		super.onDestroy();
	}

	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		Log.i("EV_DOG","看门狗启动...");
		timer.scheduleWithFixedDelay(new Runnable() { 
	        @Override 
	        public void run() { 
	        	if(allopen==1)
        		{
	        		if(tempwatchfeed!=watchfeed)
	    			{
	        			tempwatchfeed=watchfeed;
	    				dogUart=0;
	    				restartNo=0;
	        			shutdownNo=0;
	        			WriteSharedPreferences(restartNo,shutdownNo);	    				
	        			Log.i("EV_DOG","看门狗TaskDiff="+watchfeed+",dogUart="+dogUart);
	    			}
	    			else
	    			{
	    				dogUart++;
	    				Log.i("EV_DOG","看门狗TaskSame="+watchfeed+",dogUart="+dogUart);
	    			}
	        		//重置复位
	        		if(dogUart>2)
					{
	        			dogUart=0;
						Map<String,Integer> map=ReadSharedPreferences();
						
						if(map.containsKey("restartNo"))
						{
							restartNo=map.get("restartNo");								
						}
						if(map.containsKey("shutdownNo"))
						{
							shutdownNo=map.get("shutdownNo");							
						}
						//先重试3次看能不能重新启动程序
						if(restartNo<3)
						{
							restartNo++;
							WriteSharedPreferences(restartNo,shutdownNo);
							Log.i("EV_DOG","看门狗重启程序restartNo="+restartNo+",shutdownNo="+shutdownNo);
							String MY_PKG_NAME = "com.example.evconsole";
							Intent intent = new Intent();		        			 
		        			PackageManager packageManager = DogService.this.getPackageManager();
		        			intent = packageManager.getLaunchIntentForPackage(MY_PKG_NAME);
		        			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_CLEAR_TOP) ;
		        			DogService.this.startActivity(intent);
						}
						//不行，再重启机器3次看能不能使用
						else if(shutdownNo<3)
						{
							shutdownNo++;
							WriteSharedPreferences(restartNo,shutdownNo);
							Log.i("EV_DOG","看门狗重启机器restartNo="+restartNo+",shutdownNo="+shutdownNo);
							try {
								Runtime.getRuntime().exec(new String[]{"/system/bin/su","-c","reboot now"});
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
						}
						//弹出窗口提示故障
						else
						{
							Log.i("EV_DOG","看门狗确认系统故障...");
		        		}
						
					}
	        	}
	        	else
	        	{
	        		Log.i("EV_DOG","看门狗停止...");
        		}	        		        		        	
	        } 
	    },3*60,3*60,TimeUnit.SECONDS);       // timeTask 
	}
	
	//读取文件信息
	Map<String,Integer>  ReadSharedPreferences()
	{
		Map<String,Integer> map=new HashMap<String, Integer>();
		//文件是私有的
		SharedPreferences  user = getSharedPreferences("restart_info",0);
		//读取
		map.put("restartNo",user.getInt("restartNo",0));
		map.put("shutdownNo",user.getInt("shutdownNo",0));
		return map;
	}
	//写入文件信息
	void  WriteSharedPreferences(int  restartNo,int shutdownNo)
	{
		//文件是私有的
		SharedPreferences  user = getSharedPreferences("restart_info",0);
		//需要接口进行编辑
		SharedPreferences.Editor edit=user.edit();
		//设置
		edit.putInt("restartNo", restartNo);
		edit.putInt("shutdownNo" ,shutdownNo);
		//提交更新
		edit.commit();
	}
	
	
	

}
