<template>
  <t-row>
    <t-col :span="2">
      <t-card :size="settingStore.size" style="min-height: 80vh">
        <t-input v-model="filterText" :size="settingStore.size" @change="onInput">
          <template #label>
            <t-icon name="search" />
          </template>
        </t-input>
        <t-tree :data="state.deptOptions" activable expand-on-click-node :filter="handleFilterByText" hover line @click="onChange" />
      </t-card>
    </t-col>
    <t-col :span="10">
      <x-form ref="xform" :form-data="state.requestConfig.data" :label-width="70" @reset="onReset" @submit="onSubmit" />
      <x-tabel ref="xtabel" row-key="id" local-config :other-columns="state.otherColumns" :config="state.requestConfig">
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
import { onMounted, reactive, ref } from "vue";
import { useSettingStore } from '@/store';
import { DialogPlugin } from 'tdesign-vue-next';
import XTabel from '@/components/x-tabel/index.vue';
import XForm from '@/components/x-form/index.vue';
import { request } from "@/utils/request";

const settingStore = useSettingStore();
const xtabel = ref(null);
const xform = ref(null);

const state = reactive({
  filterText: null,
  handleFilterByText: null,
  rules: {},
  requestConfig: {
    url: '/sysUser/page',
    data: {
      deptId: '',
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
  deptOptions: [],
});

const filterText = ref('');
const handleFilterByText = ref(null);

const treeData = (value) => {
  request.post({ url: '/department/options', data: { currenId: value } }).then((res) => {
    state.deptOptions = res.data;
  });
};

onMounted(() => {
  treeData('');
});

const onChange = (value) => {
  state.requestConfig.data.deptId = value.node.data.value;
  onSubmit();
};
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
