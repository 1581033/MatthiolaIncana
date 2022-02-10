<template>
  <a-layout class="layout-container">
    <a-layout-sider
        class="layout-container-sider"
        v-model:collapsed="state.collapsed"
        :theme="props.settings.theme"
        :trigger="null"
        collapsible
        width="240"
        :style="props.settings.fixSiderbar ? state.layoutSider : {}"
    >
      <div class="layout-container-logo" @click="$router.push({ path: '/' })">
        <x-icon-font :type="props.settings.icon" style="margin: 0 10px" viewBox="0 0 1024 1024" />
        <span :style="state.showTitle" class="animate__flash">{{ props.settings.title }}</span>
      </div>
      <ant-manu :settings="props.settings"/>
    </a-layout-sider>
    <a-layout :style="props.settings.fixSiderbar ? state.layout : {}">
      <a-layout-header
          class="layout-container-header"
          :style="props.settings.fixedHeader ? state.layoutHeader : {}"
      >
        <div class="trigger">
          <MenuUnfoldOutlined v-if="state.collapsed"  @click="state.collapsed = !state.collapsed"/>
          <MenuFoldOutlined v-else @click="state.collapsed = !state.collapsed" />
        </div>
        <div class="layout-container-header-other">
          <slot name="header"></slot>
        </div>
      </a-layout-header>
      <a-layout-content
          class="layout-container-content"
          :style="props.settings.fixedHeader ? state.layoutContent : {}"
      >
        <slot></slot>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<script setup>
import AntManu from '@/components/antmenu/AntManu.vue'
import { MenuUnfoldOutlined, MenuFoldOutlined } from '@ant-design/icons-vue'
import { reactive, computed } from "vue";

const props = defineProps({
  settings: Object
})

const state = reactive({
  collapsed: false,
  showTitle: {
    display: computed(() => state.collapsed ? 'none' : 'inline'),
    transition: 'all 0.2s',
    marginLeft: '12px',
    fontWeight: 600
  },
  layoutSider: { overflow: 'auto', height: '100vh', position: 'fixed', left: 0 },
  layoutHeader: {
    position: 'fixed',
    zIndex: 1,
    left: computed(() => state.collapsed ? '80px' : '240px'),
    right: 0
  },
  layout: {
    paddingLeft: computed(() => state.collapsed ? '80px' : '240px'),
    transition: 'all 0.2s'
  },
  layoutContent: {marginTop: '64px'}
})

</script>

<style lang="less" scoped>
.layout-container{
  min-height: 100vh;
  .layout-container-sider{
  }
  .layout-container-logo{
    font-size: 25px;
    height: 32px;
    color: white;
    margin: 16px;
    line-height: 32px;
  }
  .layout-container-header{
    background: #fff;
    padding: 0 8px;
    transition: all 0.2s;
  }
  .layout-container-content{
    padding: 8px;
  }
}
.layout-container-header-other{
  float: right;
}
.trigger {
  float: left;
  font-size: 30px;
  margin: 0 24px;
  cursor: pointer;
  transition: color 0.3s;
}

.trigger:hover {
  color: #1890ff;
}

.layout-container-logo:hover{
  cursor: pointer;
}
</style>
