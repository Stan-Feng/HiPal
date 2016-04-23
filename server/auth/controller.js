const User = require('../apis/user/userModel');
const signToken = require('./auth').signToken;

module.exports = {
  signin (req, res, next) {
    const token = signToken(req.user._id);

    res.json({ token });
  }
};
