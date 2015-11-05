package com.urqa.profile;

import android.content.Context;
import android.util.Log;

/**
 * Created by hwangheeseon on 15. 10. 9..
 */
public class UrQAProfile {


    private static Context context;

    private static boolean isOnProfiling = false;
    private static SendFileManager sendFileManager = null;
    private static String cachePath;


    public UrQAProfile(Context _context) {
        //application context
        context = _context;
        sendFileManager = SendFileManager.getInstance(context);
        cachePath = sendFileManager.getCachePath();

    }

    public static String GetCachePath() {
        return cachePath;
    }


    //profiling 데이타 수집 시작
    public void start(){
        if( UrQAProfile.isOnProfiling == false) {
            Log.e("hs------------------", "start");
            UrQAProfile.isOnProfiling = true;
            sendFileManager.startProfiling();
        }
    }

    //profiling 데이타 수집 종료
    public void stop(){

        if( UrQAProfile.isOnProfiling ) {
            Log.e("hs------------------", "stop");
            UrQAProfile.isOnProfiling = false;
            if (sendFileManager != null) {
                sendFileManager.stopProfiling();
            }
        }
    }

    public static void writeProfile(){
        if(UrQAProfile.isOnProfiling ){
            sendFileManager.writeProfile();

        }

    }


}
