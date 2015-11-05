package com.urqa.profile;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Vector;

/**
 * Created by hwangheeseon on 15. 10. 6..
 */
public class SendFileManager {

    private Vector<String> profilingfiles;
    private Context context;
    private String cachePath;

    private BufferedWriter currentFileWriter;
    private String currentFileName;
    private String currentFilePath;

    public static SendFileManager sendFileManager = null;

    private Battery battery = null;
    private CPU cpu = null;
    private Memory memory = null;




    private SendFileManager(Context _context){
        context = _context;
        cachePath = context.getCacheDir().getAbsolutePath();
        profilingfiles = new Vector<String>();
    }

    public static SendFileManager getInstance(Context _context){

       if(sendFileManager == null) {
           sendFileManager = new SendFileManager(_context);
           return sendFileManager;
       }
       else {
           return sendFileManager;
       }


    }

    public BufferedWriter newFile(){

        currentFileName = context.getApplicationContext().getApplicationInfo().toString() + "_" + (new Date(System.currentTimeMillis())).toString()+ "_Profileing.json";

        currentFilePath = cachePath + "/" + currentFileName;

        try {
            currentFileWriter = new BufferedWriter(new FileWriter(currentFilePath, true));
        } catch (Exception e){

        }
        return currentFileWriter;
    }

    public String getCachePath(){
        return cachePath;
    }




    public void writeProfile(){
        JSONObject profilingData = new JSONObject();

        //cpu
        try {
            profilingData.put("Battery", battery.getBatteryPercentage());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            profilingData.put("CPU", cpu.getCPUInformation());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            profilingData.put("Memory", memory.getMemoryInformation());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {

            if(currentFileWriter != null){
                currentFileWriter.write(profilingData.toString());
                currentFileWriter.flush();
                Log.e("profile.....", profilingData.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void startProfiling(){

        newFile();

        battery = new Battery(context);
        battery.start();

        cpu = new CPU();
        memory = new Memory();

    }


    public void stopProfiling() {


        if (currentFileWriter != null){
            try {
                currentFileWriter.flush();
                currentFileWriter.close();

            } catch (IOException e) {
                //e.printStackTrace();
            }

            currentFileWriter = null;
            profilingfiles.addElement(currentFilePath);
        }

        if(battery != null){
            battery.stop();
            battery = null;
        }

        if(cpu != null){
            cpu = null;
        }

        if(memory != null){
            memory = null;
        }
    }

}
