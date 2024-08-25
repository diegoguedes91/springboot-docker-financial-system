# Usando uma imagem Maven baseada no OpenJDK 17
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Usando uma imagem OpenJDK confiável para rodar a aplicação
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/financial-system-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]