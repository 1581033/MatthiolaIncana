import { createApp } from 'vue'
import App from './App.vue'
import 'vite-plugin-svg-icons/register'
import Antd from 'ant-design-vue'
import router from './router/index'
import store from './store/index'
import 'ant-design-vue/dist/antd.less'
import './permission'
import { createFromIconfontCN } from '@ant-design/icons-vue'
import './global.less'
import XTabel from 'components/Xtabel/Xtabel.vue'

const MyIcon = createFromIconfontCN({
    scriptUrl: '//at.alicdn.com/t/font_2918935_u3lafdp4e0m.js', // 在 iconfont.cn 上生成
});

createApp(App)
    .component('x-tabel',XTabel)
    .component('MyIcon',MyIcon)
    .use(store)
    .use(router)
    .use(Antd)
    .mount('#app')
