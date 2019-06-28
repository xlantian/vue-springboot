var path = require('path');

module.exports = {
    build: {
        sitEnv: require('./sit.env'),
        prodEnv: require('./prod.env'),
        index: path.resolve(__dirname, '../dist/index.html'),
        assetsRoot: path.resolve(__dirname, '../dist'),
        assetsSubDirectory: '',
        assetsPublicPath: './', //生产环境assetsPublicPath: '/'
        staticPath:'./',        //生产环境 staticPath:''
        productionSourceMap: false,
        productionGzip: false,
        productionGzipExtensions: ['js', 'css'],
        bundleAnalyzerReport: process.env.npm_config_report
    },
    dev: {
        env: require('./dev.env'),
        port: new Date().getFullYear(),
        autoOpenBrowser: true,
        assetsSubDirectory: 'static',
        assetsPublicPath: '/',
        staticPath:'/static/',
        proxyTable: {
            '/api': {
                target: 'http://localhost:8080',
                changeOrigin: true,  //支持跨域
                pathRewrite: {
                  '^/api': ''
                }
              }
        },
        cssSourceMap: false
    }
}
