const User = require('./userModel');
const signToken = require('../../auth/auth').signToken;

module.exports = {
  params (req, res, next, id) {
    User.findById(id)
      .then(user => {
        if (!user) {
          next(new Error('Invalid user ID'));
        } else {
          req.user = user;
          next();
        }
      })
      .catch(err => {
        next(err);
      });
  },

  me (req, res, next) {
    res.json(req.user.toJson());
  },

  get (req, res, next) {
    User.find({})
      .then(users => {
        res.json(users);
      })
      .catch(err => {
        next(err);
      });
  },

  getOne (req, res, next) {
    res.json(req.user);
  },

  post (req, res, next) {
    const newUser = new User(req.body);

    newUser.save((err, user) => {
      if (err) {
        next(err);
      } else {
        const token = signToken(user._id);

        res.json(token);
      }
    });
  },

  put (req, res, next) {
    const user = req.user;
    const update = req.body;

    Object.keys(update).forEach(prop => {
      if (user.hasOwnProperty(prop)) {
        user[prop] = update[prop];
      }
    });

    user.save((err, saved) => {
      if (err) {
        next(err);
      } else {
        res.json(saved);
      }
    });
  },

  delete (req, res, next) {
    req.user.remove((err, removed) => {
      if (err) {
        next(err);
      } else {
        res.json(removed);
      }
    });
  }
};
