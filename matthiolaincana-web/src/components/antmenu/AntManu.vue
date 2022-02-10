<template>
  <a-menu
      :theme="props.settings.theme"
      mode="inline"
      :selectedKeys="[ $route.path ]"
      :openKeys="state.openKeys"
      @click="({ item, key, keyPath }) => $router.push({ path: key })"
  >
    <template v-for="item in state.menus" :key="item.path">
      <template v-if="!item.hidden">
        <template v-if="!item.children">
          <a-menu-item :key="item.path">
            <template #icon>
              <x-icon-font :type="item.meta.icon" viewBox="0 0 1024 1024" />
            </template>
            {{ item.meta.title }}
          </a-menu-item>
        </template>
        <sub-menu v-else :key="item.path" :menu-info="item" />
      </template>
    </template>
  </a-menu>
</template>

<script setup>
import { useStore } from "vuex"
import { useRoute } from 'vue-router'
import SubMenu from "./SubMenu.vue"
import { defineProps, reactive, onMounted, computed, watch } from 'vue'
const props = defineProps({
  settings: Object
})

const state = reactive({
  menus:[],
  openKeys: []
})

const store = useStore()
const route = useRoute()

const mainMenu = computed(() => store.getters.addRouters)

onMounted(() => {
  const routes = mainMenu.value.find(item => item.path === '/')
  state.menus = (routes && routes.children) || []
  state.openKeys = route.matched.map((item) => item.path)
})

watch(route,() => {
  state.openKeys = []
  state.openKeys = route.matched.map((item) => item.path)
})

</script>
