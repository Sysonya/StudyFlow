# Шаг 1: Сборка на Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Копируем проект и собираем его без тестов
COPY . .
RUN mvn clean package -DskipTests -Dmaven.test.skip=true

# Шаг 2: Запуск на Java 21
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]