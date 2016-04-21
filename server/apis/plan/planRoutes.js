const route = require('express').Router();
const controller = require('./planController');

const auth = require('../../auth/auth');
const checkUser = [auth.decodeToken(), auth.getFreshUser()];

router.param('token', controller.params);

router.route('/')
  .post(controller.post);

router.route('/:token')
  .get(checkUser, controller.getPlanByUser);

module.exports = router;
