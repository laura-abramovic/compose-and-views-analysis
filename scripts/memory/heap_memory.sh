#!/bin/bash

compose_app_package="com.abramoviclaura.androidanalysisui.compose"
views_app_package="com.abramoviclaura.androidanalysisui.views"

package=$views_app_package

output_file="output_heap_memory.csv"
output="scripts/memory/outputs/$output_file"

touch $output

{
    echo "Device,$(adb shell getprop ro.product.model)"
    echo "Android Version,$(adb shell getprop ro.build.version.release)"
    echo "API Level,$(adb shell getprop ro.build.version.sdk)"
    echo "Run,Java_heap,Native_heap"
} > $output

 adb shell dumpsys gfxinfo $package reset > /dev/null 2>&1

for i in {1..10}; do
    echo "Run $i"

    MEMINFO=$(adb shell dumpsys meminfo $package)

    JAVA_HEAP=$(echo "$MEMINFO" | grep -m 1 "Java Heap" | awk '{print $3}')
    NATIVE_HEAP=$(echo "$MEMINFO" | grep -m 1 "Native Heap" | awk '{print $3}')

    # If a value is not found, default to 0
    JAVA_HEAP=${JAVA_HEAP:-0}
    NATIVE_HEAP=${NATIVE_HEAP:-0}

    echo "$i,$JAVA_HEAP,$NATIVE_HEAP" >> "$output"
    sleep 5
done
