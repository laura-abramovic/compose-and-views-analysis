#!/bin/bash

source scripts/startuptime/app_startup_time.sh

compose_app_package="com.abramoviclaura.androidanalysisui.compose"
compose_app_main_activity="$compose_app_package/com.abramoviclaura.composeui.MainActivity"

views_app_package="com.abramoviclaura.androidanalysisui.views"
views_app_main_activity="$views_app_package/com.abramoviclaura.viewsui.MainActivity"

activity=$compose_app_main_activity

output_file="output_hot_start.csv"
output="scripts/startuptime/outputs/$output_file"

touch $output
get_file_header > $output

adb shell am start -n $activity

for i in {1..10}; do
  sleep 1
  adb shell input keyevent KEYCODE_HOME
  sleep 1

  result=$(adb shell am start -W -n $activity | grep -E "WaitTime|TotalTime")
  log_results "$i" "$result" >> $output
done
