const router = require('express').Router();

router.use('/posts', require('./post/postRoutes'));

module.exports = router;
