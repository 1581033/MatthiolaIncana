<template>
  <a-card style="margin-bottom: 20px">
    <a-form layout="inline" :model="listQury">
      <a-form-item label="任务名称">
        <a-input v-model:value="listQury.name" placeholder="Username" />
      </a-form-item>
      <a-form-item>
        <a-button type="primary" @click="">
          <template #icon><SearchOutlined /></template>
          查询
        </a-button>
        <a-button type="primary" @click="$refs.expression.showModal()">
          <template #icon><SearchOutlined /></template>
          生成表达式
        </a-button>
      </a-form-item>
    </a-form>
  </a-card>
  <x-tabel
      :dataSource="state.dataSource"
      :columns="state.columns"
      :loading="state.tableLoding"
      @addClick=""
      @restClick=""
  >
    <template #bodyCell="{ column, text, record }">
      <template v-if="column && column.dataIndex === 'operation'">
        <a @click="$refs.expression.showModal()">编辑</a>
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
  <expression ref="expression" />
</template>

<script setup>
import {reactive} from "vue";
import {notification} from "ant-design-vue";
import Expression from "./modules/expression.vue";

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

const deleteData = (value) => {
}
</script>

<style lang="less" scoped>

</style>
