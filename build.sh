#!/bin/bash

cd WebApp
npm install
npm run build
cd ..
echo "Removing old static files"
rm -rf src/main/resources/static/*
echo "Copying new static files"
cp -R WebApp/build/* src/main/resources/static/
./gradlew build
java -jar build/libs/TFG-1.0.0-RELEASE.jar
