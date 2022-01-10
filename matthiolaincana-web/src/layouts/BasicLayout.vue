<template>
  <a-layout style="height: 100%">
    <a-layout-sider v-model:collapsed="collapsed" :theme="settings.theme" width="250">
      <div class="ant-pro-sider-menu-logo" @click="$router.push({ path: '/' })" >
        <MyIcon type="icon-dashboard" style="fontSize: 25px;color: #1890ff;" viewBox="0 0 1024 1024" />
        <span v-show="!collapsed">首页</span>
      </div>
      <ant-manu :settings="settings"/>
    </a-layout-sider>
    <a-layout>
      <a-layout-header style="background: #fff; padding: 0; border-bottom: 1px solid #ebeef5; box-shadow: 0 2px 12px 0 rgb(0 0 0 / 20%);">
        <div style="float: left">
          <MenuUnfoldOutlined v-if="collapsed" class="trigger" @click="collapsed = !collapsed"/>
          <MenuFoldOutlined v-else class="trigger" @click="collapsed = !collapsed" />
        </div>
        <right-content/>
      </a-layout-header>
      <a-layout-content :style="{ padding: '12px', minHeight: '280px', backgroundColor: 'rgba(255,255,255,0.92)' }">
        <router-view/>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>
<script setup>
import { useStore } from 'vuex'
import AntManu from '@/components/antmenu/AntManu.vue'
import defaultSettings from '@/config/defaultSettings'
import { CONTENT_WIDTH_TYPE, SIDEBAR_TYPE, TOGGLE_MOBILE_TYPE } from '@/store/mutation-types'
import { MenuUnfoldOutlined, MenuFoldOutlined } from '@ant-design/icons-vue'
import { ref,reactive, watch } from 'vue'
import RightContent from 'components/GlobalHeader/RightContent.vue'
const settings = reactive({
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

const collapsed = ref(false)
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
