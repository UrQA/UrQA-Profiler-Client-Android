package com.urqa.profile;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hwangheeseon on 15. 10. 6..
 */
public class Battery {

    private Context context ;
    private int batteryPercentage;


    public Battery(Context _context){
        context = _context;
    }


    // the receiver to get battery information
    private BroadcastReceiver BatteryInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            String action = intent.getAction();
            if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {
                int level = intent.getIntExtra("level", 0);
                int scale = intent.getIntExtra("scale", 100);
                batteryPercentage = level * 100 / scale;
            }
        }
    };

    public void start(){
        context.registerReceiver(BatteryInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }


    public void stop(){
        try {
            context.unregisterReceiver(BatteryInfoReceiver);
        }catch(Exception e){

        }
    }

    public JSONObject getBatteryPercentage(){
        JSONObject batteryData = new JSONObject();
        try {
            batteryData.put("battery", batteryPercentage);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return batteryData;
    }



}
