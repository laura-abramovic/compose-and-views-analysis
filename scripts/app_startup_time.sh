#!/bin/bash

get_file_header() {
    echo "Device,$(adb shell getprop ro.product.model)"
    echo "Android Version,$(adb shell getprop ro.build.version.release)"
    echo "API Level,$(adb shell getprop ro.build.version.sdk)"
    echo "Run,TotalTime,WaitTime"
}

log_results() {
  local index=$1
  local result_string=$2

  total_time=$(echo "$result_string" | grep "TotalTime" | awk '{print $2}')
  wait_time=$(echo "$result_string" | grep "WaitTime" | awk '{print $2}')

  echo "$index,$total_time,$wait_time"
}
