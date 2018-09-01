import webpackDevServer from "webpack-dev-server";
import webpack from "webpack";

const config = require('./webpack.config.babel.js');
const options = {
    contentBase: './dist',
    hot: true,
    host: 'localhost',
    proxy: {
        '/api': 'http://localhost:8080/'
    }
};

webpackDevServer.addDevServerEntrypoints(config, options);
const compiler = webpack(config);
const server = new webpackDevServer(compiler, options);

server.listen(5000, 'localhost', () => {
    console.log('dev server listening on port 5000');
});