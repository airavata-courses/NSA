const assert = require("assert");
const calls = require("../sessionservice/sessioncontroller");

connectToMongo = require("../config/dbconfig");
connectToMongo();

var test_input = {
  userID: "test-user",
  day: "test_24",
  month: "test_10",
  year: "test_2018",
  radarID: "test_radar",
  output: "test_image",
  jobstatus: "test_status",
  jobtype: "test_session",
};

describe("Create mongo session document of type test_input", () => {
  it("Should successfully create a document in Mongo Collection", done => {
    calls.createdata(test_input).then(data => {
      assert(data, "Record Stored");
      done();
    });
  });
});

describe("Retrieve record from collection", () => {
  it("Should successfully retrieve a document in Mongo Collection", done => {
    calls.history_test("test-user").then(data => {
      assert(data, "Record Retrieved");
      done();
    });
  });
});