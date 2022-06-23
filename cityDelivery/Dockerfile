FROM openjdk:11-jdk-slim
COPY backend/target/*.jar app.jar
EXPOSE 8088
ENTRYPOINT ["java","-jar","app.jar"]
