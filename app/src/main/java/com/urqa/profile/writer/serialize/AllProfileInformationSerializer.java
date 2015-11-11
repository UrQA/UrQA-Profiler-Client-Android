package com.urqa.profile.writer.serialize;

/**
 * Created by hwangheeseon on 15. 11. 9..
 */
public class AllProfileInformationSerializer implements java.io.Serializable{

    private static final long serialVersionUID = 1209L;

    public BatterySerializer batteryInformation;
    public CPUSerializer cpuInformation;
    public MemorySerializer memoryInformation;


    public AllProfileInformationSerializer(){

    }




}
