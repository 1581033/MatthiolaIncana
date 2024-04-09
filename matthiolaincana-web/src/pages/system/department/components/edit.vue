<template>
  <t-dialog :visible="state.visible" width="40%" header="编辑部门" :on-confirm="onConfirm" :on-close="close">
    <t-form ref="form" :label-width="70" :data="state.formData" :rules="state.rules">
      <t-form-item v-if="state.formData.parentId !== '0' || state.mode !== 1" label="上级部门" name="parentId">
        <t-tree-select v-model="state.formData.parentId" :data="state.deptOptions" :size="settingStore.size" :tree-props="treeProps" />
      </t-form-item>
      <t-form-item label="部门名称" name="name">
        <t-input v-model="state.formData.name" :size="settingStore.size" />
      </t-form-item>
      <t-form-item label="状态" name="status">
        <t-radio-group v-model="state.formData.status" name="city" :options="state.options" />
      </t-form-item>
    </t-form>
  </t-dialog>
</template>

<script setup lang="ts">
import { reactive, defineExpose, ref, defineEmits } from 'vue';
import { useSettingStore } from '@/store';
import { request } from '@/utils/request';

const emit = defineEmits(['fetch']);
const settingStore = useSettingStore();
const form = ref(null);

const state = reactive({
  visible: false,
  mode: 1,
  formData: {
    id: '',
    parentId: '',
    status: 1,
    name: '',
  },
  rules: {
    parentId: [
      { required: true, message: '上级部门必填' },
    ],
    name: [
      { required: true, message: '部门名称必填' },
    ],
    status: [
      { required: true, message: '状态必填' },
    ],
  },
  deptOptions: [],
  options: [
    { value: 1, label: '正常' },
    { value: 0, label: '停用' },
  ],
});

const treeProps = {
  keys: {
    label: 'name',
    value: 'id',
    children: 'children',
  },
};

const treeData = (value) => {
  request.post({ url: '/department/tree', data: { currenId: value } }).then((res) => {
    state.deptOptions = res.data;
    state.visible = true;
  });
};

const open = (value, mode) => {
  state.mode = mode;
  const data = JSON.parse(JSON.stringify(value));
  if (state.mode === 1) {
    state.formData = data.row;
    treeData(state.formData.id);
  } else {
    state.formData.parentId = data.row.id;
    treeData('');
  }
};

const onConfirm = () => {
  form.value.validate().then((validateResult) => {
    if (validateResult === true) {
      request.post({ url: '/department', data: state.formData }).then((res) => {
        emit('fetch');
        close();
      });
    }
  });
};

const close = () => {
  state.visible = false;
  form.value.reset();
  state.formData.status = 1;
};

defineExpose({
  open,
});
</script>

<style lang="less" scoped>
</style>
