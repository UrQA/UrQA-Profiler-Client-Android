package com.urqa.profile.reader;

import com.urqa.profile.writer.serialize.AllProfileInformationSerializer;
import com.urqa.profile.writer.serialize.CurrrentThreadSerializer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by hwangheeseon on 15. 11. 12..
 */
public class CurrentThreadInformationReader implements ProfileResourceReader{

    @Override
    public void prepareReadResource() {

    }

    @Override
    public void finishReadResource() {

    }

    @Override
    public void readAsJSON(JSONObject paramResourceObj) {
        JSONObject currentThreadData = new JSONObject();
        try {
            StackTraceElement[] traceElements = Thread.currentThread().getStackTrace();
            StringBuffer sb = new StringBuffer();
            for(StackTraceElement list : traceElements){
                sb.append("\n" + list.getClassName() + " : " + list.getMethodName() + " : " + list.getLineNumber());
            }
            currentThreadData.put("currentThread", sb.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            paramResourceObj.put("CurrentThread", currentThreadData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readAsSerializableObject(Serializable paramResourceObj) {
        CurrrentThreadSerializer currrentThreadSerializer = ((AllProfileInformationSerializer)paramResourceObj).currrentThreadInformation;

        StackTraceElement[] traceElements = Thread.currentThread().getStackTrace();
        StringBuffer sb = new StringBuffer();
        for(StackTraceElement list : traceElements){
            sb.append("\n" + list.getClassName() + " : " + list.getMethodName() + " : " + list.getLineNumber());
        }
        currrentThreadSerializer.currentThreadInformation = sb.toString();
    }
}
