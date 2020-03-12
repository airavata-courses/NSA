var kafka = require("kafka-node");
Consumer = kafka.Consumer;
client = new kafka.KafkaClient({kafkaHost: 'kafka:9092'});
consumer = new Consumer(
  client,
  [
    { topic: "login-sessionmgmt"},
    { topic: "dataretrieval-sessionmgmt"},
    { topic: "postprocess-sessionmgmt"},
    { topic: "ui-sessionhistory"}
  ],
  {
    autoCommit: false,
    fromOffset: true
  }
);

Producer = kafka.Producer;
producer = new Producer(client);

module.exports = { consumer, producer };
