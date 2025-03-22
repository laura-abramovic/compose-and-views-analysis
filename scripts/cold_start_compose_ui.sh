#!/bin/bash

compose_app_package="com.abramoviclaura.androidanalysisui.compose"
compose_app_main_activity="$compose_app_package/com.abramoviclaura.composeui.MainActivity"

output_file="output_cold_start_compose_ui.csv"
output="scripts/outputs/$output_file"

touch $output

{
  echo "Device,$(adb shell getprop ro.product.model),"
  echo "Android Version,$(adb shell getprop ro.build.version.release),"
  echo "API Level,$(adb shell getprop ro.build.version.sdk)"
  echo "Run,TotalTime,WaitTime"
} > $output

for i in {1..5}; do
  result=$(adb shell am start -W -n $compose_app_main_activity | grep -E "WaitTime|TotalTime")

  # Extract times from the output
  total_time=$(echo "$result" | grep "TotalTime" | awk '{print $2}')
  wait_time=$(echo "$result" | grep "WaitTime" | awk '{print $2}')

  echo "$i,$total_time,$wait_time" >> $output

  sleep 1
  adb shell pm clear $compose_app_package ## close the app
  sleep 1
done
