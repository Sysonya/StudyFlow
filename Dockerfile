# Этап 1: Сборка проекта (здесь мы создаем ту самую папку target)
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Этап 2: Запуск (здесь мы забираем готовый файл из первого этапа)
FROM eclipse-temurin:17-jre
WORKDIR /app
# Копируем любой jar файл из папки target первого этапа
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]