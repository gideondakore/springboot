FROM maven:4.0.0-rc-5-eclipse-temurin-17-alpine AS built
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests


FROM eclipse-temurin:25.0.2_10-jre-ubi10-minimal
WORKDIR /app
COPY --from=built /app/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
