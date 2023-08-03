// extra-webpack.config.js
const fs = require('fs');
const util = require('util');
const webpack = require('webpack');

const readFile = util.promisify(fs.readFile);

module.exports = readFile('./LICENSE', {
  encoding: 'utf-8',
}).then(license => ({
  plugins: [new webpack.BannerPlugin(license)],
}));