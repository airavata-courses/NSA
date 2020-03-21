const mongoose = require("mongoose");
const { ObjectId } = mongoose.Schema;

var session = new mongoose.Schema({
  sessID: {
        type: ObjectId,
        required: false
    },
  userID: {
    type: String,
    required: true
  },
  day: {
    type: String,
    required: false
  },
  month: {
    type: String,
    required: false
  },
  year: {
    type: String,
    required: false
  },
  radarID: {
    type: String,
    required: false
  },
  output: {
    type: String
  },
  jobstatus:{
    type: String,
    default:'None'
  },
  jobtype:{
    type: String,
    default:'None'
  }
});
session.set('timestamps', true);
module.exports = mongoose.model('session_data', session);
