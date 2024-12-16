FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY jarsDir/joint-purchase-security-0.0.1.jar /app/joint-purchase-security-0.0.1.jar
EXPOSE 8082
CMD ["java", "-jar", "joint-purchase-security-0.0.1.jar"]