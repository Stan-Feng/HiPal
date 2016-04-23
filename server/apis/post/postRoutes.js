const router = require('express').Router();
const controller = require('./postController');

router.param('cityName', controller.params);

router.route('/')
  .get(controller.get)
  .post(controller.post);

router.route('/:cityName')
  .get(controller.getSameCityPosts);

module.exports = router;
