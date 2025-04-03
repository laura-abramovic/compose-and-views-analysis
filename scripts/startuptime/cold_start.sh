#!/bin/bash

source scripts/startuptime/app_startup_time.sh

compose_app_package="com.abramoviclaura.androidanalysisui.compose"
compose_app_main_activity="$compose_app_package/com.abramoviclaura.composeui.MainActivity"

views_app_package="com.abramoviclaura.androidanalysisui.views"
views_app_main_activity="$views_app_package/com.abramoviclaura.viewsui.MainActivity"

activity=$compose_app_main_activity

output_file="output_cold_start.csv"
output="scripts/startuptime/outputs/$output_file"

touch $output
get_file_header > $output

for i in {1..10}; do
  result=$(adb shell am start -W -n $activity | grep -E "WaitTime|TotalTime")
  log_results "$i" "$result" >> $output

  sleep 1
  adb shell input keyevent KEYCODE_APP_SWITCH
  sleep 2
  adb shell input keyevent DEL
  sleep 1
done
