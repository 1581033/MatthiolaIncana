<template>
  <div>
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
  </div>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue';
import { useSettingStore } from '@/store';

const settingStore = useSettingStore();

const state = reactive({
  formData: {},
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
</script>

<style lang="less" scoped>
@import url('./index.less');
</style>
