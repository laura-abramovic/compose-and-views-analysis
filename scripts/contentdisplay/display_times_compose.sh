compose_app_package="com.abramoviclaura.androidanalysisui.compose"
compose_app_main_activity="$compose_app_package/com.abramoviclaura.composeui.MainActivity"

output_file="output_ttid_compose.csv"
output="scripts/contentdisplay/outputs/$output_file"

touch $output

{
    echo "Device,$(adb shell getprop ro.product.model)"
    echo "Android Version,$(adb shell getprop ro.build.version.release)"
    echo "API Level,$(adb shell getprop ro.build.version.sdk)"
    echo "Run,TTID,TTFD"
} > $output

for i in {1..5}; do
  adb logcat -c
  adb shell am start -n $compose_app_main_activity
  sleep 2

  result=$(adb shell logcat -d -v raw ActivityManager:I *:S)
  displayed=$(echo "$result" | grep "Displayed"| awk '{print $3}')
  fully_drawn=$(echo "$result" | grep "Fully drawn"| awk '{print $4}')

  echo "$i,$displayed,$fully_drawn" >> $output

  sleep 2
  adb shell pm clear $compose_app_package ## close the app
done
