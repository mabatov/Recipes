FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY build/libs/*.jar recipebook.jar
ENTRYPOINT ["java","-jar","/recipebook.jar"]