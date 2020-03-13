const dotenv = require("dotenv");
var express = require('express');
var cookieParser = require('cookie-parser');
var session = require('express-session');
var bodyParser = require('body-parser')
var cors = require('cors')

main = () => {
  const consumer = require('./config/kafkaconfig').consumer;
  const calls = require('./sessionservice/sessioncontroller');

  dotenv.config()
  connectToMongo = require("./config/dbconfig");
  connectToMongo();

  var sess = express();

  sess.use(bodyParser());
  sess.use(cookieParser());
  sess.use(cors());
  // sess.use(session({secret: "secret!"}));

  consumer.on('message', function (message) {

    if (message.topic == 'login-sessionmgmt') {
      calls.createdata(message)

    } else if (message.topic == 'dataretrieval-sessionmgmt') {
      calls.createdata(message)


    } else if (message.topic == 'postprocess-sessionmgmt') {
      calls.createdata(message)

    }
    else if (message.topic == 'ui-sessionhistory') {
      calls.history(message)

    }

    sess.get('/', function (req, res) {
      if (req.session.page_views) {
        req.session.page_views++;
        res.send("You visited this page " + req.session.page_views + " times");
      } else {
        req.session.page_views = 1;
        res.send("Welcome to this page for the first time!");
      }
    });

  });
  sess.listen(process.env.PORT);
 
}


setTimeout(main, 60000);
