<template>
  <a-card style="margin-bottom: 20px">
    <a-form layout="inline" :model="listQury">
      <a-form-item label="用户">
        <a-input v-model:value="listQury.name" placeholder="Username" />
      </a-form-item>
      <a-form-item>
        <a-button type="primary" @click="getSysUserList">
          <template #icon><SearchOutlined /></template>
          查询
        </a-button>
      </a-form-item>
    </a-form>
  </a-card>
  <x-tabel
      :dataSource="state.dataSource"
      :columns="state.columns"
      :loading="state.tableLoding"
      @addClick="$refs.addSysUser.addData()"
      @restClick="getSysUserList"
  >
    <template #bodyCell="{ column, text, record }">
<!--      <div v-if="state.editableData[0] === record.key && state.editableData[1] === column.dataIndex" >
        <a-input ref="tableInput" size="small" v-model:value="state.dataSource[record.key][column.dataIndex]" @blur="save(record)" />
      </div>
      <div v-else @click="deitl(record.key,column.dataIndex)">
        {{ text || ' ' }}
      </div>-->
      <template v-if="column && column.dataIndex === 'status'">
        <span :class="[ text === '1' ? 'ant-badge-status-success' : 'ant-badge-status-default', 'ant-badge-status-dot'] " />
        <span class="ant-badge-status-text">{{ text == '1' ? '已启用' : '未启用' }}</span>
      </template>
      <template v-if="column && column.dataIndex === 'operation'">
        <a @click="$refs.addSysUser.editData(record)">编辑</a>
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
  <add-sys-user ref="addSysUser" @select="inquire" />
</template>

<script setup>
import AddSysUser from './modules/AddSysUser.vue'
import { notification } from 'ant-design-vue'
import { SearchOutlined, LockOutlined } from '@ant-design/icons-vue'
import { ref, onMounted, reactive, computed, nextTick} from 'vue'
import { inquireSysUserList, inquireSysUserHeadList, deleteSysUser } from 'apis/SystemManagement/usermanagement'
const state = reactive({
  dataSource: [],
  columns: [],
  editableData: [],
  tableLoding: false
});
const listQury = reactive({
  user: 'asda',
  name: ''
})

onMounted(() => {
  getSysUserHeadList()
})

function getSysUserHeadList() {
  state.tableLoding = true
  inquireSysUserHeadList(listQury).then(rs => {
    state.columns = rs.result
    state.columns.push({
      title: '操作',
      dataIndex: 'operation',
      width: '100px',
      align: 'center'
    })
    getSysUserList()
  })
}

function getSysUserList(){
  inquireSysUserList(listQury).then(res => {
    state.dataSource = res.result.records
    state.dataSource.forEach((s,index) => s.key =  index)
    state.tableLoding = false
  })
}

const deleteData = (value) => {
  deleteSysUser({ id: value }).then(res => {
    notification.success({ message: '删除成功' })
    getSysUserList()
  })
}

/*function deitl(key,dataIndex){
  this.state.editableData[0] = key
  this.state.editableData[1] = dataIndex
  nextTick( () => tableInput.value.focus())
}*/

</script>

<style lang="less" scoped>
</style>
