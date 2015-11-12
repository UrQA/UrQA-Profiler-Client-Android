package com.urqa.profile.writer.serialize;

/**
 * Created by hwangheeseon on 15. 11. 10..
 */
public class MemorySerializer implements java.io.Serializable {

    private static final long serialVersionUID = 1206L;

    public int memoryInformation_privateDirty_native;
    public int memoryInformation_privateDirty_dalvik;
    public int memoryInformation_privateDirty_other;
    public int memoryInformation_privateDirty_total;
    public int memoryInformation_PssMemory_native;
    public int memoryInformation_PssMemory_dalvik;
    public int memoryInformation_PssMemory_other;
    public int memoryInformation_PssMemory_total;
    public long memoryInformation_HeapMemory_nativeHeapAllocatedSize;
    public long memoryInformation_HeapMemory_nativeHeapFreeSize;
    public long memoryInformation_HeapMemory_nativeHeapSize;


    public MemorySerializer(){

    }



}
