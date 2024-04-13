FROM openjdk:17-alpine
COPY build/libs/*.jar app.jar

ENV DATABASE_HOST=host.docker.internal

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.datasource.url=jdbc:mariadb://${DATABASE_HOST}:3306/cine_verse_db"]
