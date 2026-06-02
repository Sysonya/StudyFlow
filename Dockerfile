# Stage 1: Сборка приложения из исходников
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Собираем .jar файл, пропуская тесты ради скорости сборки
RUN mvn clean package -DskipTests

# Stage 2: Легковесный запуск
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]