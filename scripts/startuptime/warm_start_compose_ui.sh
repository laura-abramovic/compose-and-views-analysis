#!/bin/bash

source scripts/startuptime/app_startup_time.sh

compose_app_package="com.abramoviclaura.androidanalysisui.compose"
compose_app_main_activity="$compose_app_package/com.abramoviclaura.composeui.MainActivity"

output_file="output_warm_start_compose_ui.csv"
output="scripts/startuptime/outputs/$output_file"

touch $output
get_file_header > $output

for i in {1..5}; do
  adb shell am start -n $compose_app_main_activity
  sleep 1
  adb shell input keyevent KEYCODE_HOME
  sleep 1
  adb shell am force-stop compose_app_main_activity
  sleep 1

  result=$(adb shell am start -W -n $compose_app_main_activity | grep -E "WaitTime|TotalTime")
  log_results "$i" "$result" >> $output

  sleep 1
  adb shell pm clear $compose_app_package ## close the app
  sleep 1
done
