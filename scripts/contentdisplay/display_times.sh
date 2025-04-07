#!/bin/bash

compose_app_package="com.abramoviclaura.androidanalysisui.compose"
compose_app_main_activity="$compose_app_package/com.abramoviclaura.composeui.MainActivity"

views_app_package="com.abramoviclaura.androidanalysisui.views"
views_app_main_activity="$views_app_package/com.abramoviclaura.viewsui.MainActivity"

activity=$views_app_main_activity

output_file="output_display_time.csv"
output="scripts/contentdisplay/outputs/$output_file"

touch $output

{
    echo "Device,$(adb shell getprop ro.product.model)"
    echo "Android Version,$(adb shell getprop ro.build.version.release)"
    echo "API Level,$(adb shell getprop ro.build.version.sdk)"
    echo "Run,TTID,TTFD"
} > $output

for i in {1..10}; do
  adb logcat -c
  adb shell am start -n $activity
  sleep 2

  result=$(adb shell logcat -d -v raw ActivityManager:I)
  displayed=$(echo "$result" | grep "Displayed"| awk '{print $6}')
  fully_drawn=$(echo "$result" | grep "Fully drawn"| awk '{print $4}')

  echo "$i,$displayed,$fully_drawn" >> $output

  sleep 1
  adb shell input keyevent KEYCODE_APP_SWITCH
  sleep 2
  adb shell input keyevent DEL
  sleep 1
done
