<template>
  <div>
    <x-form ref="xform" :form-data="state.requestConfig.data" :label-width="70" @reset="onReset" @submit="onSubmit" />
    <x-tabel ref="xtabel" row-key="id" type="tree" local-config :other-columns="state.otherColumns" :config="state.requestConfig">
      <template #status="{ row }">
        <t-switch v-model="row.status" :custom-value="[1, 0]" />
      </template>
      <template #operation="slotProps">
        <t-button variant="text" size="small" theme="primary" @click="$refs.editRef.open(slotProps, 1)">
          <template #icon>
            <t-icon name="edit" />
          </template>
          编辑
        </t-button>
        <t-button variant="text" size="small" theme="primary" @click="$refs.editRef.open(slotProps, 2)">
          <template #icon>
            <t-icon name="add" />
          </template>
          添加
        </t-button>
        <t-button v-if="slotProps.row.id !== '1'" variant="text" size="small" theme="primary" @click="del(slotProps)">
          <template #icon>
            <t-icon name="delete" />
          </template>
          删除
        </t-button>
      </template>
    </x-tabel>
    <edit ref="editRef" @fetch="onReset" />
  </div>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue';
import { DialogPlugin, MessagePlugin } from 'tdesign-vue-next';
import Edit from './components/edit.vue';
import XTabel from '@/components/x-tabel/index.vue';
import XForm from '@/components/x-form/index.vue';
import { request } from "@/utils/request";

const xtabel = ref(null);

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
  xtabel.value.converConfig(state.requestConfig);
};
const onReset = () => {
  state.requestConfig.data = {};
  onSubmit();
};

const del = (value) => {
  const DialogInstance = DialogPlugin.confirm({
    header: '系统提示',
    body: `是否确认删除名称为"${value.row.name as String}"的数据项?`,
    onConfirm: (context) => {
      request.delete({ url: `/department?id=${value.row.id}` }).then(res => {
        MessagePlugin.success('删除成功!');
        DialogInstance.hide();
        onReset();
      });
    },
  });
};
</script>

<style lang="less" scoped>
@import url('./index.less');
</style>
