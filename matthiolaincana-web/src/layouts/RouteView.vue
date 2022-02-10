<template >
  <router-view v-slot="{ Component }">
    <keep-alive v-if="$route.meta.keepAlive">
      <component :is="Component"  />
    </keep-alive>
    <component :is="Component"  v-else/>
  </router-view>
</template>
<script setup>
const props = defineProps({
  keepAlive: {
    type: Boolean,
    default: true
  }
})
</script>
