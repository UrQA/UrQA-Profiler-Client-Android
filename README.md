# UrQA-Profiler-Client-Android
- UrQA android client sdk which is changed project for Eclipse into Android Studio
- add profiling fuction(there are two profiling options, shallow and deep)

To build & release android client sdk for java
1. set the library version inside the below files
   - update the variable of "SDKVersion" in "UrQA-Profiler-Client-Android/app/src/main/java/com/urqa/common/StateData.java" 
   - update the jar library name at the task "exportJar" in the "UrQA-Profiler-Client-Android/app/build.gradle"
2. open Gradle projects
3. double-click the "UrQA-Profile-Client-Android/app/Tasks/other/exportJar" task
4. the jar library file is created in the path of "UrQA-Profiler-Client-Android/app/release/[jar file name]"
5. release the jar file

To build & release the android client sdk for ndk
1. define the APP_STL as "gnustl_static" in the "UrQA-Profiler-Client-Android/app/src/main/jni/Application.mk"
2. click Gradle on the right side of the IDE window, then The Gradle tasks panel appears.
2. double-click the "UrQA-Profile-Client-Android/app/Tasks/other/bundleRelease" task
3. the build generates two static library files below in the directory of "UrQA-Profile-Client-Android/app/src/main/obj/local"
   - armeabi/liburqanative.a
   - armeabi-v7a/liburqanative.a
4. rename two static library files as like
   - armeabi/liburqanative_gnustl.a
   - armeabi-v7a/liburqanative_gnustl.a
5. define the APP_STL as "stlport_static" in the "UrQA-Profiler-Client-Android/app/src/main/jni/Application.mk"
6. click Gradle on the right side of the IDE window, then The Gradle tasks panel appears.
7. double-click the "UrQA-Profile-Client-Android/app/Tasks/other/bundleRelease" task
8. the build generates two static library files below in the directory of "UrQA-Profile-Client-Android/app/src/main/obj/local”
   - armeabi/liburqanative.a
   - armeabi-v7a/liburqanative.a
9. copy the directory of “UrQA-Profiler-Client-Android/app/src/main/jni/header”
10. delete *.cc files in the directory and and all levels of subdirectories
11. create a zip archive of the below directories and the readme file about this library
  - armeabi
  - armeabi-v7a
  - header
13. release the zip file



