const MongoClient = require('mongodb').MongoClient;
const assert = require('assert');

const url = 'mongodb://localhost:27017';
const dbName = 'devops';
var myClient = null;
MongoClient.connect(url).then(function(client) {
  console.log("Connected to MongoDB server");
    myClient = client;
});

var mongoDo = function(operationFunc) {
    const db = myClient.db(dbName);
    operationFunc(db)
}

var out = {
    frontIp:'localhost:3000',
    teamIp:'localhost:5555',
    mongoDo: mongoDo
}
module.exports = out;