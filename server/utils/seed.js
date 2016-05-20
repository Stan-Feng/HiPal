const Post = require('../apis/post/postModel');
const City = require('../apis/city/cityModel');
const User = require('../apis/user/userModel');
const Plan = require('../apis/plan/planModel');
const Label = require('../apis/label/labelModel');

const users = require('./fakeData').users;
const posts = require('./fakeData').posts;
const cities = require('./fakeData').cities;
const labels = require('./fakeData').labels;
const plans = require('./fakeData').plans;

var savedUsers;
var savedCities;
var savedLabels;
var savedPlans;

const createDoc = function (Model, doc) {
  return new Promise((resolve, reject) => {
    new Model(doc).save((err, saved) => {
      return err ? reject(err) : resolve(saved);
    });
  });
};

const cleanDB = function () {
  console.log('....cleaning the test DB');

  const models = [Post, City, User, Label, Plan];
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

  const promises = l.map(el => {
    return createDoc(Label, el);
  });

  return Promise.all(promises)
    .then(labels => {
      savedLabels = labels;
      return labels;
    });
};

const createPlan = function () {
  const newPlans = plans.map((plan, i) => {
    plan.city = savedCities[i % 20];
    plan.user = savedUsers[i % 4];
    plan.labels = [];
    plan.labels.push(savedLabels[i % 11]);
    plan.name = `From ${plan.startDate.toString()} to ${plan.endDate.toString()}.`;

    return createDoc(Plan, plan);
  });

  return Promise.all(newPlans)
    .then(plans => {
      savedPlans = plans;

      return plans;
    })
    .catch(err => {
      throw err;
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
      return 'Seed DB Done...';
    });

};

cleanDB()
  .then(createUser)
  .then(initCity)
  .then(initLabels)
  .then(createPlan)
  .then(addPosts)
  .then(console.log)
  .catch(err => {
    console.log(err);
  });
