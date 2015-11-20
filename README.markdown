# UrQA-Profiler-Client-Android history
- UrQA android client sdk which is changed project for Eclipse into Android Studio
- add profiling fuction(there are two profiling options, shallow and deep)

# Android Client SDK
This is the client sdk for android application which is being tested automatically. 
While the android application is being tested, the sdk
- is gathering profiling data, and sends the profiling data to server after the test is finished. 
- reports the crash to the server when the runtime exceptions or errors are occurred.

So, the developer can 
- analyze potential problem for the resource usage of the application and optimize the resource usage of the application with profiling data
- analyze the crash and solve the problems
- test the multi devices at once and compare the test result of devices

# To build & release the android client sdk for java
1. set the library version inside the below files
 - update the variable of "SDKVersion" in "UrQA-Profiler-Client-Android/app/src/main/java/com/urqa/common/StateData.java"<br>
  <img src="readmeImages/sdkversion.png" alt="SDK Version" width="300" height="150"/>
 - update the jar library name at the task "exportJar" in the "UrQA-Profiler-Client-Android/app/build.gradle"<br>
  <img src="readmeImages/sdklibraryname.png" alt="SDK name" width="300" height="150"/>
2. click Gradle on the right side of the IDE window, then The Gradle tasks panel appears<br>
  <img src="readmeImages/gradletaskspanel.png" alt="SDK name" width="200" height="150"/>
3. double-click the "UrQA-Profile-Client-Android/app/Tasks/other/exportJar" task<br>
  <img src="readmeImages/exportJar.png" alt="SDK name" width="200" height="150"/>
4. the build generates an jar library file named "file name.jar" in the "UrQA-Profiler-Client-Android/app/release” directory<br>
  <img src="readmeImages/sdklibrary.png" alt="SDK name" width="200" height="150"/>
5. release the jar file<br>

# To build & release the android client sdk for ndk
1. define the APP_STL as "gnustl_static" in the "UrQA-Profiler-Client-Android/app/src/main/jni/Application.mk"
2. click Gradle on the right side of the IDE window, then The Gradle tasks panel appears
3. double-click the "UrQA-Profile-Client-Android/app/Tasks/other/ndkBuild" task
4. the build generates two static library files below in the directory of "UrQA-Profile-Client-Android/app/src/main/obj/local"
 - armeabi/liburqanative.a
 - armeabi-v7a/liburqanative.a
5. rename two static library files as below
 - armeabi/liburqanative_gnustl.a
 - armeabi-v7a/liburqanative_gnustl.a
6. define the APP_STL as "stlport_static" in the "UrQA-Profiler-Client-Android/app/src/main/jni/Application.mk"<br>
  <img src="readmeImages/application_mk.png" alt="SDK name" width="200" height="150"/>
7. click Gradle on the right side of the IDE window, then The Gradle tasks panel appears
8. double-click the "UrQA-Profile-Client-Android/app/Tasks/other/ndkBuild" task
9. the build generates two static library files below in the directory of "UrQA-Profile-Client-Android/app/src/main/obj/local”
 - armeabi/liburqanative.a
 - armeabi-v7a/liburqanative.a<br>
   <img src="readmeImages/staticlibrary.png" alt="SDK name" width="200" height="350"/>
10. copy the directory of “UrQA-Profiler-Client-Android/app/src/main/jni/header”
11. delete *.cc files in the directory and and all levels of subdirectories
12. create a zip archive of the below directory
  - armeabi
  - armeabi-v7a
  - header<br>
   <img src="readmeImages/tree.png" alt="SDK name" width="200" height="400"/>&nbsp;&nbsp;
   <img src="readmeImages/tree2.png" alt="SDK name" width="200" height="400"/>&nbsp;&nbsp;
   <img src="readmeImages/tree3.png" alt="SDK name" width="200" height="400"/>&nbsp;&nbsp;
   <img src="readmeImages/tree4.png" alt="SDK name" width="200" height="400"/>&nbsp;&nbsp;
   <img src="readmeImages/tree5.png" alt="SDK name" width="200" height="400"/>&nbsp;&nbsp;
13. release the zip file<br>

# How to use the sdk for profile

1. download the lastest version of the UrQA client library 
 - https://github.com/UrQA/UrQA-Profiler-Client-Android/tree/master/app/release 

2. import the UrQA client library 
    1. copy the library file in the libs folder. Make sure you are in the Project view mode (Top left corver of the Project window)
    2. Right click on the UrQA client library file and select the last option "Add as library" on the pop up window

3. Add the “Internet” permission to your manifest file to upload the profile data file to the server 
 - <uses-permission android:name="android.permission.INTERNET" />

4. write the code  
    1. import com.urqa.profile.BaseProfile;
    2. create an instance for profile function
       in case of Shallow Profile 
        - BaseProfile.getInstance(getApplicationContext(), BaseProfile.PROFILE_TYPE_SHALLOW);
          - gather the profiling data for memory, cpu, and power periodically ( the period of the profile is 1000 miliseconds
       or 
        - BaseProfile.getInstance(getApplicationContext(), BaseProfile.PROFILE_TYPE_SHALLOW , x);
          - gather the profiling data for memory, cpu, and power per x miliseconds

       in case of Deep profile 
        - BaseProfile.getInstance(getApplicationContext(), BaseProfile.PROFILE_TYPE_DEEP);
          - gather the profiling data  for memory, cpu, and power only when the method which is annotated is called. 
  
    3. in case of Deep Profile<br> 
       mark with @AfterProfile, @BeforeProfile, @Profile annotation on the method which means you want to profile<br> 
       warning) don’t mark with annotation on the method when it is Shallow Profile

    4. call the startReadProfileData() function of the BaseProfile instance<br> 
       then it will start the profiling 

    5. call the stopReadProfileData() function of the BaseProfile instance<br> 
       then it will finish the profiling 
