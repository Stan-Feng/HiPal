const express = require('express');
const app = express();

const config = require('./config');

// Connect to MongoDB
require('mongoose').connect(config.url);

if (config.isSeed) {
  require('./utils/seed');
}

// 404 Error Handling
app.use(function (err, req, res, next) {
  if (err.name === 'UnauthorizedError') {
    console.log(err);
    return res.status(401).send('Invalid Token');
  } else {
    res.status(500).json({ err: err, msg: 'Url Not Found!'});
  }
});

module.exports = app;
