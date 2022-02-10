<template>
  <a-card style="margin-bottom: 20px">
    <a-form ref="formRefDex" layout="inline" :model="state.listQury">
      <a-form-item label="字典名称" name="name">
        <a-input v-model:value="state.listQury.name" placeholder="请输入字典名称" />
      </a-form-item>
      <a-form-item label="字典类型" name="code">
        <a-input v-model:value="state.listQury.code" placeholder="请输入字典类型" />
      </a-form-item>
      <a-form-item label="状态" name="status">
        <a-select v-model:value="state.listQury.status" style="width: 200px" placeholder="字典状态" allowClear>
          <a-select-option v-for="item in state.sysNormalDisable" :value="item.code">
            {{ item.value }}
          </a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="创建时间" name="dateTime">
        <a-range-picker  v-model:value="state.listQury.dateTime" />
      </a-form-item>
      <a-form-item>
        <a-button type="primary" @click="getSysDictTypeList">
          <template #icon><SearchOutlined /></template>
          查询
        </a-button>
      </a-form-item>
      <a-form-item>
        <a-button type="primary" @click="restGetData">
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
      @addClick="$refs.addSysDistType.addData()"
      @restClick="restGetData"
  >
    <template #bodyCell="{ column, text, record }">
      <template v-if="column && column.dataIndex === 'code'">
        <a-button type="link" @click="$router.push({ path: '/SystemManagement/DictDataManagement/' + record.id })">{{ text }}</a-button>
      </template>
      <template v-if="column && column.dataIndex === 'status'">
        <a-tag :color="state.sysNormalDisable.find((item) => text === item.code).listClass">
          {{ state.sysNormalDisable.find((item) => text === item.code).value }}
        </a-tag>
      </template>
      <template v-if="column && column.dataIndex === 'operation'">
        <a @click="$refs.addSysDistType.editData(record)">编辑</a>
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
  <add-sys-dist-type ref="addSysDistType" :sysNormalDisable="state.sysNormalDisable" @select="getSysDictTypeList" />
</template>

<script setup>
import { SearchOutlined, LockOutlined, PlusOutlined, ReloadOutlined } from '@ant-design/icons-vue'
import {reactive, onMounted, ref} from "vue";
import Api from 'apis/baseresponse'
import {notification} from "ant-design-vue";
import AddSysDistType from "./modules/AddSysDistType.vue";

const state = reactive({
  requestApi: 'sysDictType',
  dataSource: [],
  columns: [],
  editableData: [],
  tableLoding: false,
  visible: false,
  sysNormalDisable: [],
  listQury: {}
});

const formRefDex = ref()

onMounted(() => {
  getSysDictTypeHeadList()
  getFilters()
})

const getFilters = () => {
  Api.dictData('sys_normal_disable').then(res => {
    state.sysNormalDisable = res.result
  })
}

const getSysDictTypeHeadList = () => {
  state.tableLoding = true
  Api.inquireHeadList({},state.requestApi).then(rs => {
    state.columns = rs.result
    state.columns.push({
      title: '操作',
      dataIndex: 'operation',
      width: '100px',
      align: 'center'
    })
    getSysDictTypeList()
  })
}

const getSysDictTypeList = () => {
  state.tableLoding = true
  Api.inquireList(state.listQury,state.requestApi).then(res => {
    state.dataSource = res.result
    state.tableLoding = false
  })
}

const deleteData = (values) => {
  Api.delete({ id: values },state.requestApi).then(res => {
    notification.success({ message: '删除成功' })
    getSysDictTypeList()
  })
}

const restGetData = () => {
  formRefDex.value.resetFields()
  console.log(formRefDex.value)
  getSysDictTypeList()
}

</script>

<style lang="less" scoped>

</style>
