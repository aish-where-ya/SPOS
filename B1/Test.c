#include"Test.h"
#include <jni.h>
#include<stdio.h>

JNIEXPORT jint JNICALL Java_Test_add (JNIEnv *env, jobject obj, jint n1, jint n2)
{
	jint res;
	res = n1+n2;
	return res;
}

JNIEXPORT jint JNICALL Java_Test_sub  (JNIEnv *env, jobject obj, jint n1, jint n2)
{
	jint res;
	res = n1-n2;
	return res;
}
