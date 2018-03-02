#!/bin/bash
echo ------------------------------------------------
echo RexCord: Getting Project version from pom.xml...

VERSION=$(mvn help:evaluate -Dexpression=project.version | grep -v '^\[')


if [ -z ${VERSION} ]; then
    echo Could not get version number from pom.xml. Exiting...
    sleep 3
    exit
fi

echo RexCord: Found version ${VERSION}
echo ------------------------------------------------

java -jar target/rexcord_${VERSION}.jar

sleep 5