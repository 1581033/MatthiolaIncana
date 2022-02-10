<template>
  <a-card style="margin-bottom: 20px">
    <a-form ref="formRefDex" layout="inline" :model="state.listQury">
      <a-form-item label="字典名称">
        <a-select v-model:value="state.listQury.typeId" style="width: 200px" placeholder="请输入字典名称">
          <a-select-option v-for="item in state.sysDictTypeData" :value="item.id">
            {{ item.name }}
          </a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="字典标签" name="value">
        <a-input v-model:value="state.listQury.value" placeholder="请输入字典标签" />
      </a-form-item>
      <a-form-item label="状态" name="status">
        <a-select v-model:value="state.listQury.status" style="width: 200px" placeholder="字典状态" allowClear>
          <a-select-option v-for="item in state.sysNormalDisable" :value="item.code">
            {{ item.value }}
          </a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item>
        <a-button type="primary" @click="getSysDictDataList">
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
      @addClick="$refs.addSysDistData.addData(state.listQury.typeId)"
      @restClick="getSysDictDataList"
  >
    <template #bodyCell="{ column, text, record }">
      <template v-if="column && column.dataIndex === 'status'">
        <a-tag :color="state.sysNormalDisable.find((item) => text === item.code).listClass">
          {{ state.sysNormalDisable.find((item) => text === item.code).value }}
        </a-tag>
      </template>
      <template v-if="column && column.dataIndex === 'value'">
        <a-tag v-if="record.listClass !== 'default'" :color="record.listClass">{{ text }}</a-tag>
        <span v-else >{{ text }}</span>
      </template>
      <template v-if="column && column.dataIndex === 'operation'">
        <a-space :size="10" >
          <a @click="$refs.addSysDistData.editData(record)">编辑</a>
          <a-popconfirm
              title="你确定删除吗?"
              ok-text="确定"
              cancel-text="取消"
              @confirm="deleteData(record.id)"
          >
            <a style="color: red;" >删除</a>
          </a-popconfirm>
        </a-space>
      </template>
    </template>
  </x-tabel>
  <add-sys-dist-data ref="addSysDistData" :sysNormalDisable="state.sysNormalDisable" @select="getSysDictDataList"/>
</template>

<script setup>
import { useRoute } from 'vue-router'
import {onMounted, reactive, ref} from "vue";
import Api from 'apis/baseresponse'
import {notification} from "ant-design-vue";
import AddSysDistData from "./modules/AddSysDistData.vue";

const state = reactive({
  requestApi: 'sysDictData',
  dataSource: [],
  columns: [],
  editableData: [],
  tableLoding: false,
  visible: false,
  sysNormalDisable: [],
  listQury: {},
  sysDictTypeData: []
});

const formRefDex = ref()

onMounted(() => {
  state.listQury.typeId = useRoute().params.id
  getSysDictDataHeadList()
  getFilters()
})

const getFilters = () => {
  Api.dictData('sys_normal_disable').then(res => {
    state.sysNormalDisable = res.result
  })
  Api.inquireList({},'sysDictType').then(res => {
    state.sysDictTypeData = res.result
  })
}

const getSysDictDataHeadList = () => {
  state.tableLoding = true
  Api.inquireHeadList({},state.requestApi).then(rs => {
    state.columns = rs.result
    state.columns.push({
      title: '操作',
      dataIndex: 'operation',
      width: '100px',
      align: 'center'
    })
    getSysDictDataList()
  })
}

const getSysDictDataList = () => {
  state.tableLoding = true
  Api.inquireList(state.listQury,state.requestApi).then(res => {
    state.dataSource = res.result
    state.tableLoding = false
  })
}

const deleteData = (values) => {
  Api.delete({ id: values },state.requestApi).then(res => {
    notification.success({ message: '删除成功' })
    getSysDictDataList()
  })
}

const restGetData = () => {
  formRefDex.value.resetFields()
  getSysDictDataList()
}
</script>

<style lang="less" scoped>

</style>
