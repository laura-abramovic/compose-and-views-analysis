#!/bin/bash

source scripts/startuptime/app_startup_time.sh

compose_app_package="com.abramoviclaura.androidanalysisui.compose"
compose_app_main_activity="$compose_app_package/com.abramoviclaura.composeui.MainActivity"

output_file="output_hot_start_compose_ui.csv"
output="scripts/startuptime/outputs/$output_file"

touch $output
get_file_header > $output

adb shell am start -n $compose_app_main_activity

for i in {1..5}; do
  sleep 1
  adb shell input keyevent KEYCODE_HOME
  sleep 1

  result=$(adb shell am start -W -n $compose_app_main_activity | grep -E "WaitTime|TotalTime")
  log_results "$i" "$result" >> $output
done
