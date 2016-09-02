package com.example.evdog;


import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.ActivityManager;
import android.app.Service;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;

public class DogService extends Service {
	ScheduledExecutorService timer = Executors.newScheduledThreadPool(1);
	private int allopen=1;//1表示一直打开应用,0表示关闭后不打开应用	
	private int watchfeed=0;//喂食,0时启动操作
	ActivityReceiver receiver;
	private int tempwatchfeed=0;//临时变量
	private int dogUart=0;
	
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
			//ToolClass.Log(ToolClass.INFO,"EV_DOG","看门狗isallopen="+isallopen+",feed="+feed,"dog.txt");
			setAllopen(isallopen,feed);	
		}

	}
			
	public int getAllopen() {
		return allopen;
	}
	public void setAllopen(int isallopen,int feed) {
		this.allopen = isallopen;
		this.watchfeed+=feed;
		ToolClass.Log(ToolClass.INFO,"EV_DOG","看门狗setAllopen="+allopen+",watchfeed="+watchfeed,"dog.txt");
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		ToolClass.Log(ToolClass.INFO,"EV_DOG","看门狗创建...","dog.txt");
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
		ToolClass.Log(ToolClass.INFO,"EV_SERVER","看门狗删除...","dog.txt");
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
		ToolClass.Log(ToolClass.INFO,"EV_DOG","看门狗启动...","dog.txt");
		timer.scheduleWithFixedDelay(new Runnable() { 
	        @Override 
	        public void run() { 	        	
	        	//if(allopen==1)
        		{
	        		if(tempwatchfeed!=watchfeed)
	    			{
	        			tempwatchfeed=watchfeed;
	    				dogUart=0;
	    				ToolClass.Log(ToolClass.INFO,"EV_DOG","看门狗TaskDiff="+watchfeed+",dogUart="+dogUart,"dog.txt");
	    			}
	    			else
	    			{
	    				dogUart++;
	    				ToolClass.Log(ToolClass.INFO,"EV_DOG","看门狗TaskSame="+watchfeed+",dogUart="+dogUart,"dog.txt");
	    			}
	        		//重置复位
	        		if(dogUart>5)
					{
	        			ToolClass.Log(ToolClass.INFO,"EV_DOG","看门狗重启程序...","dog.txt");
						dogUart=0;
					}
	        	}
//	        	else
//	        	{
//	        		ToolClass.Log(ToolClass.INFO,"EV_DOG","看门狗停止...","dog.txt");
//				}	        		        		        	
	        } 
	    },15,15,TimeUnit.SECONDS);       // timeTask 
	}
	
	

}
