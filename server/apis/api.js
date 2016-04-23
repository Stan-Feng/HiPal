const router = require('express').Router();

router.use('/posts', require('./post/postRoutes'));
router.use('/user', require('./user/userRoutes'));
router.use('/label', require('./label/labelRoutes'));

module.exports = router;
