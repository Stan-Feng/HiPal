const express = require('express');
const app = express();

const api = require('./apis/api');
const auth = require('./auth/routes');

const config = require('./config');

// Connect to MongoDB
require('mongoose').connect(config.url);

if (config.isSeed) {
  require('./utils/seed');
}

// Setup Middlewares
require('./middlewares/appMiddleware')(app);

// Routes
app.use('/api', api);
app.use('auth', auth);

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
