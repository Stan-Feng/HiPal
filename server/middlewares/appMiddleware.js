// Create by Junwen Feng
const path = require('path');
const morgan = require('morgan');
const express = require('express');
const bodyParser = require('body-parser');

module.exports = function (app) {
  // Static Files
  app.use(express.static('client'));

  // Log Request
  app.use(morgan('dev'));

  // Parsers
  app.use(bodyParser.urlencoded({ extended: true }));
  app.use(bodyParser.json());

};
