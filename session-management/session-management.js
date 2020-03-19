const dotenv = require("dotenv");
const express = require('express');
const cookieParser = require('cookie-parser');
const bodyParser = require('body-parser')
const cors = require('cors')
const sess = express();
sess.use(bodyParser.urlencoded({ extended: false }));
sess.use(cookieParser());
sess.use(cors());

main = () => {
const consumer = require('./config/kafkaconfig').consumer;
const calls = require('./sessionservice/sessioncontroller');

dotenv.config()
connectToMongo = require("./config/dbconfig");

connectToMongo();

consumer.on('message', function (message) {
  console.log('Inside consumer');

  if (message.topic == 'login-sessionmgmt') {
    message = JSON.parse(message.value)
    calls.createdata(message)

  } else if (message.topic == 'dataretrieval-sessionmgmt') {
    message = JSON.parse(message.value)
    calls.createdata(message)


  } else if (message.topic == 'postprocess-sessionmgmt') {
    message = JSON.parse(message.value)
    calls.createdata(message)

  }
  else if (message.topic == 'ui-sessionhistory') {
    message = JSON.parse(message.value)
    calls.history(message)
  }
});

sess.listen(process.env.PORT,function(){
  console.log("server is running on port 3002");
});
}
setTimeout(main, 10000);

