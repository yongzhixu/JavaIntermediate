// File : "DateTimeUtils.c"
#include <jni.h>            // JNI header provided by JDK
#include <stdio.h>          // C Standard IO Header
#include "DateTimeUtils.h"             // Generated

// Implementation of the native method getSystemTime()
JNIEXPORT void JNICALL Java_com_example_jvm_lab_DateTimeUtils_getSystemTime(JNIEnv *env, jobject thisObj) {
   printf("Hello World!\n");
   return;
}