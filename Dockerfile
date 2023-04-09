FROM openjdk:19
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/word-count-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
COPY target/word-count-0.0.1-SNAPSHOT.jar word-count-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/word-count-0.0.1-SNAPSHOT.jar"]