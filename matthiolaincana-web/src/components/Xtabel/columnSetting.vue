<template>
  <a-checkbox v-model:checked="state.checkAll" :indeterminate="state.indeterminate" style="margin-left: 24px;font-size: 16px;" @change="onCheckAllChange">列展示 / 排序</a-checkbox>
  <a-button style="font-size: 18px" type="link" @click="rest">重置</a-button>
  <a-divider style="margin: 10px 0" />
  <a-checkbox-group v-model:value="state.checkedList" style="width: 100%" @change="emitColumnChange">
    <draggable
        v-model="state.columns"
        :animation="300"
        v-bind="state.dragOptions"
        dragClass="selectdragClass"
        chosenClass="selectChosenClass"
        :forceFallback="true"
        item-key="dataIndex"
        @end="emitColumnChange"
    >
      <template #item="{ element, index }">
        <a-col :span="24" class="draggableSelect">
          <MyIcon type="icon-yidongshu" style="margin: 0 5px;" class="icon-hover"/>
          <a-checkbox :value="element.dataIndex" style="font-size: 16px">{{ element.title }}</a-checkbox>
        </a-col>
      </template>
    </draggable>
  </a-checkbox-group>
</template>

<script setup>
import draggable from 'vuedraggable'
import {computed, onMounted, reactive, watch} from "vue"
const emit = defineEmits(['columnChange'])
const props = defineProps({
  columns: {
    type: Array,
    default: () => ([])
  }
})
const state = reactive({
  columns: [],
  checkAll: true,
  indeterminate: false,
  plainOptions: computed(() => props.columns.map(s => s.dataIndex)),
  checkedList: [],
  dragOptions: computed(() => {
    return{
      animation: 10,
      group: 'description',
      disabled: false,
      ghostClass: 'ghost'
    }
  })
})
onMounted(() => {
  const colun = props.columns.map(value => ({...value}))
  Object.assign(state, {
    columns: colun,
    checkedList: colun.map(value => value.dataIndex)
  })
})
const onCheckAllChange = (any) => {
  Object.assign(state, {
    checkedList: any.target.checked ? state.plainOptions : [],
    indeterminate: false,
  })
  emitColumnChange()
}
const emitColumnChange = () => {
  const column = state.columns.filter((value) => state.checkedList.includes(value.dataIndex))
  emit('columnChange',column)
}
const rest = () => {
  const colun = props.columns.map(value => ({...value}))
  Object.assign(state, {
    checkAll: true,
    indeterminate: false,
    columns: colun,
    checkedList: colun.map(value => value.dataIndex)
  })
  emitColumnChange()
}

</script>

<style lang="less" scoped>
.selectChosenClass{
  opacity:0;
}
.selectdragClass{
  color: #409EFF;
  background-color: rgba(255, 255, 255, 0.59) !important;
  box-shadow:0 1px 10px rgba(0, 0, 0, 0.3);
}
.draggableSelect{
  height: 38px;
  line-height: 38px;
}
.icon-hover:hover{
  cursor: move;
}
</style>
