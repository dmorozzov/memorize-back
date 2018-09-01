import HtmlWebpackPlugin from "html-webpack-plugin";
import webpack from "webpack";

const env = process.env.NODE_ENV || 'production';
console.log('>> Building ver.: ' + env);

let entry = __dirname + '/app/index.js';
let output = {
    path: __dirname + '/dist',
    filename: "app.min.js",
};
let devtool = 'sourcemaps';
let plugins = [
    new webpack.ProvidePlugin({
        $: "jquery",
        jQuery: "jquery",
        "window.jQuery": "jquery"
    }),
    new HtmlWebpackPlugin({
        template: "./templates/index.html",
        inject: "body"
    })
];

if (env === 'development') {
    plugins.push(new webpack.HotModuleReplacementPlugin());
}

module.exports = {
    entry: entry,
    mode: env,
    output: output,
    devtool: devtool,
    module: {
        rules: [
            {
                test: /.jsx?$/,
                loader: "babel-loader",
                exclude: /node_modules/
            },
            {
                test: /\.css$/,
                loader: "style-loader!css-loader"
            },
            {
                test: /\.(png|woff|woff2|eot|ttf|svg)$/,
                loader: "url-loader?limit=100000"
            }
        ]
    },
    plugins: plugins
};