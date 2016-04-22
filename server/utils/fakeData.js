const cities = [
  'Beijing', 'Dalian', 'Guangzhou', 'Hunan', 'Shenzhen',
  'Suzhou', 'Tianjing', 'Xian', 'Xiamen', 'Shanghai',
  'HongKong', 'Maco', 'Harbin', 'Qingdao', 'Wulumuqi',
  'Huhehaite', 'Sanya', 'Guilin', 'Qinghai', 'Luoyang']
  .map(cityName => ({ name: cityName }));

const taste = ['Chilli', 'Dessert', 'Lenmon', 'Salty']
  .map((el, i) => ({ name: el, correlation: i, class: 'taste' }));

const music = ['R&B', 'Jazz', 'Pop', 'Rap', 'Rock', 'Lyric']
  .map((el, i) => ({ name: el, correlation: i, class: 'music'}));

const sleep = [8, 9, 10, 11, 12, 13]
  .map((el, i) => ({ name: el, correlation: i, class: 'sleep' }));

const movie = ['Game of Thrones', 'The Walking Dead', 'The Godfather', 'Inception', 'Birdman']
  .map((el, i) => ({ name: el, correlation: i, class: 'movie' }));

const sports = ['Basketball', 'Soccer', 'Tennis', 'Snooker', 'Baseball']
  .map((el, i) => ({ name: el, correlation: i, class: 'sports' }));

const idol = ['Kobe Bryant', 'G-Dragon', 'Joey', 'Jay Chou', 'Gao Xiaosong']
  .map((el, i) => ({ name: el, correlation: i, class: 'idol' }));

const users = ['Anan Wang', 'Junwen Feng', 'Mengyu Wang', 'Min Cao']
  .map((name, i) => ({
    username: name,
    nickname: name,
    password: 'test123',
    signature: 'I am ' + name,
    gender: (i % 2) === 0 ? 'Female' : 'Male',
    city: 'Suzhou',
    age: 21
  }));

const posts = [
  { text: 'I went to Suzhou to have fun.' },
  { text: 'Shanghai has a bunch of delicious food.' },
  { text: 'Hangzhou Tea!!!!!!!!!.' },
  { text: 'Beijing Duck is aswesome.' },
  { text: 'Guangdong is very hot.' },
  { text: 'There\'s an island in Hujian called Gulang Yu.' }
];

module.exports = {
  posts,
  users,
  cities,
  labels: {
    taste,
    music,
    sleep,
    movie,
    sports,
    idol
  }
};
