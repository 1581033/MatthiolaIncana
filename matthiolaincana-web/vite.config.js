import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import viteSvgIcons from 'vite-plugin-svg-icons'
import path from 'path'

export default defineConfig({
  server: {
    host: process.env.VITE_API_DOMAIN,
    port: 8082,
    open: false
  },
  resolve: {
    alias: {
      '@': path.resolve(__dirname, 'src'),
      'router': path.resolve(__dirname, 'src/router'),
      'components': path.resolve(__dirname, 'src/components'),
      'styles': path.resolve(__dirname, 'src/styles'),
      'views': path.resolve(__dirname, 'src/views'),
      'utils': path.resolve(__dirname, 'src/utils'),
      'apis': path.resolve(__dirname, 'src/apis')
    }
  },
  css: {
    preprocessorOptions: {
      less: {
        modifyVars: {
          hack: `true; @import (reference) "${path.resolve('src/global.less')}";`,
        },
        javascriptEnabled: true
      }
    }
  },
  plugins: [
      vue(),
      viteSvgIcons({
        iconDirs: [path.resolve(process.cwd(), 'src/icons/svg')],
        symbolId: 'icon-[dir]-[name]',
      })
  ],
  build: {
    outDir: 'dist',
    assetsDir: 'assets',
    assetsInlineLimit: '4096',
    cssCodeSplit: true,
    sourcemap: false,
    manifest: false
  }
})
