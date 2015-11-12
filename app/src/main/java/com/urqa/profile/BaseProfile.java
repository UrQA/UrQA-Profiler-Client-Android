package com.urqa.profile;

import android.content.Context;

import com.urqa.profile.reader.BatteryInformationReader;
import com.urqa.profile.reader.CPUInformationReader;
import com.urqa.profile.reader.MemoryInformationReader;
import com.urqa.profile.reader.ProfileResourceReader;
import com.urqa.profile.writer.json.JSONProfileDataFile;
import com.urqa.profile.writer.ProfileDataFile;
import com.urqa.profile.writer.serialize.SerializedProfileDataFile;

import java.util.Date;
import java.util.Vector;

/**
 * Created by hwangheeseon on 15. 11. 10..
 */
public abstract class BaseProfile {

    protected Vector<String> profilingfiles;
    protected Context context;
    protected String cachePath;

    protected ProfileDataFile profileDataFile = null;
    protected String currentFileName;
    protected String currentFilePath;

    protected Vector<ProfileResourceReader> resourceReaders;

    protected static BaseProfile profilingObject = null;

    public static final int PROFILE_TYPE_SHALLOW = 1;
    public static final int PROFILE_TYPE_DEEP = 2;

    private int profileType;

    public static final int PROFILE_DATAFILE_JSON = 1;
    public static final int PROFILE_DATAFILE_SERIALIZATION = 2;

    private int profileDatafileType;

    public BaseProfile(Context paramContext, int paramProfileType, int paramProfileDataFileFormatType){
        prepareProfiling(paramContext, paramProfileType, paramProfileDataFileFormatType);
    }

    public static BaseProfile getInstance(Context paramContext, int paramProfileType, int paramProfileDataFileFormatType){

        if(profilingObject == null) {

            if(paramContext == null)
                return null;

            if(paramProfileType == PROFILE_TYPE_SHALLOW)
                profilingObject = new ShallowProfile(paramContext, paramProfileType, paramProfileDataFileFormatType);
            else if(paramProfileType == PROFILE_TYPE_DEEP)
                profilingObject = new DeepProfile(paramContext, paramProfileType, paramProfileDataFileFormatType);
            else
                return null;

            if(paramProfileDataFileFormatType != PROFILE_DATAFILE_JSON && paramProfileDataFileFormatType != PROFILE_DATAFILE_SERIALIZATION){
                return null;
            }

        }

        return profilingObject;

    }

    public void startReadProfileData() {

        if(profileDataFile != null)
        {
            stopReadProfileData();
        }
        profileDataFile = makeFileForStoringProfileData();

        for(int i = 0 ; i < resourceReaders.size() ; ++ i){
            resourceReaders.elementAt(i).prepareReadResource();
        }

    }

    public void stopReadProfileData() {
        if (profileDataFile != null){
            try {
                profileDataFile.closeProfileDataFile();
            } catch (Exception e) {
                //e.printStackTrace();
            }
            profileDataFile = null;
        }

        if(resourceReaders != null){
            for(int i = 0 ; i < resourceReaders.size() ; ++ i){
                resourceReaders.elementAt(i).finishReadResource();
            }

            resourceReaders.removeAllElements();
            resourceReaders = null;

        }
    }

    protected void prepareProfiling(Context paramContext, int paramProfileType, int paramProfileDataFileFormatType){
        context = paramContext;
        cachePath = context.getCacheDir().getAbsolutePath();
        profilingfiles = new Vector<String>();

        profileType = paramProfileType;
        profileDatafileType = paramProfileDataFileFormatType;

        resourceReaders = new Vector<ProfileResourceReader>();

        ProfileResourceReader battery = new BatteryInformationReader(context);
        ProfileResourceReader cpu = new CPUInformationReader();
        ProfileResourceReader memory = new MemoryInformationReader();

        resourceReaders.addElement(battery);
        resourceReaders.addElement(cpu);
        resourceReaders.addElement(memory);
    }


    protected ProfileDataFile makeFileForStoringProfileData() {

        String currentFileName = context.getApplicationContext().getApplicationInfo().toString() + "_" + (new Date(System.currentTimeMillis())).toString()+ "_Profileing.json";
        currentFilePath = cachePath + "/" + currentFileName;

        if(profileDatafileType == PROFILE_DATAFILE_JSON){
            profileDataFile = new JSONProfileDataFile(currentFilePath,resourceReaders);
        }
        else if (profileDatafileType == PROFILE_DATAFILE_SERIALIZATION){
            profileDataFile = new SerializedProfileDataFile(currentFilePath,resourceReaders);
        }
        else
            return null;

        profilingfiles.addElement(currentFilePath);
        return profileDataFile;
    }


    public static void writeProfileDataToFile(){
        if(profilingObject != null)
            profilingObject.profileDataFile.readAndWriteProfileDataToFile();

    }

}
