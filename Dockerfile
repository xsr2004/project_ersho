FROM openjdk:8
VOLUME /tmp
ADD ershou_server-0.0.1-SNAPSHOT.jar ershou.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/ershou.jar"]
