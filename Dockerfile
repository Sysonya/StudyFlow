# Этап 1: Сборка jar-файла
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Копируем pom.xml и исходный код
COPY pom.xml .
COPY src ./src

# Собираем проект, принудительно пропуская тесты и любые проверки
RUN mvn package -DskipTests=true -Dmaven.main.skip=false

# Этап 2: Запуск приложения
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]