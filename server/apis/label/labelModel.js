// Create by Junwen Feng
const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const LabelSchema = new Schema({
  name: {
    type: String,
    required: true
  },

  correlation: {
    type: Number,
    required: true
  },

  class: {
    type: String,
    required: true
  }
});

module.exports = mongoose.model('label', LabelSchema);
