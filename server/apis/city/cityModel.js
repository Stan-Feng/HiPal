const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const CitySchema = new Schema({
  name: {
    type: String,
    required: true,
    unique: true
  },

  posts: [{ type: Schema.Types.ObjectId, ref: 'post' }]
});

module.exports = mongoose.model('city', CitySchema);
