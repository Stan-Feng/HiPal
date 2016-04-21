const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const LabelSchema = new Schema({
  name: {
    type: String,
    required: true,
    unique: true
  },

  color: {
    type: String,
    required: true,
    unique: true
  },

  correlation: {
    type: Number,
    required: true
  }
});

module.exports = mongoose.model('label', LabelSchema);
