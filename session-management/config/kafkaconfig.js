var kafka = require("kafka-node");
Consumer = kafka.Consumer;
client = new kafka.KafkaClient();
consumer = new Consumer(
  client,
  [
    { topic: "ui-sessionhistory", partition: 0 },
    { topic: "sessionhistory-ui", partition: 0 },
    { topic: "dataretrieval-sessionmgmt", partition: 0 },
    { topic: "postprocess-sessionmgmt", partition: 0 }
  ],
  {
    autoCommit: false,
    fromOffset: true
  }
);

Producer = kafka.Producer;
producer = new Producer(client);

module.exports = { consumer, producer };
