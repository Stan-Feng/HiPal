const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const PostSchema = new Schema({
  text: {
    type: String,
    required: true
  },

  image: {
    type: String,
    required: false
  },

  city: {
    type: Schema.Types.ObjectId,
    ref: 'city',
    required: true
  }
});

module.exports = mongoose.model('post', PostSchema);
