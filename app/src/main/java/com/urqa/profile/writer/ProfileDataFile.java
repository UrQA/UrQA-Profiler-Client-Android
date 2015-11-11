package com.urqa.profile.writer;

/**
 * Created by hwangheeseon on 15. 11. 11..
 */
public interface ProfileDataFile {

    public void openFileForStoringProfileData();
    public void readAndWriteProfileDataToFile();
    public void closeProfileDataFile() throws Exception;


}
