const router = require('express').Router();

router.use('/posts', require('./post/postRoutes'));
router.use('/user', require('./user/userRoutes'));
router.use('/label', require('./label/labelRoutes'));
router.use('/plan', require('./plan/planRoutes'));

module.exports = router;
