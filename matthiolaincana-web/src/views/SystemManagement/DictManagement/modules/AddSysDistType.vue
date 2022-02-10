<template>
  <a-modal v-model:visible="state.visible" forceRender :title="state.title" width="24%" ok-text="保存" cancel-text="取消" :confirmLoading="state.confirmLoading" @cancel="$refs.formRef.resetFields()" @ok="submitData">
    <a-form :model="state.data" ref="formRef" :rules="rules" :label-col="state.labelCol" :wrapper-col="state.wrapperCol">
      <a-form-item label="字典名称" name="name">
        <a-input v-model:value="state.data.name" />
      </a-form-item>
      <a-form-item label="字典类型" name="code">
        <a-input v-model:value="state.data.code" />
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
import {reactive, ref} from "vue";
import Api from 'apis/baseresponse'
import {notification} from "ant-design-vue";

const props = defineProps({
  sysNormalDisable: Array
})

const state = reactive({
  requestApi: 'sysDictType',
  visible: false,
  title: '添加菜单',
  confirmLoading: false,
  data: {
    status: '1'
  },
  labelCol: {
    style: { width: '100px' }
  },
  wrapperCol: {
    span: 18
  }
})

const rules = reactive({
  name: [{required: true, message: 'Please input name',}]
})
const formRef = ref()
const emit = defineEmits(['select'])

const addData = () => {
  state.title = '添加字典'
  state.visible = true
}

const editData = (values) => {
  state.title = '编辑字典'
  state.data = {...values}
  state.visible = true
}

const submitData = () => {
  formRef.value.validate().then((values) => {
    state.confirmLoading = true
    if (state.title === '添加字典'){
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
