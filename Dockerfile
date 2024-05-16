# Use the official Maven image with JDK installed as the base image
FROM maven:3.8.4-openjdk-17-slim

# Set the working directory in the Docker image
WORKDIR /app

# Copy your project into the Docker image
COPY . .

# Pre-download dependencies to improve build times unless dependencies change
RUN mvn dependency:go-offline
RUN mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install-deps"

# Run your tests when the container starts.
# Note: Adjust the Maven command according to your project's needs.
CMD ["mvn", "verify"]