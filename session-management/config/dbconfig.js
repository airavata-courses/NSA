let mongoose = require("mongoose");

const dbPath =
  "mongodb+srv://" +
  process.env.DB_USER +
  ":" +
  process.env.DB_PASS +
  "@cluster0-pnb3d.gcp.mongodb.net/test?retryWrites=true&w=majority";

const connectToMongo = async () => {
    try {
      console.log("Connecting to Mongo DB !!");

      await mongoose.connect(dbPath, {
        useCreateIndex: true,
        useNewUrlParser: true,
        useUnifiedTopology: true,
        dbName: "session-mgmt"
      })
    } catch (e) {
      console.log(e);
      throw e;
    }
  };
  

module.exports = connectToMongo;
