const router = require('express').Router();

router.use('/posts', require('./post/postRoutes'));
router.use('/user', require('./user/userRoutes'));

module.exports = router;
