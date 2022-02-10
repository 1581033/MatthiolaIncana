<script setup>
import zhCN from 'ant-design-vue/es/locale/zh_CN'
import dayjs from 'dayjs'
import 'dayjs/locale/zh-cn'
dayjs.locale('zh-cn')
const locale = zhCN
</script>

<template>
  <a-config-provider :locale="locale">
    <router-view/>
  </a-config-provider>
</template>

<style lang="less">
html,body,#app{
  font-family: 'Helvetica Neue', Helvetica, 'PingFang SC', 'Hiragino Sans GB', 'Microsoft YaHei', '微软雅黑', Arial, sans-serif;
  height:100%;
  margin: 0px;
  padding: 0px;
}
</style>
