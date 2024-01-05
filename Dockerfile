FROM node:20
SHELL ["/bin/bash", "-c"]

USER root

RUN apt update && \
    apt install git unzip curl openjdk-17-jdk -y && \
    wget -c https://services.gradle.org/distributions/gradle-7.4.2-bin.zip -P /tmp && \
    unzip -d /opt/gradle /tmp/gradle-7.4.2-bin.zip
ENV GRADLE_HOME=/opt/gradle/gradle-7.4.2
ENV PATH=${GRADLE_HOME}/bin:${PATH}

WORKDIR /opt
RUN git clone https://github.com/dmm1005/PhysicsEngine && \
    cd /opt/PhysicsEngine/WebApp && \
    echo "Building WebApp..." && \
    npm install &> /dev/null && \
    npm run build &> /dev/null && \
    echo "Building WebApp done!" && \
    echo "Removing old static files" && \
    rm -rf /opt/PhysicsEngine/src/main/resources/static && \
    echo "Copying new static files" && \
    cp -r /opt/PhysicsEngine/WebApp/build /opt/PhysicsEngine/src/main/resources/static && \
    cd /opt/PhysicsEngine && \
    echo "Building Server..." && \
    gradle build &> /dev/null && \
    echo "Building Server done!"

WORKDIR /opt/PhysicsEngine
EXPOSE 3100
# Set the command to run your app
CMD ["java", "-jar", "/opt/PhysicsEngine/build/libs/TFG-1.0.0-RELEASE.jar"]

