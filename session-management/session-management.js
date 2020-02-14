var express = require('express');
var cookieParser = require('cookie-parser');
var session = require('express-session');
var mongoose = require('mongoose');
var bodyParser = require('body-parser')
var cors = require('cors')
const consumer = require('./config/kafkaconfig').consumer;
const producer = require("./config/kafkaconfig").producer;
const calls= require('./sessionservice/sessioncontroller');

var Session = require('./models/sessionmodel');

var sess = express();

sess.use(bodyParser());
sess.use(cookieParser());
sess.use(cors());
sess.use(session({secret: "secret!"}));

var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://localhost:27017/";

MongoClient.connect(url, function(err, db) {
  if (err) throw err;
  var dbo = db.db("user_session_history");
  if (err) throw err;
  console.log("MongoDb connected");


consumer.on('message', function(message) {
    
    if (message.topic == 'postprocess-sessionmgmt') {
      var msg= JSON.parse(message.value);
      dbo.collection("session").insertOne(msg,function(err,res){
        if (err) throw err;
        console.log('1 doc inserted')
      });
    }
     else if (message.topic == 'ui-sessionhistory'){
       console.log("inside ui-sess");
       let data=JSON.parse(message.value);
       dbo.collection('session'),function(err,collection)
       {
         let myoutput = collection.find({userID:data["userID"]});
         console.log('retrieved',data);
         publish(myoutput, 'sessionhistory_ui');
       }
     }
  });

  function publish(msg, topicName) {
    msg = JSON.stringify(msg);
    let payloads = [
      {
        topic: 'sessionhistory-ui',
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

sess.get('/', function(req, res){
   if(req.session.page_views){
      req.session.page_views++;
      res.send("You visited this page " + req.session.page_views + " times");
   } else {
      req.session.page_views = 1;
      res.send("Welcome to this page for the first time!");
   }
});

});
sess.listen(3001);
