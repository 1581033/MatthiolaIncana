<template>
  <t-row :gutter="16">
    <t-col :span="3">
      <t-card :size="settingStore.size">
        <t-input v-model="filterText" label="部门：" :size="settingStore.size" @change="onInput" />
        <t-tree :data="items" activable expand-on-click-node :filter="handleFilterByText" hover line />
      </t-card>
    </t-col>
    <t-col :span="9">
      <t-card :size="settingStore.size">
        <t-form ref="form" :data="state.formData" layout="inline" :label-width="60" colon :rules="state.rules" @reset="onReset" @submit="onSubmit">
          <t-form-item label="名字" name="name">
            <t-input v-model="state.formData.name" :size="settingStore.size" />
          </t-form-item>
          <t-form-item label="密码" name="password">
            <t-input v-model="state.formData.password" :size="settingStore.size" />
          </t-form-item>
          <t-form-item>
            <t-button theme="primary" type="submit" :size="settingStore.size">查询</t-button>
            <t-button theme="primary" type="reset" :size="settingStore.size">重置</t-button>
          </t-form-item>
        </t-form>
      </t-card>
      <t-card :size="settingStore.size">
        <t-table row-key="index" drag-sort="col" hover bordered :size="settingStore.size" :data="state.datas" :columns="state.columns" :loading="state.tableLoading" :pagination="state.pagination" @drag-sort="onDragSort" >
          <template #operation="slotProps">
            <t-button variant="text" size="small" theme="primary" @click="edit(slotProps)">
              <template #icon>
                <t-icon name="edit" />
              </template>
              编辑
            </t-button>
            <t-button variant="text" size="small" theme="danger">
              <template #icon>
                <t-icon name="delete" />
              </template>
              删除
            </t-button>
            <t-button variant="text" size="small" theme="primary">
              <template #icon>
                <t-icon name="chevron-right-double" />
              </template>
              更多
            </t-button>
          </template>
        </t-table>
      </t-card>
    </t-col>
  </t-row>
</template>

<script lang="ts">
export default {
  name: 'SystemUser',
};
</script>

<script setup lang="ts">
import { reactive, ref } from 'vue';
import { useSettingStore } from '@/store';
import { DialogPlugin } from 'tdesign-vue-next';

const settingStore = useSettingStore();

const state = reactive({
  formData: {},
  filterText: null,
  handleFilterByText: null,
  tableLoading: false,
  rules: {},
  columns: [
    {
      align: 'center',
      width: '100',
      colKey: 'index',
      title: '序号',
    },
    {
      align: 'center',
      width: 100,
      colKey: 'platform',
      title: '平台',
    },
    {
      align: 'center',
      width: 100,
      colKey: 'name',
      title: '名称',
    },
    {
      align: 'center',
      colKey: 'operation',
      title: '操作',
      width: 70,
      cell: 'operation',
      fixed: 'right',
    },
  ],
  datas: [
    { index: 1, platform: '公用', name: 'asdasda' },
    { index: 2, platform: '私有', name: 'asdasda' },
    { index: 3, platform: '私有', name: 'asdasda' },
    { index: 4, platform: '私有', name: 'asdasda' },
    { index: 5, platform: '私有', name: 'asdasda' },
  ],
  pagination: {
    current: 1,
    pageSize: 10,
    total: 0,
    showJumper: true,
    onChange: (pageInfo) => {
      console.log('pagination.onChange', pageInfo);
    },
  },
});

const items = [
  {
    value: '1',
    label: '1',
    children: [
      {
        value: '1.1',
        label: '1.1',
        children: [
          {
            value: '1.1.1',
            label: '1.1.1',
            children: [
              {
                value: '1.1.1.1',
                label: '1.1.1.1',
              },
              {
                value: '1.1.1.2',
                label: '1.1.1.2',
              },
            ],
          },
          {
            value: '1.1.2',
            label: '1.1.2',
            children: [
              {
                value: '1.1.2.1',
                label: '1.1.2.1',
              },
              {
                value: '1.1.2.2',
                label: '1.1.2.2',
              },
            ],
          },
        ],
      },
      {
        value: '1.2',
        label: '1.2',
        children: [
          {
            value: '1.2.1',
            label: '1.2.1',
            children: [
              {
                value: '1.2.1.1',
                label: '1.2.1.1',
              },
              {
                value: '1.2.1.2',
                label: '1.2.1.2',
              },
            ],
          },
          {
            value: '1.2.2',
            label: '1.2.2',
            children: [
              {
                value: '1.2.2.1',
                label: '1.2.2.1',
              },
              {
                value: '1.2.2.2',
                label: '1.2.2.2',
              },
            ],
          },
        ],
      },
    ],
  },
  {
    value: '2',
    label: '2',
    children: [
      {
        value: '2.1',
        label: '2.1',
      },
      {
        value: '2.2',
        label: '2.2',
      },
    ],
  },
];

const filterText = ref('');
const handleFilterByText = ref(null);

const onDragSort = ({ currentIndex, targetIndex, current, target, data, newData, e, sort }) => {
  console.log('交换行', currentIndex, targetIndex, current, target, data, newData, e, sort);
  if (sort === 'col') {
    state.columns = newData;
  }
};
const onReset = () => {
  state.tableLoading = false;
};
const onSubmit = () => {
  state.tableLoading = true;
  console.log(1);
};
const onInput = (state) => {
  handleFilterByText.value = (node) => node.data.label.indexOf(filterText.value) >= 0;
};
const edit = (state) => {
  DialogPlugin.confirm({
    body: 'Are you sure to delete it?',
  });
};
</script>

<style lang="less" scoped>
@import url('./index.less');
</style>
