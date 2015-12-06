#!/bin/bash

#sleep 1
#git pull

sleep 1
./gradlew assembleDebug

sleep 1
cp app/build/outputs/apk/*.apk ~/Google\ Drive/SFTWRE\ ENGENG/Apk/

ls -alh app/build/outputs/apk/



exit 0
