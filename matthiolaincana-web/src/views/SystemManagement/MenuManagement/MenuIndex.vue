<template>
  <a-card style="margin-bottom: 20px">
    <a-form layout="inline" :model="listQury">
      <a-form-item label="用户">
        <a-input v-model:value="listQury.name" placeholder="Username" />
      </a-form-item>
      <a-form-item>
        <a-button type="primary" @click="getSysMenuList">
          <template #icon><SearchOutlined /></template>
          查询
        </a-button>
      </a-form-item>
      <a-form-item>
        <a-button type="primary" @click="">
          <template #icon><reload-outlined /></template>
          重置
        </a-button>
      </a-form-item>
    </a-form>
  </a-card>
  <x-tabel
      :dataSource="state.dataSource"
      :columns="state.columns"
      :loading="state.tableLoding"
      @addClick="$refs.addSysMenu.addData()"
      @restClick="getSysMenuList"
  >
    <template #bodyCell="{ column, text, record }">
      <template v-if="column && column.dataIndex === 'icon'">
        <MyIcon :type="text" viewBox="0 0 1024 1024" style="color: #1890ff"/>
      </template>
      <template v-if="column && column.dataIndex === 'operation'">
        <a @click="$refs.addSysMenu.editData(record)">编辑</a>
        <a-popconfirm
            title="你确定删除吗?"
            ok-text="确定"
            cancel-text="取消"
            @confirm="deleteData(record.id)"
        >
          <a style="color: red;margin-left: 18px" >删除</a>
        </a-popconfirm>
      </template>
    </template>
  </x-tabel>
  <add-sys-menu ref="addSysMenu" :treeData="state.dataSource" @select="getSysMenuList" />
</template>

<script setup>
import { SearchOutlined, LockOutlined, PlusOutlined, ReloadOutlined } from '@ant-design/icons-vue'
import { ref, onMounted, reactive, computed, nextTick} from 'vue'
import { notification } from 'ant-design-vue'
import { inquireSysMenuList, inquireSysMenuHeadList, deleteSysMenu } from 'apis/SystemManagement/menumanagement'
import AddSysMenu from './modules/AddSysMenu.vue'
const state = reactive({
  dataSource: [],
  columns: [],
  editableData: [],
  tableLoding: false,
  visible: false
});

const listQury = reactive({
  page: 1,
  size: 10,
  user: 'asda',
  name: ''
})

const tableInput = ref(null)
const addSysMenu = ref(null)

onMounted(() => {
  getSysMenuHeadList()
})

function getSysMenuHeadList() {
  state.tableLoding = true
  inquireSysMenuHeadList(listQury).then(rs => {
    state.columns = rs.result
    state.columns.push({
      title: '操作',
      dataIndex: 'operation',
      width: '100px',
      align: 'center'
    })
    getSysMenuList()
  })
}

function getSysMenuList(){
  state.tableLoding = true
  inquireSysMenuList(listQury).then(res => {
    state.dataSource = res.result
    state.tableLoding = false
  })
}

const deleteData = (values) => {
  deleteSysMenu({ id: values }).then(res => {
    notification.success({ message: '删除成功' })
    getSysMenuList()
  })
}

</script>

<style lang="less" scoped>
</style>
