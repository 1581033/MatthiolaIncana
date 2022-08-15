<template>
  <div>
    <x-form ref="xform" :form-data="state.requestConfig.data" :label-width="70" @reset="onReset" @submit="onSubmit" />
    <x-tabel ref="xtabel" row-key="id" type="tree" :other-columns="state.otherColumns" :config="state.requestConfig">
      <template #status="{ row }">
        <t-switch v-model="row.status" :custom-value="[1, 0]" />
      </template>
      <template #operation="slotProps">
        <t-button variant="text" size="small" theme="primary" @click="$refs.editRef.open(slotProps)">
          <template #icon>
            <t-icon name="edit" />
          </template>
          编辑
        </t-button>
        <t-button v-if="slotProps.row.id !== '1'" variant="text" size="small" theme="danger">
          <template #icon>
            <t-icon name="delete" />
          </template>
          删除
        </t-button>
        <t-button variant="text" size="small" theme="primary" @click="state.visible = true">
          <template #icon>
            <t-icon name="chevron-right-double" />
          </template>
          更多
        </t-button>
      </template>
    </x-tabel>
    <edit ref="editRef" />
  </div>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue';
import Edit from './components/edit.vue';
import XTabel from '@/components/x-tabel/index.vue';
import XForm from '@/components/x-form/index.vue';

const xtabel = ref();

const state = reactive({
  requestConfig: {
    url: '/department/tree',
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
const onSubmit = () => {
  xtabel.value.dataList(state.requestConfig);
};
const onReset = () => {
  onSubmit();
};
</script>

<style lang="less" scoped>
@import url('./index.less');
</style>
