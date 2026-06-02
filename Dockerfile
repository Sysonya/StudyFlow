# Использовать готовый образ Maven с Java 17 для сборки
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /app

# Копируем абсолютно всё содержимое проекта внутрь контейнера
COPY . .

# Принудительно собираем проект, полностью игнорируя тесты
RUN mvn clean package -Dmaven.test.skip=true -DskipTests

# Этап запуска готового JAR-файла
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]