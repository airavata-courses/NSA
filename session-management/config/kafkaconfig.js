var kafka = require("kafka-node");
const ConsumerGroup = kafka.ConsumerGroup
client = new kafka.KafkaClient({kafkaHost: 'kafka:9092'});

const consumerOptions = {
  kafkaHost: 'kafka:9092',
  autoCommit: false,
  encoding: "utf8",
  groupId: "session-management",
  protocol: ["roundrobin"],
  fromOffset: "latest"
};

const consumer = new ConsumerGroup(consumerOptions, [
  "login-sessionmgmt",
  "dataretrieval-sessionmgmt",
  "postprocess-sessionmgmt",
  "ui-sessionhistory"
]);


Producer = kafka.Producer;
producer = new Producer(client);

module.exports = { consumer, producer };
