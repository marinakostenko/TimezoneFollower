# our base build image
FROM maven:3.8.6-openjdk-18 as maven

# copy the project files
COPY ./pom.xml ./pom.xml

#   build all dependencies
RUN mvn dependency:go-offline -B

# copy your other files
COPY ./src ./src

# build for release
RUN mvn package -DskipTests

# our final base image
FROM openjdk:18-slim

# set deployment directory
WORKDIR /TimezoneFollower

# copy over the built artifact from the maven image
COPY --from=maven target/timezonefollower-rest-0.0.1-SNAPSHOT.jar ./

# set the startup command to run your binary
CMD ["java", "-jar", "./timezonefollower-rest-0.0.1-SNAPSHOT.jar"]