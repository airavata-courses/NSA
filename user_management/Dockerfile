FROM maven:3.6.3-jdk-13

ADD KafkaConsumer /
# WORKDIR /KafkaConsumer
RUN mvn clean install

CMD [ "java", "-jar", "./target/user_management.jar" ]