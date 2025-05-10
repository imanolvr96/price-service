# Stage 1: Build the application using Maven
# We use an image with JDK installed to build the app

FROM eclipse-temurin:23-jdk-alpine AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml first to leverage Docker's caching mechanism for dependencies
# This way, if the pom.xml hasn't changed, Docker will reuse the layer with dependencies already resolved
COPY pom.xml .

# Copy the rest of the application source code
COPY src ./src

# Build the application using Maven Wrapper, skipping tests for faster build
RUN ./mvnw clean package -DskipTests

# Stage 2: Create a smaller final image for running the application
# We use the JRE image (Java Runtime Environment) which is smaller and does not include development tools

FROM eclipse-temurin:23-jre-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the jar file from the previous build stage into the new image
COPY --from=build /app/target/*.jar app.jar

# Expose the port your application will run on
EXPOSE 8080

# Set the default command to run your application
CMD ["java", "-jar", "app.jar"]