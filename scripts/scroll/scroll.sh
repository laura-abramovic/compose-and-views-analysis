#!/bin/bash

compose_app_package="com.abramoviclaura.androidanalysisui.compose"
views_app_package="com.abramoviclaura.androidanalysisui.views"

package=$views_app_package

adb shell dumpsys gfxinfo $package reset > /dev/null 2>&1

for i in {1..100}; do
  adb shell input swipe 500 2000 500 500
  sleep 0.1
done

sleep 1

adb shell dumpsys gfxinfo $package | grep -E 'Janky frames|50th percentile|90th percentile|95th percentile|99th percentile'
