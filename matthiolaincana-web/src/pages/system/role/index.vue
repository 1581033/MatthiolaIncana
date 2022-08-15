<template>
  <div>
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
  </div>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue';
import XTabel from '@/components/x-tabel/index.vue';
import XForm from '@/components/x-form/index.vue';

const xtabel = ref();

const state = reactive({
  requestConfig: {
    url: '/sysRole/inquireSysRoleList',
    data: {},
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

const onReset = () => {
  onSubmit();
};
const onSubmit = () => {
  xtabel.value.dataList(state.requestConfig);
};
</script>

<style lang="less" scoped>
@import url('./index.less');
</style>
