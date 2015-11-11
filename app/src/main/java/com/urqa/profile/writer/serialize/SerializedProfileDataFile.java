package com.urqa.profile.writer.serialize;

import com.urqa.profile.reader.ProfileResourceReader;
import com.urqa.profile.writer.ProfileDataFile;
import com.urqa.profile.writer.serialize.AllProfileInformationSerializer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Vector;

/**
 * Created by hwangheeseon on 15. 11. 11..
 */
public class SerializedProfileDataFile implements ProfileDataFile {

    protected String profilePath;
    protected Vector<ProfileResourceReader> resourceReaders;

    // ObjectOutputStream 을 이용한 객체 파일 저장
    protected FileOutputStream fos = null;
    protected ObjectOutputStream oos = null;

    public SerializedProfileDataFile(String paramFullFilePath, Vector<ProfileResourceReader> paramResourceReaders){
        profilePath = paramFullFilePath;
        resourceReaders = paramResourceReaders;
    }

    public void openFileForStoringProfileData(){

        try {
            fos = new FileOutputStream(profilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readAndWriteProfileDataToFile(){


        AllProfileInformationSerializer serializedData = new AllProfileInformationSerializer();

        for(int i = 0 ; i < resourceReaders.size() ; ++ i){
            resourceReaders.elementAt(i).readAsSerializableObject(serializedData);
        }

        try {
            oos.writeObject(serializedData);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void closeProfileDataFile(){

        // 스트림을 닫아준다.
        if(fos != null)
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        if(oos != null)
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }



}
