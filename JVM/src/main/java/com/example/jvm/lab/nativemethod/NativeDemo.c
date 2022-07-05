#include <jni.h>
#include <stdio.h>
#include "NativeDemo.h"
JNIEXPORT void JNICALL
Java_NativeDemo_encryptData(JNIEnv *env, jobject obj, jstring inputstr)
{
const char *str= (*env)->GetStringUTFChars(env,inputstr,0) // create string from jstring
char Newch = '@';
for(i = 0; i <= strlen(str); i++)
{
if(str[i] == 'a' || str[i]== 'e' || str[i]== 'i' || str[i]== 'o' || str[i]== 'u' || str[i] == 'A' || str[i]== 'E' || str[i]== 'I' || str[i]== 'O' || str[i]== 'U')
{
str[i] = Newch;
}
}
return env->NewStringUTF(str); // convert string to jstring
}