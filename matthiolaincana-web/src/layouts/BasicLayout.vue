<template>
  <pro-layout :settings="settings">
    <template #header>
      <right-content/>
    </template>
    <a-back-top />
    <router-view/>
  </pro-layout>
</template>
<script setup>
import { useStore } from 'vuex'
import ProLayout from 'components/ProLayout/prolayout.vue'
import defaultSettings from '@/config/defaultSettings'
import { CONTENT_WIDTH_TYPE, SIDEBAR_TYPE, TOGGLE_MOBILE_TYPE } from '@/store/mutation-types'
import { ref,reactive, watch } from 'vue'
import RightContent from 'components/GlobalHeader/RightContent.vue'
const settings = reactive({
  icon: defaultSettings.iconfontUrl,
  title: defaultSettings.title,
  // 布局类型
  layout: defaultSettings.layout,
  // CONTENT_WIDTH_TYPE
  contentWidth: defaultSettings.layout === 'sidemenu' ? CONTENT_WIDTH_TYPE.Fluid : defaultSettings.contentWidth,
  // 主题 'dark' | 'light'
  theme: defaultSettings.navTheme,
  // 主色调
  primaryColor: defaultSettings.primaryColor,
  fixedHeader: defaultSettings.fixedHeader,
  fixSiderbar: defaultSettings.fixSiderbar,
  colorWeak: defaultSettings.colorWeak,

  hideHintAlert: false,
  hideCopyButton: false
})

const isMobile = ref(false)
const store = useStore()

watch(() => 'collapsed', () => {
  store.commit(SIDEBAR_TYPE, collapsed.value)
})

watch(() => 'isMobile', () => {
  store.commit(TOGGLE_MOBILE_TYPE, isMobile.value)
})

</script>

<style lang="less" scoped>
.trigger {
  font-size: 18px;
  line-height: 64px;
  padding: 0 24px;
  cursor: pointer;
  transition: color 0.3s;
}

.trigger:hover {
  color: #1890ff;
}

.logo {
  height: 50px;
  line-height: 50px;
  font-size: 20px;
  color: #1890ff;
  margin: 16px 25px;
}

.title {
  margin-left: 15px;
  font-size: 33px;
  color: rgba(0, 0, 0, .85);
  font-family: Avenir, 'Helvetica Neue', Arial, Helvetica, sans-serif;
  font-weight: 600;
  position: relative;
  top: 2px;
}

.site-layout .site-layout-background {
  background: #fff;
}

.ant-pro-sider-menu-logo{
  height: 64px;
  padding-left: 16px;
  -webkit-transition: all .3s;
  transition: all .3s;
  line-height: 64px;
  span{
    color: #1890ff;
    font-size: 20px;
    margin: 0 0 0 12px;
    font-family: Avenir,Helvetica Neue,Arial,Helvetica,sans-serif;
    font-weight: 600;
  }
}

.ant-pro-sider-menu-logo:hover{
  cursor: pointer;
}

.sider-menu{
}
</style>
