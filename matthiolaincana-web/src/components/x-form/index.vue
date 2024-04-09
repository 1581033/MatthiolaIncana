<template>
  <t-card v-if="state.columns.length !== 0" :size="settingStore.size">
    <t-form v-bind="$attrs" :data="props.formData" layout="inline" colon>
      <t-form-item v-for="item in state.columns" :label="item.title" :name="item.colKey">
        <template v-if="item.inquiryMode === COMPONENTS_TYPES.INPUT">
          <t-input v-model="props.formData[item.colKey]" :size="settingStore.size" />
        </template>
        <template v-if="item.inquiryMode === COMPONENTS_TYPES.SELECT">
          <t-select v-model="props.formData[item.colKey]" :size="settingStore.size">
            <t-option key="apple" label="Apple" value="apple" />
            <t-option key="orange" value="orange">Orange</t-option>
            <t-option key="banana" label="Banana" value="banana" />
          </t-select>
        </template>
        <template v-if="item.inquiryMode === COMPONENTS_TYPES.DATE">
          <t-date-range-picker v-model="props.formData[item.colKey]" :size="settingStore.size" enable-time-picker />
        </template>
      </t-form-item>
      <t-form-item>
        <t-button theme="primary" type="submit" :size="settingStore.size">
          <template #icon>
            <t-icon name="search" />
          </template>
          查询
        </t-button>
        <t-button theme="primary" type="reset" :size="settingStore.size">
          <template #icon>
            <t-icon name="refresh" />
          </template>
          重置
        </t-button>
      </t-form-item>
    </t-form>
  </t-card>
</template>

<script lang="ts">
export default {
  name: 'Xform',
};
</script>

<script setup lang="ts">
import { onMounted, reactive, defineExpose } from 'vue';
import { useRoute } from 'vue-router';
import { useSettingStore } from '@/store';
import { request } from '@/utils/request';
import { COMPONENTS_TYPES } from '@/constants';

const props = defineProps({
  formData: Object,
});

const settingStore = useSettingStore();
const route = useRoute();

const state = reactive({
  columns: [],
});

onMounted(() => {
  dataHeader();
});

const dataHeader = () => {
  const url = `/pagination/${route.name as string}/formList`;
  request.get({ url }).then((res) => {
    state.columns = res.data;
  });
};
</script>
