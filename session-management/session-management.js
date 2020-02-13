var express = require('express');
var cookieParser = require('cookie-parser');
var session = require('express-session');
var mongoose = require('mongoose');
var bodyParser = require('body-parser')
var cors = require('cors')
const consumer = require('./config/kafkaconfig').consumer;
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
    
    //if (message.topic == 'login-sessionmgmt') {
    //  calls.createdata(message);
   // }
    //if (message.topic == 'dataretrieval-sessionmgmt') {
    //  calls.updateinputdata(message)

    //}
    if (message.topic == 'dataretrieval-sessionmgmt') {
      calls.updatetest(message)
    }
      else if (message.topic == 'postprocess-sessionmgmt') {
      calls.updateoutputandState(message)
    }
     else if (message.topic == 'sessionhistory-ui'){
       calls.history(message)
     }
  });

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
