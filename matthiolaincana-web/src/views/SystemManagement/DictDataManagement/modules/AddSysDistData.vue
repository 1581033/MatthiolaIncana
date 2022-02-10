<template>
  <a-modal v-model:visible="state.visible" forceRender :title="state.title" width="24%" ok-text="保存" cancel-text="取消" :confirmLoading="state.confirmLoading" @cancel="$refs.formRef.resetFields()" @ok="submitData">
    <a-form :model="state.data" ref="formRef" :rules="rules" :label-col="state.labelCol" :wrapper-col="state.wrapperCol">
      <a-form-item label="字典类型">
        <a-input v-model:value="state.data.typeId" disabled/>
      </a-form-item>
      <a-form-item label="数据标签" name="value">
        <a-input v-model:value="state.data.value" />
      </a-form-item>
      <a-form-item label="数据键值" name="code">
        <a-input v-model:value="state.data.code" />
      </a-form-item>
      <a-form-item label="样式属性" name="cssClass">
        <a-input v-model:value="state.data.cssClass" />
      </a-form-item>
      <a-form-item label="显示排序">
        <a-input-number v-model:value="state.data.sort" :min="1" :max="100" />
      </a-form-item>
      <a-form-item label="回显样式">
        <a-select v-model:value="state.data.listClass" :options="state.selectOptions" />
      </a-form-item>
      <a-form-item label="状态">
        <a-radio-group v-model:value="state.data.status">
          <a-radio v-for="item in props.sysNormalDisable" :value="item.code">{{ item.value }}</a-radio>
        </a-radio-group>
      </a-form-item>
      <a-form-item label="备注" name="remark">
        <a-textarea v-model:value="state.data.remark" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script setup>
import Api from 'apis/baseresponse'
import {reactive, ref, onMounted} from "vue";
import {notification} from "ant-design-vue";

const props = defineProps({
  sysNormalDisable: Array
})

const state = reactive({
  requestApi: 'sysDictData',
  visible: false,
  title: '添加菜单',
  confirmLoading: false,
  data: {
    sort: 1,
    status: '1',
    listClass: 'default'
  },
  labelCol: {
    style: { width: '100px' }
  },
  wrapperCol: {
    span: 18
  },
  selectOptions: [
    { label: '默认', value: 'default' },
    { label: '主要', value: 'processing' },
    { label: '成功', value: 'success' },
    { label: '信息', value: '#909399' },
    { label: '警告', value: 'warning' },
    { label: '危险', value: 'error' },
  ],
  sysNormalDisable: []
})

const rules = reactive({
  name: [{required: true, message: 'Please input name',}]
})
const formRef = ref()
const emit = defineEmits(['select'])

const addData = (value) => {
  state.title = '添加字典数据'
  state.data.typeId = value
  state.visible = true
}

const editData = (values) => {
  state.title = '编辑字典数据'
  state.data = {...values}
  state.visible = true
}

const submitData = () => {
  formRef.value.validate().then((values) => {
    state.confirmLoading = true
    if (state.title === '添加字典数据'){
      Api.increase(state.data,state.requestApi).then(res => {
        if (res.result === 1){
          formRef.value.resetFields()
          notification.success({ message: '添加成功' })
          state.visible = false
          emit('select', null)
        }else {
          notification.error({ message: '添加失败' })
        }
        state.confirmLoading = false
      })
    }else {
      Api.updata(state.data,state.requestApi).then(res => {
        notification.success({ message: '修改成功' })
        state.visible = false
        state.confirmLoading = false
        emit('select', null)
      })
    }
  }).catch((errors) => {})
}

defineExpose({
  addData,
  editData
})

</script>

<style lang="less" scoped>

</style>
