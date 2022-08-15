<template>
  <t-card v-if="state.columns.length !== 0" :size="settingStore.size">
    <t-form v-bind="$attrs" :data="props.formData" layout="inline" colon>
      <t-form-item v-for="item in state.columns" :label="item.title" :name="item.colKey">
        <t-input v-if="item.inquiryMode === COMPONENTS_TYPES.INPUT" v-model="props.formData[item.colKey]" :size="settingStore.size" />
        <t-select v-if="item.inquiryMode === COMPONENTS_TYPES.SELECT" v-model="props.formData[item.colKey]" :size="settingStore.size">
          <t-option key="apple" label="Apple" value="apple" />
          <t-option key="orange" value="orange">Orange</t-option>
          <t-option key="banana" label="Banana" value="banana" />
        </t-select>
        <t-date-range-picker v-if="item.inquiryMode === COMPONENTS_TYPES.DATE" v-model="props.formData[item.colKey]" :size="settingStore.size" enable-time-picker />
      </t-form-item>
      <t-form-item>
        <t-button theme="primary" type="submit" :size="settingStore.size">查询</t-button>
        <t-button theme="primary" type="reset" :size="settingStore.size">重置</t-button>
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
import { useSettingStore } from '@/store';
import { request } from '@/utils/request';
import { COMPONENTS_TYPES } from '@/constants';
import { useRoute } from 'vue-router';

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
  request.get({ url: '/pagination/formList', params: { routeName: route.name } }).then((res) => {
    state.columns = res.result;
  });
};
</script>
