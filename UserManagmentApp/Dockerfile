FROM maven:3.8-openjdk-11 as builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package


FROM tomcat:9.0.48-jdk11-openjdk-slim
COPY --from=builder /app/target/UserManagmentApp-1.0.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war



