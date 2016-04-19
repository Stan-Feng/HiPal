const _ = require('lodash');
const devDB = 'mongodb://localhost/hipaldev';
const prodDB = 'mongodb://localhost/hipalprod';

const config = {
  port: process.env.PORT || 3000,
  // 10 days
  secret: {
    jwt: process.env.JWT || 'whatever secrets',
    expireTime: 24 * 60 * 60 * 10
  }
};

config.env = process.env.NODE_ENV || 'dev';

var dbConfig;
if (config.env === 'dev') {
  dbConfig = {
    url: devDB,
    isSeed: true
  };
} else if (config.env === 'prod') {
  dbConfig = {
    url: prodDB,
    isSeed: false
  };
}

module.exports = _.merge(config, dbConfig);
