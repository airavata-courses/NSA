var Session = require("../models/sessionmodel");
const producer = require("../config/kafkaconfig").producer;

async function createdata(msg) {
  let data = JSON.parse(msg.value);
  try {
    session = new Session(data);
    console.log(data);
    console.log(session)
    let x = await session.save();
    console.log("hey",x)
  } catch (err) {
    console.log(err.message);
  }
}

async function history(msg) {
  let data = JSON.parse(msg.value);
  console.log('retrieved');
  let sessions= Session.find({'userID': data['userID']}, function(err, documents) {
    data={"sessions":documents,
      "userID":data['userID'],
      "userID":data['userID']
      
    }
    console.log('retrieved',data);
    publish(data, 'sessionhistory_ui');
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
module.exports = { createdata,history };