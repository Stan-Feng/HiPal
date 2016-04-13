const config = require('./server/config');
const app = require('./server/app');

app.listen(config.port, function (req, res) {
  console.log(`Server is running at port ${config.port}.....`);
});
