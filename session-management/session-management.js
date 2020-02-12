var express = require('express');
var cookieParser = require('cookie-parser');
var session = require('express-session');
var mongoose = require('mongoose');
var bodyParser = require('body-parser')
var cors = require('cors')

var Session = require('./models/sessionmodel')

var sess = express();

sess.use(bodyParser())
sess.use(cookieParser());
sess.use(cors())
sess.use(session({secret: "secret!"}));

var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://localhost:27017/";

MongoClient.connect(url, function(err, db) {
  if (err) throw err;
  var dbo = db.db("weather_prediction");
  var myobj = { name: "User", status: "Success" };
  dbo.collection("session").insertOne(myobj, function(err, res) {
    if (err) throw err;
    console.log("1 document inserted");
    db.close();
  });
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


sess.listen(3001);
