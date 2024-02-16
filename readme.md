# Syscode Assignment

This is a multi-module Maven-based project designed for the Syscode Assignment. It includes everything necessary to run and build the Profile and Address services, either with Docker or directly from IntelliJ.

## Requirements

- Java 17
- Maven (optional, since the project is configured with Maven Wrapper)
- Docker (optional, only required for building the docker images and running the provided compose file)
- IntelliJ (optional, only for running the project from IntelliJ)

## Build and run the project

### Without IntelliJ, using Docker:

The project utilizes the Google Jib Maven plugin for creating Docker images from the services and publishing them to your local Docker repository.

You can build the project by running the following command in the root project directory:

`mvn clean package -DskipTests`

Alternatively, you can use the Maven Wrapper:

- For Unix-based systems: `./mvnw clean package -DskipTests`
- For Windows: `mvnw.cmd clean package -DskipTests`

This process will create two Docker images:

- `syscode-address-service:latest`
- `syscode-profile-service:latest`

After a successful build, you can start the entire stack using the provided Docker Compose file. This will start both services with proper configuration on your local machine.

Navigate to the `/misc` directory, where you'll find a `docker-compose.yml` file. Run the following command in this directory:

`docker-compose up -d`

#### Accessing the API Documentation
Both services come with SpringDoc OpenAPI and Swagger UI enabled by default.

After a successful startup, you can access the Swagger UI for each service from your preferred browser:

- For the syscode-profile-service: [http://localhost:8443/swagger-ui/index.html#](http://localhost:8443/swagger-ui/index.html#)
- For the syscode-address-service: [http://localhost:8444/swagger-ui/index.html#](http://localhost:8444/swagger-ui/index.html#)


### Build and Run from IntelliJ

The project is fully compatible with IntelliJ IDEA and includes pre-configured run configurations for both the Address and Profile services. These configurations are stored in the `.run` directory at the root of the project and should be automatically recognized by IntelliJ, allowing you to run each service without additional setup.

#### Running the Services

To run a service, simply find the corresponding configuration in the IntelliJ IDEA run configurations dropdown and click the run button. This will start the selected service on your local machine.

#### Accessing the API Documentation

Both the Address and Profile services are configured with Swagger UI, which provides a web-based UI for interacting with the service's API. Once a service is running, you can access its Swagger UI at the following URLs:

- For the syscode-profile-service: [http://localhost:8080/swagger-ui/index.html#](http://localhost:8080/swagger-ui/index.html#)
- For the syscode-address-service: [http://localhost:8081/swagger-ui/index.html#](http://localhost:8081/swagger-ui/index.html#)

This will allow you to explore the APIs, send requests, and view responses directly from your browser.


