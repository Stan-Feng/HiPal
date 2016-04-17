const Post = require('./postModel');

module.exports = {

  params (req, res, next, id) {
    Post.findById(id)
      .then(post => {
        if (!post) {
          next(new Error('Invalid Post Id.'));
        } else {
          req.post = post;
          next();
        }
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

  getOne (req, res, next) {
    var post = req.body;
    res.json(req.body);
  }
};
