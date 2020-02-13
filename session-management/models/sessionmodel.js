const mongoose = require("mongoose");
const uuidv1 = require("uuid/v1");
const crypto = require("crypto");
const { ObjectId } = mongoose.Schema;

var sessionSchema = new mongoose.Schema({
  sessID: {
        type: ObjectId,
        required: false
    },
  userID: {
    type: String,
    required: true
  },
  input: {
    type: JSON,
    required: true
  },
  output: {
    type: String
  },
  status:{
    type: String,
    default:'None'
  },
  timeStamp: {
    type: Date,
    default: Date.now()
}
});
sessionSchema.set('timestamps', true);
module.exports = mongoose.model('session-mgmt', sessionSchema);
