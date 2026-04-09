FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

#COPY ./target/LearnDockerManual-0.0.1-SNAPSHOT.jar app.jar

#EXPOSE 9099

#ENTRYPOINT ["java","-jar","app.jar"]

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]