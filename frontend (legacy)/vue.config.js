const { defineConfig } = require('@vue/cli-service')

module.exports = defineConfig({
  transpileDependencies: true,
  publicPath: '',
  devServer: {
    proxy: 'http://localhost:8081',
  }
  // publicPath: process.env.NODE_ENV === 'production'
  //   ? '/' + process.env.CI_PROJECT_NAME + '/'
  //   : '/'
})
//
// module.exports = {
//   publicPath: process.env.NODE_ENV === 'production'
//     ? '/' + process.env.CI_PROJECT_NAME + '/'
//     : '/'
// }
