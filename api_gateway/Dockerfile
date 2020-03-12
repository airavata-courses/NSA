FROM maven:3.6.3-jdk-13

ADD KafkaProducer /
RUN mvn clean install

CMD [ "java", "-jar", "./target/api_gateway.jar" ]