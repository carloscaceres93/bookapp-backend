FROM maven:3.8.1-openjdk-11 AS TEMP_BUILD_IMAGE
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY . .
COPY ./prod.application.properties ./src/main/resources/application.properties
RUN mvn package -DskipTests

FROM openjdk:11
ENV ARTIFACT_NAME=prueba-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/target/$ARTIFACT_NAME .
CMD java -jar $ARTIFACT_NAME