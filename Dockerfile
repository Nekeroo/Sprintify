# --- Build Stage ---
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app
COPY . .

RUN mvn clean package -DskipTests

# --- Runtime Stage ---
FROM eclipse-temurin:21-jdk

WORKDIR /app
COPY --from=builder /app/target/Sprintify-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
