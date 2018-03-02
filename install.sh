#!/bin/bash

echo ------------------------------------------------
echo RexCord: Getting Project version from pom.xml...

mvn install clean package
read