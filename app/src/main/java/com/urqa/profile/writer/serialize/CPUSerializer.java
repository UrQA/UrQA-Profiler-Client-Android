package com.urqa.profile.writer.serialize;

/**
 * Created by hwangheeseon on 15. 11. 10..
 */
public class CPUSerializer implements java.io.Serializable {

    private static final long serialVersionUID = 1207L;

    public float cpuInformation_idle;
    public float cpuInformation_user;
    public float cpuInformation_system;
    public float cpuInformation_total;

    public CPUSerializer(){

    }


}
