var Session = require("../models/sessionmodel");
const producer = require("../config/kafkaconfig").producer;

async function createdata(msg) {
  let data = msg;
  try {
    console.log(msg)
    session = new Session(data);
    session.save();
    return "Record Stored";
  } catch (err) {
    console.log(err.message);
  }
}

async function history_test(msg) {
  let data = msg;
  let sessions= Session.find({'userID': msg}, function(err, documents) {
    data={"sessions":documents};
  });
  return "Record Retrieved";
}

async function history(msg) {

  let val = msg["userID"];
  let sessions= Session.find({'userID': val}, function(err, documents) {
    data={"sessions":documents
    }
    console.log('retrieved',data);
    publish(data, 'sessionhistory-ui');
  }); 
}

function publish(msg, topicName) {
  msg = JSON.stringify(msg);
  let payloads = [
    {
      topic: topicName,
      messages: msg
    }
  ];
  producer.send(payloads, (error, data) => {
    if (error) {
      console.log(error);
    } else {
      console.log("sent new");
    }
  });
}
module.exports = { createdata,history,history_test};
