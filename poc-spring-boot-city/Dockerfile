FROM maven:3.6.3-openjdk-8-slim
MAINTAINER Celso Agra <celso.agra@compasso.com.br>

ENV DB_IP 127.0.0.1
ENV DB_PORT 5432
ENV DB_BASE postgres
ENV DB_USER postgres
ENV DB_PASS postgres

RUN mkdir /app && mkdir /app/.m2 && mkdir /app/.m2/repository && mkdir /app/src 

COPY ./src /app/src
COPY ./pom.xml /app

WORKDIR /app

RUN mvn clean package

ENV MAVEN_CONFIG=/app/.m2
EXPOSE 8080

CMD ["java", "-jar", "-Dspring.profiles.active=docker", "/app/target/app-0.0.1-SNAPSHOT.jar"]