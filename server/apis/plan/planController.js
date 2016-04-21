const Plan = require('./planModel');
const User = require('../user/userModel');

module.exports = {
  params (req, res, next, userToken) {
    req.headers.authorization = 'Bearer ' + userToken;
    next();
  },

  post (req, res, next) {
    
  },

  getPlanByUser (req, res, next) {
    Plan.find({ user: req.user._id })
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
