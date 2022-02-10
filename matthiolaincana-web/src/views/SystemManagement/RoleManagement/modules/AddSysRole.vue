<template>
  <a-modal v-model:visible="state.visible" forceRender :title="state.title"  width="50%" ok-text="保存" cancel-text="取消" :confirmLoading="state.confirmLoading" @cancel="$refs.formRef.resetFields()" @ok="submitData">
    <a-form :model="data.datas" :labelCol="{ md: 4, sm: 6 }" :wrapperCol="{ md: 18, sm: 20 }" ref="formRef" :rules="rules">
      <a-form-item label="权限名称" name="name" required>
        <a-input v-model:value="data.datas.name" />
      </a-form-item>
      <a-form-item label="权限状态" name="status" required>
        <a-switch v-model:checked="data.datas.status" checked-children="开" un-checked-children="关" checkedValue="true" unCheckedValue="false" />
      </a-form-item>
      <a-form-item label="描述" name="roleDescribe" required>
        <a-input v-model:value="data.datas.roleDescribe" />
      </a-form-item>
      <a-form-item label="菜单权限" name="menus" required>
        <a-tree-select
            v-model:value="data.datas.menus"
            :tree-data="state.menuTreeData"
            :fieldNames="state.fieldNames"
            :show-checked-strategy="SHOW_PARENT"
            treeDefaultExpandAll
            tree-checkable
            allow-clear
        />
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script setup>
import { TreeSelect, notification } from 'ant-design-vue'
import {reactive, ref, onMounted} from "vue"
import { inquireSysRoleByMenu } from 'apis/SystemManagement/rolemanagement'
import Api from 'apis/baseresponse'
const SHOW_PARENT = TreeSelect.SHOW_PARENT
const state = reactive({
  requestApi: 'sysRole',
  visible: false,
  title: '添加角色',
  confirmLoading: false,
  menuTreeData: [],
  labelCol: {
    span: 15
  },
  fieldNames: {
    children: 'children',
    label: 'title',
    key: 'id',
    value: 'id'
  }
})

const data = reactive({
  datas: {
    name: '',
    status: 'false',
    roleDescribe: '',
    menus: []
  }
})

const rules = {
  menus: [{required: true, message: 'Please input menus',}]
}

const formRef = ref()
const emit = defineEmits(['select'])

onMounted(() => {
  Api.inquireList({ page: 1 },'sysMenu').then(res => {
    state.menuTreeData = res.result
  })
})

const getDefaultValue = (id) => {
  inquireSysRoleByMenu({ id: id }).then(res => {
    data.datas.menus = res.result.map(s => s.menuId)
  })
}

const addData = () => {
  state.title = '添加角色'
  state.visible = true
}

const editData = (values) => {
  state.title = '编辑角色'
  data.datas = {...values}
  getDefaultValue(values.id)
  state.visible = true
}

const submitData = () => {
  formRef.value.validate().then((values) => {
    state.confirmLoading = true
    if (state.title === '添加角色'){
      Api.increase(values,state.requestApi).then(res => {
        if (res.result === 1){
          notification.success({ message: '添加成功' })
        } else {
          notification.error({ message: '添加失败' })
        }
      })
    } else {
      Api.updata(data.datas,state.requestApi).then(res => {
        notification.success({ message: '修改成功' })
      })
    }
  })
    .catch((errors) => {})
    .finally(() => {
      formRef.value.resetFields()
      state.confirmLoading = false
      state.visible = false
      emit('select', null)
    })
}

defineExpose({
  addData,
  editData
})
</script>

<style lang="less" scoped>
</style>
