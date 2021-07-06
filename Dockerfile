FROM openjdk:8 AS TEMP_BUILD_IMAGE
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
#RUN chmod +x gradlew 
COPY . .
COPY ./prod.application.properties ./src/main/resources/application.properties 
RUN ./mvnw package -DskipTests 

FROM openjdk:8
ENV ARTIFACT_NAME=prueba-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/$ARTIFACT_NAME .
CMD java -jar $ARTIFACT_NAME
