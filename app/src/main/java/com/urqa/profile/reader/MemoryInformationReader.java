package com.urqa.profile.reader;

import android.os.Debug;

import com.urqa.profile.AllProfileInformationSerializer;
import com.urqa.profile.writer.serialize.MemorySerializer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by hwangheeseon on 15. 10. 6..
 */
public class MemoryInformationReader implements ProfileResourceReader{

    public MemoryInformationReader() {

    }

    @Override
    public void prepareReadResource() {

    }

    @Override
    public void finishReadResource() {

    }

    @Override
    public void readAsJSON(JSONObject paramResourceObj) {


        Debug.MemoryInfo meminfo = new Debug.MemoryInfo();
        Debug.getMemoryInfo(meminfo);


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
            heapData.put("NativeHeapAllocatedSize", Debug.getNativeHeapAllocatedSize() / 1024);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            heapData.put("NativeHeapFreeSize", Debug.getNativeHeapFreeSize() / 1024);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            heapData.put("NativeHeapSize", Debug.getNativeHeapSize() / 1024);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            memoryData.put("HeapMemory", heapData);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            paramResourceObj.put("Memory", memoryData);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void readAsSerializableObject(Serializable paramResourceObj) {

        MemorySerializer memorySerializer = ((AllProfileInformationSerializer)paramResourceObj).memoryInformation;

        Debug.MemoryInfo meminfo = new Debug.MemoryInfo();
        Debug.getMemoryInfo(meminfo);

        memorySerializer.memoryInformation_privateDirty_native = meminfo.nativePrivateDirty;
        memorySerializer.memoryInformation_privateDirty_dalvik = meminfo.dalvikPrivateDirty;
        memorySerializer.memoryInformation_privateDirty_other = meminfo.otherPrivateDirty;
        memorySerializer.memoryInformation_privateDirty_total = meminfo.getTotalPrivateDirty();

        memorySerializer.memoryInformation_PssMemory_native = meminfo.nativePss;
        memorySerializer.memoryInformation_PssMemory_dalvik = meminfo.dalvikPss;
        memorySerializer.memoryInformation_PssMemory_other = meminfo.otherPss;
        memorySerializer.memoryInformation_PssMemory_total = meminfo.getTotalPss();
        memorySerializer.memoryInformation_HeapMemory_nativeHeapAllocatedSize = Debug.getNativeHeapAllocatedSize() / 1024;
        memorySerializer.memoryInformation_HeapMemory_nativeHeapFreeSize = Debug.getNativeHeapFreeSize() / 1024;
        memorySerializer.memoryInformation_HeapMemory_nativeHeapSize = Debug.getNativeHeapSize() / 1024;


    }
}