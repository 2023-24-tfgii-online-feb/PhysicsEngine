#!/bin/bash

cd WebApp
echo "Building React Application"
npm install &> /dev/null
npm run build &> /dev/nul
echo "React Application built!"
cd ..
echo "Removing old static files"
rm -rf src/main/resources/static/*
echo "Copying new static files"
cp -R WebApp/build/* src/main/resources/static/
echo "Building Spring Boot Application"
./gradlew clean build &> /dev/nul
echo "Spring Boot Application built!"
echo "Running Spring Boot Application..."
java -jar build/libs/TFG-1.0.0-RELEASE.jar
