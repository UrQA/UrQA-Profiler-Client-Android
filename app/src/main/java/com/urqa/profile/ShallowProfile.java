package com.urqa.profile;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

import com.urqa.profile.reader.CurrentThreadInformationReader;
import com.urqa.profile.reader.ProfileResourceReader;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hwangheeseon on 15. 11. 10..
 */
class ShallowProfile extends BaseProfile{

    private Timer mTimer = null;
    private long profilingPeriodMillis;

    public ShallowProfile(){
        super();
        profilingPeriodMillis = 1000;
    }

    public ShallowProfile(long periodMillis){
        super();
        profilingPeriodMillis = periodMillis;
    }

    protected void prepareProfiling(Context paramContext, int paramProfileType, int paramProfileDataFileFormatType) {
        super.prepareProfiling(paramContext, paramProfileType, paramProfileDataFileFormatType);
        ProfileResourceReader currentThread = new CurrentThreadInformationReader();

        resourceReaders.addElement(currentThread);

    }

    public void startReadProfileData() {
        super.startReadProfileData();

        mTimer = new Timer(true);
        mTimer.schedule(
                new TimerTask() {
                    @TargetApi(Build.VERSION_CODES.ECLAIR)
                    @Override
                    public void run() {
                        BaseProfile.writeProfileDataToFile();
                    }

                }, 1000, profilingPeriodMillis
        );

    }

    public void stopReadProfileData() {
        if (mTimer != null) {
            try {
                mTimer.cancel();
                mTimer = null;
            } catch (Exception e) {

            }
        }
        super.stopReadProfileData();

    }

}
