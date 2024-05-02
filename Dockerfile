FROM eclipse-temurin:17
WORKDIR /home
COPY ./target/c322-final-backend-0.0.1-SNAPSHOT.jar final.jar
ENTRYPOINT ["java", "-jar", "final.jar"]