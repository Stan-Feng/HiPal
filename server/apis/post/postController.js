const Post = require('./postModel');
const City = require('../city/cityModel');

module.exports = {

  params (req, res, next, cityName) {
    City.findOne({ name: cityName })
      .then(city => {
        Post.find({ city: city._id })
          .then(posts => {
            if (!posts) {
              next(new Error('Invalid City Name'));
            } else {
              req.posts = posts;
              next();
            }
          }).catch(err => {
            next(err);
          });
      })
      .catch(err => {
        next(err);
      });
  },

  get (req, res, next) {
    Post.find({})
      .populate('city')
      .exec()
      .then(posts => {
        res.json(posts);
      })
      .catch(err => {
        next(err);
      });
  },

  post (req, res, next) {
    var newPost = req.body;

    Post.create(newPost)
      .then(post => {
        res.json(post);
      })
      .catch(err => {
        next(err);
      });
  },

  getSameCityPosts (req, res, next) {
    res.json({ posts: req.posts });
  }
};
