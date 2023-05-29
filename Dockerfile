FROM openjdk:8
LABEL authors="joao fellype"
ADD ./target/delivery-0.0.1-SNAPSHOT.jar delivery-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar", "delivery-0.0.1-SNAPSHOT.jar"]