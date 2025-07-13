# Stage 1: Build the project
FROM maven:3.9.10-eclipse-temurin-21 AS build

WORKDIR /app

# Copy only pom.xml and download dependencies first (to cache them)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build the application JAR
RUN mvn clean package -DskipTests

# Stage 2: Run the app with lightweight image
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy the jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080 (or your configured port)
EXPOSE 8080

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
