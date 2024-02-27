FROM openjdk:17
ENV DB_URL ${DB_URL} \
    DB_USERNAME ${DB_USERNAME} \
    DB_PASSWORD ${DB_PASSWORD} \
ARG JAR_FILE=/build/libs/kobaco-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]