package com.urqa.profile.writer.json;

import android.util.Log;

import com.urqa.profile.reader.ProfileResourceReader;
import com.urqa.profile.writer.ProfileDataFile;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by hwangheeseon on 15. 11. 11..
 */
public class JSONProfileDataFile implements ProfileDataFile {

    protected BufferedWriter currentFileWriter = null;
    protected String profilePath;
    protected Vector<ProfileResourceReader> resourceReaders;

    public JSONProfileDataFile(String paramFullFilePath, Vector<ProfileResourceReader> paramResourceReaders){
        profilePath = paramFullFilePath;
        resourceReaders = paramResourceReaders;
    }

    public void openFileForStoringProfileData(){
        try {
            currentFileWriter = new BufferedWriter(new FileWriter(profilePath, true));
        } catch (Exception e) {

        }
    }

    public void readAndWriteProfileDataToFile(){
        JSONObject profilingData = new JSONObject();

        for(int i = 0 ; i < resourceReaders.size() ; ++ i){
            resourceReaders.elementAt(i).readAsJSON(profilingData);
        }

        try {

            if(currentFileWriter != null){
                currentFileWriter.write(profilingData.toString());
                currentFileWriter.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeProfileDataFile(){
        if (currentFileWriter != null){
            try {
                currentFileWriter.flush();
                currentFileWriter.close();

            } catch (IOException e) {
                //e.printStackTrace();
            }
            currentFileWriter = null;
        }

    }



}
