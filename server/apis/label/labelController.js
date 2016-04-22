const Label = require('./labelModel');

module.exports = {
  get (req, res, next) {
    Label.find()
      .then(labels => {
        res.json(labels);
      })
      .catch(err => {
        next(err);
      });
  }
};
