FROM openjdk:11-jdk
VOLUME /tmp
ARG JAR_FILE=build/libs/farmus-crop-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} farmus-crop.jar

ENTRYPOINT ["java","-jar","/farmus-crop.jar"]