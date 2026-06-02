# Stage 1: Сборка
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Сначала копируем pom.xml и загружаем зависимости
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Копируем исходный код и собираем проект без запуска тестов (чтобы сборка не падала в облаке)
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Запуск
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]