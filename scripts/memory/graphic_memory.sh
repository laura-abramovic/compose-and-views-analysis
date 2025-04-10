#!/bin/bash

compose_app_package="com.abramoviclaura.androidanalysisui.compose"
views_app_package="com.abramoviclaura.androidanalysisui.views"

package=$compose_app_package

output_file="output_graphic_memory.csv"
output="scripts/memory/outputs/$output_file"

touch $output

{
    echo "Device,$(adb shell getprop ro.product.model)"
    echo "Android Version,$(adb shell getprop ro.build.version.release)"
    echo "API Level,$(adb shell getprop ro.build.version.sdk)"
    echo "Run,Graphics_Memory,GL_Memory,EGL_Memory"
} > $output

 adb shell dumpsys gfxinfo $package reset > /dev/null 2>&1

for i in {1..10}; do
    echo "Run $i"

    MEMINFO=$(adb shell dumpsys meminfo $package)

    GRAPHICS_MEM=$(echo "$MEMINFO" | grep -m 1 "Graphics" | awk '{print $2}')
    GL_MEM=$(echo "$MEMINFO" | grep -m 1 "GL" | awk '{print $3}')
    EGL_MEM=$(echo "$MEMINFO" | grep -m 1 "EGL" | awk '{print $3}')

    # If a value is not found, default to 0
    GRAPHICS_MEM=${GRAPHICS_MEM:-0}
    GL_MEM=${GL_MEM:-0}
    EGL_MEM=${EGL_MEM:-0}

    echo "$MEMINFO" | grep -m 1 "Graphics"
    echo "$MEMINFO" | grep -m 1 "GL"
    echo "$MEMINFO" | grep -m 1 "EGL"

    echo "$i,$GRAPHICS_MEM,$GL_MEM,$EGL_MEM" >> "$output"
    sleep 5
done
