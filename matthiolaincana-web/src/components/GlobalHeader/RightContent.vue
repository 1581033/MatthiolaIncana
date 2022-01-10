<template>
  <div class="row-column-header-left">
    <a-breadcrumb class="row-column-header">
      <a-breadcrumb-item v-for="item in routes">
        <router-link :to="{ path: item.path }">
          {{ item.meta.title }}
        </router-link>
      </a-breadcrumb-item>
    </a-breadcrumb>
  </div>
  <div class="row-column-header-right">
    <a-dropdown placement="bottomRight">
      <template #overlay>
        <a-menu>
          <a-menu-item v-for="(item,index) in menus" :key="index" style="font-size: 16px;height: 40px;width: 160px">
            <template #icon><component :is="item.icon" style="font-size: 16px;" /></template>
            <span>{{ item.title }}</span>
          </a-menu-item>
          <a-menu-divider />
          <a-menu-item key="3" style="font-size: 16px;height: 40px;width: 160px" @click="loginOut">
            <template #icon><LogoutOutlined style="font-size: 16px;"/></template>
            <span>退出登录</span>
          </a-menu-item>
        </a-menu>
      </template>
      <div class="user-row-select">
        <a-avatar :size="30">
          <template #icon><UserOutlined /></template>
        </a-avatar>
        <span class="row-column-header-username">{{ userName }}</span>
      </div>
    </a-dropdown>

  </div>
</template>

<script setup>
import { UserOutlined, SettingOutlined, LogoutOutlined, ExclamationCircleOutlined  } from '@ant-design/icons-vue'
import { ref, onMounted, computed, watch, createVNode } from 'vue'
import { useRoute } from 'vue-router'
import { useStore } from 'vuex'
import { Modal } from 'ant-design-vue'
const basePath = ref('index')

const menus = [
  {title: '个人中心',icon: UserOutlined},
  {title: '个人设置',icon: SettingOutlined}
]

const router = useRoute()
const store = useStore()

const routes = ref([])

const userName = computed(() => store.getters.nickname)

onMounted(() => {
  routes.value = router.matched
})

watch(router, () => {
  routes.value = router.matched
})

const loginOut = () => {
  Modal.confirm({
    title: '退出登录',
    icon: createVNode(ExclamationCircleOutlined),
    content: createVNode('div', { style: 'color:red;font-size: 18px;' }, '你确定要退出登录?'),
    cancelText: '取消',
    okText: '确定',
    onOk() {
      return new Promise((resolve, reject) => {
        store.dispatch('Logout')
            .then(res => {
              setTimeout(() => {
                window.location.reload()
              }, 1500)
            })
      }).catch(() => console.log('Oops errors!'))
    },
    onCancel() {},
  })
}

</script>
<style lang="less" scoped>
.row-column-header{
  height: 64px;
  float: left;
  line-height: 64px;
}
.row-column-header-right{
  height: 64px;
  padding-right: 20px;
  float: right;
}
.row-column-header-username{
  font-size: 20px;
  margin-left: 10px;
}
.user-row-select{
  padding: 0 12px;
}
.user-row-select:hover{
  background-color: rgba(0, 0, 0, 0.025);
  cursor: pointer;
}
.submenu-title {
  font-size: 18px;
}
</style>
