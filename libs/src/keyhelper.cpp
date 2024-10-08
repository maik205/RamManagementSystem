#include "../headers/ui_UIController.h"
#include <iostream>
#include <conio.h>


JNIEXPORT jboolean JNICALL Java_ui_UIController_kbHit
  (JNIEnv *, jobject) {
    return _kbhit();
  }
  JNIEXPORT jchar JNICALL Java_ui_UIController_getChar
  (JNIEnv *, jobject) {
    return _getch();
  }