# UrQA-Profiler-Client-Android
- UrQA android client sdk which is changed project for Eclipse into Android Studio
- add profiling fuction(there are two profiling options, shallow and deep)

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

