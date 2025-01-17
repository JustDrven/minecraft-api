FROM openjdk:17-alpine

COPY . .

EXPOSE 8080

ENV API_KEY=test

ENTRYPOINT ["java", "-jar", "release/app.jar"]

