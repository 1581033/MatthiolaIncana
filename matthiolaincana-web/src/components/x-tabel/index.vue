<template>
  <t-card v-if="state.columns.length !== 0" :size="settingStore.size">
    <t-enhanced-table
      v-if="props.type === 'tree'"
      v-bind="$attrs"
      drag-sort="col"
      hover
      :size="settingStore.size"
      :data="state.dataList"
      :columns="state.columns"
      :loading="state.tableLoading"
      :tree="state.tableTreeConfig"
      @drag-sort="onDragSort"
    >
      <template v-for="(index, name) in $slots" #[name]="{ col, colIndex, row, rowIndex }">
        <slot :name="name" :col="col" :colIndex="colIndex" :row="row" :rowIndex="rowIndex" />
      </template>
    </t-enhanced-table>
    <t-table
      v-else
      v-bind="$attrs"
      drag-sort="col"
      hover
      :size="settingStore.size"
      :data="state.dataList"
      :columns="state.columns"
      :pagination="state.pagination"
      :loading="state.tableLoading"
      @drag-sort="onDragSort"
    >
      <template v-for="(index, name) in $slots" #[name]="{ col, colIndex, row, rowIndex }">
        <slot :name="name" :col="col" :colIndex="colIndex" :row="row" :rowIndex="rowIndex" />
      </template>
    </t-table>
  </t-card>
</template>

<script lang="ts">
export default {
  name: 'Xtabel',
};
</script>

<script setup lang="ts">
import { onMounted, reactive, PropType, defineExpose } from 'vue';
import { useRoute } from 'vue-router';
import { AxiosRequestConfig } from 'axios';
import { useSettingStore } from '@/store';
import { request } from '@/utils/request';

const settingStore = useSettingStore();
const route = useRoute();

const props = defineProps({
  otherColumns: Array,
  config: Object as PropType<AxiosRequestConfig>,
  type: String,
  localConfig: Boolean,
});

const state = reactive({
  tableLoading: false,
  columns: [],
  dataList: [],
  tableTreeConfig: {
    childrenKey: 'children',
    indent: 24,
    checkStrictly: false,
    defaultExpandAll: true,
  },
  pagination: {
    current: 1,
    pageSize: 10,
    total: 0,
    showJumper: true,
    size: settingStore.size,
    onChange: ({ current, pageSize }) => {
      state.pagination.current = current;
      state.pagination.pageSize = pageSize;
      converConfig(props.config);
    },
  },
  localConfig: {
    url: '/pagination/page',
    data: {},
  },
});

onMounted(() => {
  dataHeader();
});

const dataHeader = () => {
  state.tableLoading = true;
  request.get({ url: '/pagination/headerList', params: { routeName: route.name } }).then((res) => {
    state.columns = res.result;
    props.otherColumns.forEach((s) => state.columns.push(s));
    converConfig(props.config);
  });
};

const converConfig = (config) => {
  const con = { ...config };
  if (props.localConfig) {
    dataList(con);
  } else {
    localDataList(con);
  }
};

const localDataList = (config) => {
  state.tableLoading = true;
  state.localConfig.data = { routeName: route.name, map: { ...config.data } };
  request.post(state.localConfig).then((res) => {
    const { current, size, total, records } = res.result;
    state.dataList = records;
    state.pagination.current = current;
    state.pagination.pageSize = size;
    state.pagination.total = total;
    state.tableLoading = false;
  });
};

const dataList = (config) => {
  state.tableLoading = true;
  request.post(config).then((res) => {
    const { current, size, total, records } = res.result;
    if (total) {
      state.dataList = records;
      state.pagination.current = current;
      state.pagination.pageSize = size;
      state.pagination.total = total;
    } else {
      state.dataList = res.result;
      state.pagination = null;
    }
    state.tableLoading = false;
  });
};

const onDragSort = ({ currentIndex, targetIndex, current, target, data, newData, e, sort }) => {
  if (sort === 'col') {
    state.columns = newData;
  }
};
defineExpose({
  converConfig,
});
</script>

<style lang="less" scoped>
</style>
