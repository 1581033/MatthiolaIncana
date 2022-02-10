<template>
  <a-sub-menu :key="props.menuInfo.path">
    <template #icon>
      <x-icon-font :type="props.menuInfo.meta.icon" viewBox="0 0 1024 1024"/>
    </template>
    <template #title>
      {{ props.menuInfo.meta.title }}
    </template>
    <template v-for="item in props.menuInfo.children" :key="item.path">
      <template v-if="!item.hidden">
        <template v-if="!item.children">
          <a-menu-item :key="item.path">
            <template #icon>
              <x-icon-font :type="item.meta.icon" viewBox="0 0 1024 1024"/>
            </template>
            {{ item.meta.title }}
          </a-menu-item>
        </template>
        <sub-menu v-else :menu-info="item" :key="item.path" />
      </template>
    </template>
  </a-sub-menu>
</template>

<script setup>
import { defineProps } from 'vue'
const props = defineProps({
  menuInfo: {
    type: Object,
    default: () => ({}),
  }
})
</script>
