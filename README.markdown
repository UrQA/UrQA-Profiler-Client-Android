# UrQA-Profiler-Client-Android
- UrQA android client sdk which is changed project for Eclipse into Android Studio
- add profiling fuction(there are two profiling options, shallow and deep)

# To build & release the android client sdk for java
1. set the library version inside the below files
 - update the variable of "SDKVersion" in "UrQA-Profiler-Client-Android/app/src/main/java/com/urqa/common/StateData.java"
 - update the jar library name at the task "exportJar" in the "UrQA-Profiler-Client-Android/app/build.gradle"
2. click Gradle on the right side of the IDE window, then The Gradle tasks panel appears
3. double-click the "UrQA-Profile-Client-Android/app/Tasks/other/exportJar" task
4. the build generates an jar library file named "file name.jar" in the "UrQA-Profiler-Client-Android/app/release” directory
5. release the jar file


