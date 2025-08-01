# Use a JDK 21 base image
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy Maven project
COPY . .

# Build the application
RUN ./mvnw clean package -DskipTests

# Run the Spring Boot application
CMD ["java", "-jar", "target/Sprintify-0.0.1-SNAPSHOT.jar"]
