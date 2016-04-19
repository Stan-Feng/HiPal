const jwt = require('jsonwebtoken');
const expressJWT = require('express-jwt');

const config = require('../config');
const checkToken = expressJWT({ secret: config.secret.jwt });

const User = require('../apis/user/userModel');

module.exports = {
  signToken (id) {
    return jwt.sign(
      { _id: id },
      config.secret.jwt,
      { expiresIn: config.secret.expiresTime }
    );
  },

  decodeToken () {
    return (req, res, next) => {
      if (req.query && req.query.hasOwrnProperty('access_token')) {
        req.headers.authorization = 'Bearer ' + req.headers.access_token;
      }

      checkToken(req, res, next);
    };
  },

  getFreshUser () {
    return (req, res, next) => {
      User.findById(req.user._id)
        .then(user => {
          if (!user) {
            res.status(401).send('UnauthorizedError');
          }

          req.user = user;
          next();
        })
        .catch(err => {
          next(err);
        });
    };
  },

  verifyUser () {
    return (req, res, next) => {
      const username = req.body.username;
      const password = req.body.password;
      console.log(username);
      if (!username || !password) {
        res.status(400).send('Username and password are required.');

        return;
      }

      User.findOne({ username })
        .then(user => {
          if (!user) {
            res.status(401).send('Username not found.');
          } else if (!user.authenticate(password)) {
            res.status(401).send('Invalid password.');
          } else {
            req.user = user;
            next();
          }
        })
        .catch(err => {
          console.log(err);
          next(err);
        });
    };
  }
};
