package com.example.evdog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootBroadcastReceiver extends BroadcastReceiver {
	static final String action_boot="android.intent.action.BOOT_COMPLETED";
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if (intent.getAction().equals(action_boot)){
            Intent ootStartIntent=new Intent(context,MainActivity.class);
            ootStartIntent.setAction(Intent.ACTION_MAIN);  
            ootStartIntent.addCategory(Intent.CATEGORY_LAUNCHER);  
            ootStartIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
            ootStartIntent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED); 
            context.startActivity(ootStartIntent);
        }
	}

}
