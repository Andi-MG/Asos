FROM gradle:7.6-jdk17-alpine AS build
WORKDIR /code
COPY . /code/
RUN gradle build

FROM eclipse-temurin:17
EXPOSE 8080
WORKDIR /app
COPY --from=build /code/build/libs/*.jar .
CMD java -jar *.jar