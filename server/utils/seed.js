const Post = require('../apis/post/postModel');
const City = require('../apis/city/cityModel');
const User = require('../apis/user/userModel');

const cities = require('./cityLabelInit').cities;
const labels = require('./cityLabelInit').labels;

const users = ['Anan Wang', 'Junwen Feng', 'Mengyu Wang', 'Min Cao'];
// const cities = ['Suzhou', 'Shanghai', 'Hangzhou', 'Beijing', 'Guangdong', 'Hujian'];

const posts = [
  { text: 'I went to Suzhou to have fun.'},
  { text: 'Shanghai has a bunch of delicious food.' },
  { text: 'Hangzhou Tea!!!!!!!!!.' },
  { text: 'Beijing Duck is aswesome.' },
  { text: 'Guangdong is very hot.' },
  { text: 'There\'s an island in Hujian called Gulang Yu.' }
];

const createDoc = function (Model, doc) {
  return new Promise((resolve, reject) => {
    new Model(doc).save((err, saved) => {
      return err ? reject(err) : resolve(saved);
    });
  });
};

const cleanDB = function () {
  console.log('....cleaning the test DB');

  const models = [Post, City, User];
  return Promise.all(models.map(model => {
    return model.remove().exec();
  }));
};

console.log('Seeding the database.');

const createUser = function () {
  const promises = users.map(username => {
    return createDoc(User, { username: username, password: 'test123' });
  });

  return Promise.all(promises)
    .then(users => {
      return users;
    }).catch(err => {
      console.log(err);
    });
};

const initCity = function () {
  const promises = cities.map(city => {
    return createDoc(City, city);
  });

  return Promise.all(promises)
    .then(cities => {
      return cities;
    });
};

const addPosts = function (savedCities) {
  const addCity = (post, city) => {
    post.city = city;

    return new Promise((resolve, reject) => {
      post.save((err, saved) => {
        return err ? reject(err) : resolve(saved);
      });
    });
  };

  const newPosts = posts.map((post, i) => {
    post.city = savedCities[i]._id;
    console.log(post);
    return createDoc(Post, post);
  });

  return Promise.all(newPosts)
    .then(savedPosts => {
      return savedPosts;
    })
    .then(function () {
      return 'Seed DB Done...';
    });

};

cleanDB()
  .then(createUser)
  .then(initCity)
  .then(addPosts)
  .then(console.log);
