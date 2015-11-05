package com.urqa.profile;

import android.os.Debug;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hwangheeseon on 15. 10. 6..
 */
public class Memory {
    public Memory(){

    }
    public void start(){

    }
    public void stop(){

    }

    public JSONObject getMemoryInformation(){

        Debug.MemoryInfo meminfo = new Debug.MemoryInfo();
        Debug.getMemoryInfo(meminfo);
        StringBuffer sb = new StringBuffer();

        JSONObject memoryData = new JSONObject();
        JSONObject privateDirty = new JSONObject();

        try {
            privateDirty.put("native", meminfo.nativePrivateDirty);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            privateDirty.put("dalvik", meminfo.dalvikPrivateDirty);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            privateDirty.put("other", meminfo.otherPrivateDirty);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            privateDirty.put("total", meminfo.getTotalPrivateDirty());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            memoryData.put("PrivateDirtyMemory", privateDirty);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject pss = new JSONObject();
        try {
            pss.put("native", meminfo.nativePss);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            pss.put("dalvik", meminfo.dalvikPss);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            pss.put("other", meminfo.otherPss);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            pss.put("total", meminfo.getTotalPss());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            memoryData.put("PssMemory", pss);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject heapData = new JSONObject();

        try {
            heapData.put("NativeHeapAllocatedSize", Debug.getNativeHeapAllocatedSize()/1024);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            heapData.put("NativeHeapFreeSize", Debug.getNativeHeapFreeSize()/1024);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            heapData.put("NativeHeapSize", Debug.getNativeHeapSize()/1024);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            memoryData.put("HeapMemory", heapData);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        sb.append(meminfo.nativePrivateDirty);
        sb.append("/");
        sb.append(meminfo.dalvikPrivateDirty);
        sb.append("/");
        sb.append(meminfo.otherPrivateDirty);
        sb.append("/");
        sb.append(meminfo.getTotalPrivateDirty());
        sb.append("/");
        sb.append(meminfo.nativePss);
        sb.append("/");
        sb.append(meminfo.dalvikPss);
        sb.append("/");
        sb.append(meminfo.otherPss);
        sb.append("/");
        sb.append(meminfo.getTotalPss());
        sb.append("/");
        sb.append(Debug.getNativeHeapAllocatedSize()/1024);
        sb.append("/");
        sb.append(Debug.getNativeHeapFreeSize()/1024);
        sb.append("/");
        sb.append(Debug.getNativeHeapSize()/1024);
        sb.append("/");
        Log.e("-memory", (" = " + sb.toString()));

        return memoryData;


    }
}
