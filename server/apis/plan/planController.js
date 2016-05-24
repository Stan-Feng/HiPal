// Create by Junwen Feng
const Plan = require('./planModel');
const User = require('../plan/planModel');

module.exports = {
  params (req, res, next, userToken) {
    req.headers.authorization = 'Bearer ' + userToken;
    next();
  },

  get (req, res, next) {
    Plan.find({})
      .populate('city')
      .populate('user')
      .populate('labels')
      .then(plans => {
        res.json(plans);
      })
      .catch(err => {
        next(err);
      });
  },

  post (req, res, next) {

  },

  getPlanByUser (req, res, next) {
    Plan.find({ user: req.user._id })
      .populate('city')
      .populate('user')
      .populate('labels')
      .then(plans => {
        if (plans.length === 0) {
          next(new Error('No plan found'));
        }

        res.json({ plans });
      })
      .catch(err => {
        next(err);
      });
  }
};
