const router = require('express').Router();
const controller = require('./labelController');

router.route('/')
  .get(controller.get);

module.exports = router;
