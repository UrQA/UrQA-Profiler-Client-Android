package com.urqa.profile.reader;

import com.urqa.profile.writer.serialize.AllProfileInformationSerializer;
import com.urqa.profile.writer.serialize.CPUSerializer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;

/**
 * Created by hwangheeseon on 15. 10. 6..
 */

public class CPUInformationReader implements ProfileResourceReader{

    public CPUInformationReader(){

    }

    @Override
    public void prepareReadResource(){

    }

    @Override
    public void finishReadResource(){

    }

    @Override
    public void readAsJSON(JSONObject paramResourceObj) {

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

        try {
            paramResourceObj.put("CPU", cpuData);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void readAsSerializableObject(Serializable paramResourceObj) {

        CPUSerializer cpuSerializer = ((AllProfileInformationSerializer)paramResourceObj).cpuInformation;

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

        cpuSerializer.cpuInformation_user = Float.parseFloat(toks[1]);
        cpuSerializer.cpuInformation_system = Float.parseFloat(toks[3]);
        cpuSerializer.cpuInformation_idle = Float.parseFloat(toks[4]);
        cpuSerializer.cpuInformation_total = Float.parseFloat(toks[1]) + Float.parseFloat(toks[2]) + Float.parseFloat(toks[3]) + Float.parseFloat(toks[4]);

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
