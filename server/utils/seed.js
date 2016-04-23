const Post = require('../apis/post/postModel');
const City = require('../apis/city/cityModel');
const User = require('../apis/user/userModel');
const Label = require('../apis/label/labelModel');

const users = require('./fakeData').users;
const posts = require('./fakeData').posts;
const cities = require('./fakeData').cities;
const labels = require('./fakeData').labels;

var savedUsers;
var savedCities;
var savedLabels;

const createDoc = function (Model, doc) {
  return new Promise((resolve, reject) => {
    new Model(doc).save((err, saved) => {
      return err ? reject(err) : resolve(saved);
    });
  });
};

const cleanDB = function () {
  console.log('....cleaning the test DB');

  const models = [Post, City, User, Label];
  return Promise.all(models.map(model => {
    return model.remove().exec();
  }));
};

console.log('Seeding the database.');

const createUser = function () {
  const promises = users.map(user => {
    return createDoc(User, user);
  });

  return Promise.all(promises)
    .then(users => {
      savedUsers = users;
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
      savedCities = cities;
      return cities;
    });
};

const initLabels = function () {
  function concatAll (arr) {
    const cacheArr = [];

    arr.forEach(subArr => {
      subArr.forEach(el => {
        cacheArr.push(el);
      });
    });

    return cacheArr;
  }
  var l = Object.keys(labels)
    .map(key => (labels[key]));
  l = concatAll(l);
  console.log(l);
  const promises = l.map(el => {
    return createDoc(Label, el);
  });

  return Promise.all(promises)
    .then(labels => {
      savedLabels = labels;
      console.log(savedLabels);
      return labels;
    });
};

const addPosts = function () {
  const newPosts = posts.map((post, i) => {
    post.city = savedCities[i]._id;
    post.user = savedUsers[i >= 3 ? 0 : i];

    return createDoc(Post, post);
  });

  return Promise.all(newPosts)
    .then(savedPosts => savedPosts)
    .then(function (savedPosts) {
      console.log(savedUsers);
      console.log(savedLabels);
      console.log(savedCities);
      console.log(savedPosts);
      return 'Seed DB Done...';
    });

};

cleanDB()
  .then(createUser)
  .then(initCity)
  .then(initLabels)
  .then(addPosts)
  .then(console.log)
  .catch(err => {
    console.log(err);
  });
