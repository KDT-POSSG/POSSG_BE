# Step 1: Build the Spring Boot application
FROM maven:3.8.3-openjdk-17 AS builder

WORKDIR /app

# Copy the main pom.xml file first
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline -B

# Copy only the necessary directories and files
COPY src/ src/
COPY .mvn/ .mvn/
COPY bin/ bin/
COPY .dockerignore .dockerignore
COPY Dockerfile Dockerfile
COPY mvnw mvnw
COPY mvnw.cmd mvnw.cmd

# Add more COPY commands for other necessary files

# Build the Spring Boot application using Maven
RUN mvn clean package -DskipTests

# Step 2: Create the final image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file built in Step 1 into the container
COPY --from=builder /app/target/*.jar app.jar

# Expose the port that your Spring Boot application listens on (adjust the port if necessary)
# determine the port number
EXPOSE 3000

# Define the command to run your Spring Boot application
CMD ["java", "-jar", "app.jar"]
