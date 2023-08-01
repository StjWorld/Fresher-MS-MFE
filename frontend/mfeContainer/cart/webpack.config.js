const HtmlWebPackPlugin = require('html-webpack-plugin')
const ModuleFederationPlugin = require('webpack/lib/container/ModuleFederationPlugin')
module.exports = {
    mode: "development",
    devServer: { port: 4200 },
    plugins: [new HtmlWebPackPlugin({ template: 'src/app/index.html' }),
    new ModuleFederationPlugin({
        name: 'Cart',
        filename: 'remoteEntry.js',
        exposes: { './CartIndex': './src/index' }
    })]
}