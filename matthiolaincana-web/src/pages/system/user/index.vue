<template>
  <t-row>
    <t-col :span="2">
      <t-card :size="settingStore.size">
        <t-input v-model="filterText" label="部门：" :size="settingStore.size" @change="onInput" />
        <t-tree :data="items" activable expand-on-click-node :filter="handleFilterByText" hover line />
      </t-card>
    </t-col>
    <t-col :span="10">
      <x-form ref="xform" :form-data="state.requestConfig.data" :label-width="70" @reset="onReset" @submit="onSubmit" />
      <x-tabel ref="xtabel" row-key="id" :other-columns="state.otherColumns" :config="state.requestConfig">
        <template #status="{ row }">
          <t-switch v-model="row.status" :customValue="[1, 0]" />
        </template>
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
      </x-tabel>
    </t-col>
  </t-row>
</template>

<script lang="ts">
export default {
  name: 'SystemUser',
};
</script>

<script setup lang="ts">
import { onMounted, reactive, ref } from 'vue';
import { useSettingStore } from '@/store';
import { DialogPlugin } from 'tdesign-vue-next';
import { request } from '@/utils/request';
import XTabel from '@/components/x-tabel/index.vue';
import XForm from '@/components/x-form/index.vue';

const settingStore = useSettingStore();
const xtabel = ref();
const xform = ref();

const state = reactive({
  filterText: null,
  handleFilterByText: null,
  rules: {},
  requestConfig: {
    url: '/sysUser/queryUserInformation',
    data: {
      page: 1,
      size: 10,
    },
  },
  otherColumns: [
    {
      align: 'center',
      colKey: 'operation',
      title: '操作',
      cell: 'operation',
      fixed: 'right',
    },
  ],
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

const onReset = () => {
  onSubmit();
};
const onSubmit = () => {
  xtabel.value.converConfig(state.requestConfig);
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
