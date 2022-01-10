<template>
  <a-modal v-model:visible="state.visible" forceRender :title="state.title" width="50%" ok-text="保存" cancel-text="取消" :confirmLoading="state.confirmLoading" @cancel="$refs.formRef.resetFields()" @ok="submitData">
    <a-form :model="data.datas" ref="formRef" :rules="rules">
      <a-row :gutter="24">
        <a-col :md="12" :sm="24">
          <a-form-item label="菜单名称" name="title" required>
            <a-input v-model:value="data.datas.title" style="width: 80%" />
          </a-form-item>
        </a-col>
        <a-col :md="12" :sm="24">
          <a-form-item label="菜单图标" name="icon" required>
            <a-input v-model:value="data.datas.icon" style="width: 80%" />
          </a-form-item>
        </a-col>
        <a-col :md="12" :sm="24">
          <a-form-item label="缓存页面" name="keepAlive">
            <a-switch v-model:checked="data.datas.keepAlive" checked-children="开" un-checked-children="关" checkedValue="true" unCheckedValue="false" />
          </a-form-item>
        </a-col>
        <a-col :md="12" :sm="24">
          <a-form-item label="是否隐藏" name="hidden">
            <a-switch v-model:checked="data.datas.hidden" checked-children="开" un-checked-children="关" checkedValue="true" unCheckedValue="false" />
          </a-form-item>
        </a-col>
      </a-row>
      <a-tabs v-model:activeKey="activeKey" centered>
        <a-tab-pane key="1" tab="目录">
          <a-row :gutter="24">
            <a-col :md="12" :sm="24">
              <a-form-item label="路由地址" name="path" required>
                <a-input v-model:value="data.datas.path" style="width: 80%" />
              </a-form-item>
            </a-col>
            <a-col :md="12" :sm="24">
              <a-form-item label="重定向页" name="redirect">
                <a-input v-model:value="data.datas.redirect" style="width: 80%" />
              </a-form-item>
            </a-col>
            <a-col :md="12" :sm="24">
              <a-form-item label="路由名称" name="name" required>
                <a-input v-model:value="data.datas.name" style="width: 80%" />
              </a-form-item>
            </a-col>
            <a-col :md="12" :sm="24">
              <a-form-item label="前端组件" name="component" required>
                <a-select v-model:value="data.datas.component" :options="options" style="width: 80%" />
              </a-form-item>
            </a-col>
          </a-row>
        </a-tab-pane>
        <a-tab-pane key="2" tab="菜单">
          <a-row :gutter="24">
            <a-col :md="12" :sm="24">
              <a-form-item label="路由地址" name="path" required>
                <a-input v-model:value="data.datas.path" style="width: 80%" />
              </a-form-item>
            </a-col>
            <a-col :md="12" :sm="24">
              <a-form-item label="父组件名" name="parentId">
                <a-tree-select
                    v-model:value="data.datas.parentId"
                    style="width: 80%"
                    :dropdown-style="{ maxHeight: '400px', overflow: 'auto' }"
                    tree-default-expand-all
                    :tree-data="props.treeData"
                    :fieldNames="state.fieldNames"
                />
              </a-form-item>
            </a-col>
            <a-col :md="12" :sm="24">
              <a-form-item label="路由名称" name="name" required>
                <a-input v-model:value="data.datas.name" style="width: 80%" />
              </a-form-item>
            </a-col>
            <a-col :md="12" :sm="24">
              <a-form-item label="前端组件" name="component" required>
                <a-input v-model:value="data.datas.component" style="width: 80%" />
              </a-form-item>
            </a-col>
          </a-row>
        </a-tab-pane>
        <a-tab-pane key="3" tab="按钮">Content of Tab Pane 3</a-tab-pane>
      </a-tabs>
    </a-form>
  </a-modal>
</template>
<script setup>
import { reactive, ref, onMounted } from 'vue'
import { notification } from 'ant-design-vue'
import { increaseSysMenu, updataSysMenu } from 'apis/SystemManagement/menumanagement'
const props = defineProps({
  treeData: Array
})

const activeKey = ref('1')
const tabPosition = ref('left')
const state = reactive({
  visible: false,
  title: '添加菜单',
  confirmLoading: false,
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
    path: '',
    component: 'RouteView',
    redirect: '',
    parentId: '',
    keepAlive: false,
    hidden: false,
    title: '',
    icon: ''
  }
})
const options = [
  {
    title: 'RouteView',
    value: 'RouteView'
  }
]
const rules = reactive({
  name: [{required: true, message: 'Please input name',}]
})
const formRef = ref()
const emit = defineEmits(['select'])

const addData = () => {
  state.title = '添加菜单'
  state.visible = true
}

const editData = (values) => {
  state.title = '编辑角色'
  data.datas = {...values}
  state.visible = true
}

const submitData = () => {
  formRef.value.validate().then((values) => {
    state.confirmLoading = true
    switch (activeKey.value){
      case '1':
        values.parentId = '0'
        break
      case '2':
        values.redirect = ''
        break
    }
    if (state.title === '添加菜单'){
      increaseSysMenu(values).then(res => {
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
      updataSysMenu(data.datas).then(res => {
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
