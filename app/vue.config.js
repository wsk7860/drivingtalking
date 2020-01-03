const resolve = dir => require('path').join(__dirname, dir)

module.exports = {
  publicPath: './',

  devServer: {
    overlay: {
      warnings: true,
      errors: true
    }
  },

  css: {
    loaderOptions: {
      stylus: {
        'resolve url': true,
        'import': [
          './src/assets/stylus/theme',
          './src/assets/stylus/variable',
          './src/assets/stylus/base'

        ]
      }
    }
  },

  pluginOptions: {
    'cube-ui': {
      postCompile: true,
      theme: true
    }
  },

  outputDir: 'package/www',
  lintOnSave: true,
  productionSourceMap: true,
  filenameHashing: true
}
