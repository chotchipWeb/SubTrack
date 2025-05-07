FROM maven:3.8.5-amazoncorretto-17 as build

COPY src /src
COPY pom.xml /
RUN mvn -f /pom.xml clean package

FROM bellsoft/liberica-openjdk-alpine:17

RUN adduser --system spring-boot && addgroup --system spring-boot && adduser spring-boot spring-boot
USER spring-boot

COPY --from=build /target/*.jar application.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "application.jar"]