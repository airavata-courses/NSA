var Session = require("../models/sessionmodel");
const producer = require("../config/kafkaconfig").producer;

async function createdata(msg) {
  let data = JSON.parse(msg.value);
  try {
    session = new Session(data);
    console.log(data);
    console.log(session)
    await session.save();
  } catch (err) {
    console.log(err.message);
  }
}

async function updatetest(msg) {
  let data = JSON.parse(msg.value);
  try {
    session = new Session(data);
    console.log(data);
    console.log(session)
    await session.save();
  } catch (err) {
    console.log(err.message);
  }
}

async function updateinputdata(msg) {
  let data = JSON.parse(msg.value);
  try { 
    console.log(data);
    filter={"user":data["user"]}
    update={"input":data["input"],"output" : "","status":data["status"]}
    let session = await Session.updateOne(filter, update,{new:true}); 
  } catch (err) {
    console.log(err.message);
  }
}

 async function updateoutputandState(msg) {
  let data = JSON.parse(msg.value);
  try {
    console.log(data);
    filter={"userID":data["userID"]}
    update={"output":data["output"],"status":"executed"}
    let session = await Session.updateOne(filter, update,{new:true});
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
module.exports = { createdata,updatetest, updateinputdata, updateoutputandState,history };