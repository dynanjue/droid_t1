LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := so_t1
LOCAL_SRC_FILES := so_t1.cpp

include $(BUILD_SHARED_LIBRARY)
