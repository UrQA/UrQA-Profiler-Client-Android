package com.urqa.profile.reader;

import org.json.JSONObject;
import java.io.Serializable;

/**
 * Created by hwangheeseon on 15. 11. 11..
 */
public interface ProfileResourceReader {

    public void prepareReadResource();
    public void finishReadResource();
    public void readAsJSON(JSONObject paramResourceObj);
    public void readAsSerializableObject(Serializable paramResourceObj);

}
