package com.urqa.profile;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by hwangheeseon on 15. 10. 6..
 */
public class CPU {

    public CPU(){

    }

    public void start(){

    }

    public void stop(){

    }

    //get CPU information
    public JSONObject getCPUInformation(){

        RandomAccessFile reader = null;
        try {
            reader = new RandomAccessFile("/proc/stat", "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String load = null;
        try {
            load = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] toks = load.split("[ ]+");

        float userMode1 = Float.parseFloat(toks[1]);
        float niceMode1 = Float.parseFloat(toks[2]);
        float systemMode1 = Float.parseFloat(toks[3]);
        float idleMode1 = Float.parseFloat(toks[4]);

        float total = userMode1 + niceMode1 + systemMode1 + idleMode1;
        float user = userMode1 ;
        float system = systemMode1 ;

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject cpuData = new JSONObject();
        try {
            cpuData.put("idle", idleMode1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            cpuData.put("user", user);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            cpuData.put("system", system);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            cpuData.put("total", total);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //debugging
        final StringBuffer sb1 = new StringBuffer();
        sb1.append(total);
        sb1.append("/");
        sb1.append(user);
        sb1.append("/");
        sb1.append(system);
        sb1.append("/");
        sb1.append(idleMode1);
        Log.e("-cpu", (" = " + sb1.toString()));

        try {
            reader.close();
        }catch(Exception e){

        }

        return cpuData;

    }



}
