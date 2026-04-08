FROM maven:4.0.0-rc-5-eclipse-temurin-25-alpine AS build

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN --mount=type=cache,target=/root/.m2 \
    mvn clean package -DskipTests


FROM eclipse-temurin:25.0.2_10-jre-ubi10-minimal
LABEL org.opencontainers.image.authors="gideon dakore"
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]