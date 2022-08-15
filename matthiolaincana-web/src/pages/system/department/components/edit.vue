<template>
  <t-dialog :visible="state.visible" width="40%" header="编辑部门" :on-confirm="onConfirm" :on-close="close">
    <t-form ref="form" :label-width="70" :data="state.formData" :rules="state.rules">
      <t-form-item label="上级部门" name="id">
        <t-input v-model="state.formData.id" :size="settingStore.size" />
      </t-form-item>
      <t-form-item label="部门名称" name="name">
        <t-input v-model="state.formData.name" :size="settingStore.size" />
      </t-form-item>
      <t-form-item label="显示排序" name="status">
        <t-input-number v-model="state.formData.status" :size="settingStore.size" />
      </t-form-item>
      <t-form-item label="状态" name="status">
        <t-radio-group v-model="state.formData.status" name="city" :options="state.options" />
      </t-form-item>
    </t-form>
  </t-dialog>
</template>

<script setup lang="ts">
import { reactive, defineExpose, ref } from 'vue';
import { useSettingStore } from '@/store';

const settingStore = useSettingStore();
const form = ref();

const state = reactive({
  visible: false,
  formData: {},
  rules: {
    account: [
      { required: true, message: '姓名必填' },
    ],
  },
  options: [
    {
      value: 1,
      label: '正常',
    },
    {
      value: 0,
      label: '停用',
    },
  ],
});

const open = (value) => {
  const { row } = value;
  state.formData = row;
  state.visible = true;
};

const onConfirm = () => {
  form.value.validate().then((validateResult) => {
    if (validateResult === true) {
      state.visible = false;
    }
  });
};

const close = () => {
  state.visible = false;
};

defineExpose({
  open,
});
</script>

<style lang="less" scoped>
</style>
