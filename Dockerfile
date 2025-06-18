# Etapa 1: Construcción del JAR con Gradle
FROM gradle:8.7-jdk21 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle bootJar --no-daemon

# Etapa 2: Imagen final que ejecuta el JAR
FROM eclipse-temurin:21.0.2_13-jre-jammy
EXPOSE 8080
COPY --from=build /home/gradle/src/build/libs/event-nexus-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]