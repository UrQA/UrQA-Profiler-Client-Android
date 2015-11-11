package com.urqa.profile.reader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.urqa.profile.writer.serialize.AllProfileInformationSerializer;
import com.urqa.profile.writer.serialize.BatterySerializer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by hwangheeseon on 15. 10. 6..
 */

public class BatteryInformationReader implements ProfileResourceReader{

    private Context context ;
    private int batteryPercentage;


    public BatteryInformationReader(Context _context){
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

    @Override
    public void prepareReadResource(){
        context.registerReceiver(BatteryInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    @Override
    public void finishReadResource(){
        try {
            context.unregisterReceiver(BatteryInfoReceiver);
        }catch(Exception e){

        }
    }

    @Override
    public void readAsJSON(JSONObject paramResourceObj) {

        JSONObject batteryData = new JSONObject();

        try {
            batteryData.put("battery", batteryPercentage);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            paramResourceObj.put("Battery", batteryData);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void readAsSerializableObject(Serializable paramResourceObj) {
        BatterySerializer batterySerializer = ((AllProfileInformationSerializer)paramResourceObj).batteryInformation;
        batterySerializer.batteryPercentage = batteryPercentage;
    }
}
