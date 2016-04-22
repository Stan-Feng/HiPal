var cities = [
  'Beijing', 'Dalian', 'Guangzhou', 'Hunan', 'Shenzhen',
  'Suzhou', 'Tianjing', 'Xian', 'Xiamen', 'Shanghai',
  'HongKong', 'Maco', 'Harbin', 'Qingdao', 'Wulumuqi',
  'Huhehaite', 'Sanya', 'Guilin', 'Qinghai', 'Luoyang']
  .map(cityName => ({ name: cityName }));

var taste = ['Chilli', 'Dessert', 'Lenmon', 'Salty']
  .map((el, i) => ({ name: el, correlation: i, class: 'taste' }));

var music = ['R&B', 'Jazz', 'Pop', 'Rap', 'Rock', 'Lyric']
  .map((el, i) => ({ name: el, correlation: i, class: 'music'}));

var sleep = [8, 9, 10, 11, 12, 13]
  .map((el, i) => ({ name: el, correlation: i, class: 'sleep' }));

var movie = ['Game of Thrones', 'The Walking Dead', 'The Godfather', 'Inception', 'Birdman']
  .map((el, i) => ({ name: el, correlation: i, class: 'movie' }));

var sports = ['Basketball', 'Soccer', 'Tennis', 'Snooker', 'Baseball']
  .map((el, i) => ({ name: el, correlation: i, class: 'sports' }));

var idol = ['Kobe Bryant', 'G-Dragon', 'Joey', 'Jay Chou', 'Gao Xiaosong']
  .map((el, i) => ({ name: el, correlation: i, class: 'idol' }));

module.exports = {
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
