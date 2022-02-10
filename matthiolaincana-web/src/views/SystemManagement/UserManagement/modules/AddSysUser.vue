<template>
  <a-modal v-model:visible="state.visible" forceRender :title="state.title"  width="50%" ok-text="保存" cancel-text="取消" :confirmLoading="state.confirmLoading" @cancel="$refs.formRef.resetFields()" @ok="submitData">
    <a-form :model="data.datas" :labelCol="{ md: 4, sm: 6 }" :wrapperCol="{ md: 18, sm: 20 }" ref="formRef" :rules="rules">
      <a-form-item label="账号" name="username" required>
        <a-input v-model:value="data.datas.username" />
      </a-form-item>
      <a-form-item label="名称" name="name" required>
        <a-input v-model:value="data.datas.name" />
      </a-form-item>
      <a-form-item label="头像" name="avatar" required>
        <a-input v-model:value="data.datas.avatar" />
      </a-form-item>
      <a-form-item label="状态" name="status" >
        <a-switch v-model:checked="data.datas.status" checked-children="开" un-checked-children="关" checkedValue="1" unCheckedValue="0" />
      </a-form-item>
      <a-form-item label="电话" name="telephone" required>
        <a-input v-model:value="data.datas.telephone" />
      </a-form-item>
      <a-form-item label="角色" name="roles" >
        <a-select
            v-model:value="data.datas.roles"
            mode="multiple"
            :options="state.rolesTreeData.map(item => ({ label: item.name, value: item.id }))"
        />
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script setup>
import Api from 'apis/baseresponse'
import {onMounted, reactive, ref} from 'vue'
import {notification} from "ant-design-vue";

const state = reactive({
  requestApi: 'sysUser',
  visible: false,
  title: '',
  confirmLoading: false,
  rolesTreeData: []
})

const data = reactive({
  datas: {}
})

const rules = [{

}]

onMounted(() => {
  Api.inquireList({page: 1},'sysRole').then(res => {
    state.rolesTreeData = res.result
  })
})

const formRef = ref()

const emit = defineEmits(['select'])

const addData = () => {
  state.title = '添加用户'
  state.visible = true
}

const editData = (values) => {
  state.title = '编辑用户'
  data.datas = {...values}
  state.visible = true
}

const submitData = () => {
  formRef.value.validate().then((values) => {
    state.confirmLoading = true
    if (state.title === '添加用户'){
      Api.increase(values,state.requestApi).then(res => {
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
      Api.updata(data.datas,state.requestApi).then(res => {
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
