const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const PlanSchema = new Schema({
  startDate: {
    type: Date,
    required: true
  },

  endDate: {
    type: Date,
    required: true
  },

  name: {
    type: String,
    required: true
  },

  user: {
    type: Schema.Types.ObjectId,
    ref: 'user',
    required: true
  },

  city: {
    type: Schema.Types.ObjectId,
    ref: 'city',
    required: true
  },

  isArchieved: {
    type: Boolean,
    required: true
  },

  labels: [{ type: Schema.Types.ObjectId, ref: 'label', required: true }]

});

module.exports = mongoose.model('plan', PlanSchema);
