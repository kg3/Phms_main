#!/bin/sh

#  Pull_Build_Sync.sh
#  
#
#  Created by k3 on 12/5/15.
#

PROEJCT=~/AndroidStudioProjects/Phms_main
APKS=app/build/outputs/apk/
SYNC="/Users/k3/Google Drive/SFTWRE ENGENG/Apk/"

git pull

./gradlew assembleDebug


cp $APKS/*.apk /Users/k3/Google\ Drive/SFTWRE\ ENGENG/Apk/

exit 0