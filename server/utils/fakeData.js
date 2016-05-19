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
    age: 21,
    wechatID: name.split(' ')[0] + '_wechatID'
  }));

const posts = [
  { text: 'I went to Suzhou to have fun.' },
  { text: 'Shanghai has a bunch of delicious food.' },
  { text: 'Hangzhou Tea!!!!!!!!!.' },
  { text: 'Beijing Duck is aswesome.' },
  { text: 'Guangdong is very hot.' },
  { text: 'There\'s an island in Hujian called Gulang Yu.' }
];

const plans = [
  { startDate: new Date(), endDate: new Date(2016, 9, 2), isArchieved: false },
  { startDate: new Date(2016, 5, 1), endDate: new Date(2016, 5, 4), isArchieved: false },
  { startDate: new Date(2016, 5, 2), endDate: new Date(2016, 5, 6), isArchieved: false },
  { startDate: new Date(2016, 5, 3), endDate: new Date(2016, 5, 8), isArchieved: false },
  { startDate: new Date(2016, 5, 4), endDate: new Date(2016, 5, 10), isArchieved: false },
  { startDate: new Date(2016, 5, 5), endDate: new Date(2016, 5, 12), isArchieved: false },
  { startDate: new Date(2016, 5, 6), endDate: new Date(2016, 5, 14), isArchieved: false },
  { startDate: new Date(2016, 5, 7), endDate: new Date(2016, 5, 16), isArchieved: false },
  { startDate: new Date(2016, 5, 8), endDate: new Date(2016, 5, 18), isArchieved: false },
  { startDate: new Date(2016, 5, 9), endDate: new Date(2016, 5, 22), isArchieved: false },
  { startDate: new Date(2016, 6, 10), endDate: new Date(2016, 6, 12), isArchieved: false },
  { startDate: new Date(2016, 6, 11), endDate: new Date(2016, 6, 17), isArchieved: false },
  { startDate: new Date(2016, 6, 13), endDate: new Date(2016, 6, 20), isArchieved: false },
  { startDate: new Date(2016, 6, 17), endDate: new Date(2016, 6, 20), isArchieved: false },
  { startDate: new Date(2016, 6, 12), endDate: new Date(2016, 6, 20), isArchieved: false },
  { startDate: new Date(2016, 6, 21), endDate: new Date(2016, 6, 25), isArchieved: false },
  { startDate: new Date(2016, 6, 24), endDate: new Date(2016, 6, 28), isArchieved: false },
  { startDate: new Date(2016, 6, 27), endDate: new Date(2016, 6, 30), isArchieved: false },
  { startDate: new Date(2016, 7, 12), endDate: new Date(2016, 8, 2), isArchieved: false },
  { startDate: new Date(2016, 7, 14), endDate: new Date(2016, 8, 4), isArchieved: false },
  { startDate: new Date(2016, 7, 16), endDate: new Date(2016, 8, 6), isArchieved: false },
  { startDate: new Date(2016, 7, 18), endDate: new Date(2016, 8, 8), isArchieved: false },
  { startDate: new Date(2016, 7, 20), endDate: new Date(2016, 8, 10), isArchieved: false },
  { startDate: new Date(2016, 7, 22), endDate: new Date(2016, 8, 12), isArchieved: false }
];

module.exports = {
  plans,
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
