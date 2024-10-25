# Build stage
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
WORKDIR /workspace/app
COPY pom.xml .
COPY src src
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /workspace/app/target/*.jar app.jar

ENV SPRING_PROFILES_ACTIVE=prod

# Cloud Run inyectará la variable PORT automáticamente
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT} -jar app.jar"]