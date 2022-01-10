<template>
  <a-card style="margin-bottom: 20px">
    <a-form layout="inline" :model="listQury" style="float: left">
      <a-form-item label="用户">
        <a-input v-model:value="listQury.name" placeholder="Username" />
      </a-form-item>
    </a-form>
    <div style="float: right">
      <a-button type="primary" style="margin-right: 10px" @click="getSysRoleList">
        <template #icon><SearchOutlined /></template>
        查询
      </a-button>
      <a-button type="primary" @click="">
        <template #icon><reload-outlined /></template>
        重置
      </a-button>
    </div>

  </a-card>

  <x-tabel
      :dataSource="state.dataSource"
      :columns="state.columns"
      :loading="state.tableLoding"
      @addClick="$refs.addSysRole.addData()"
      @restClick="getSysRoleList"
  >
    <template #bodyCell="{ column, text, record }">
      <template v-if="column && column.dataIndex === 'operation'">
        <a @click="$refs.addSysRole.editData(record)">编辑</a>
        <a-popconfirm
            title="你确定删除吗?"
            ok-text="确定"
            cancel-text="取消"
            @confirm="deleteData(record.id)"
        >
          <a style="color: red;margin-left: 18px" >删除</a>
        </a-popconfirm>
      </template>
      <template v-if="column && column.dataIndex === 'status'">
        <span :class="[ text === 'true' ? 'ant-badge-status-success' : 'ant-badge-status-default', 'ant-badge-status-dot'] " />
        <span class="ant-badge-status-text">{{ text == 'true' ? '已启用' : '未启用' }}</span>
      </template>
    </template>
  </x-tabel>
  <add-sys-role ref="addSysRole" @select="getSysRoleList" />
</template>

<script setup>
import { SearchOutlined, LockOutlined, PlusOutlined, ReloadOutlined, FullscreenOutlined, SettingOutlined, ColumnHeightOutlined } from '@ant-design/icons-vue'
import { onMounted, reactive} from 'vue'
import { inquireSysRoleHeadList, inquireSysRoleList, deleteSysRole } from 'apis/SystemManagement/rolemanagement'
import {notification} from "ant-design-vue"
import AddSysRole from './modules/AddSysRole.vue'

const state = reactive({
  dataSource: [],
  columns: [],
  editableData: [],
  tableLoding: false,
  visible: false,
  isFullscreen: false,
  columnSize: 'default',
  rowClassName: null
});
const listQury = reactive({
  page: 1,
  size: 10,
  user: 'asda',
  name: ''
})

onMounted(() => {
  getSysRoleHeadList()
})

const getSysRoleHeadList = () =>{
  state.tableLoding = true
  inquireSysRoleHeadList(listQury).then(res => {
    state.columns = res.result
    state.columns.push({
      title: '操作',
      dataIndex: 'operation',
      width: '100px',
      align: 'center'
    })
    getSysRoleList()
  })
}

const getSysRoleList = () => {
  state.tableLoding = true
  inquireSysRoleList(listQury).then(res => {
    state.dataSource = res.result
    state.tableLoding = false
  })
}

const deleteData = (values) => {
  deleteSysRole({ id: values }).then(res => {
    notification.success({ message: '删除成功' })
    getSysRoleList()
  })
}

</script>

<style lang="less" scoped>
</style>
