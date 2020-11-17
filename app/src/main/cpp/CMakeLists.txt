# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.10.2)

# Declares and names the project.

project("testcurve25519")
#jni代码目录
include_directories(/)
include_directories(ed25519/)
include_directories(ed25519/additions/)
include_directories(ed25519/additions/generalized/)
include_directories(ed25519/nacl_includes/)
include_directories(ed25519/nacl_sha512)
include_directories(ed25519/tests/)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK

#递归获取当前目录及子目录下的所有c文件
file(GLOB_RECURSE c_files "*.c")

add_library( # Sets the name of the library.
             curve25519

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             ${c_files}
             curve25519-donna.c
             curve25519-jni.c
        )

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
                        curve25519

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )