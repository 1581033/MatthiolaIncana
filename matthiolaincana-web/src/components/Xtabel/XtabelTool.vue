<template>
  <div style="font-size: 20px">
    <div style="float: left;">
      <a-tooltip>
        <template #title>增加</template>
        <PlusOutlined class="icon-ccd" @click="emit('addClick')" />
      </a-tooltip>
    </div>
    <div style="float: right;">
      <a-tooltip>
        <template #title>表格斑马纹</template>
        <a-switch v-model:checked="state.checked" checked-children="开" un-checked-children="关" style="margin-right: 10px" @change="switchCherked" />
      </a-tooltip>
      <a-tooltip>
        <template #title>刷新</template>
        <ReloadOutlined class="icon-ccd" @click="emit('restClick')" />
      </a-tooltip>
      <a-tooltip>
        <template #title>密度</template>
        <a-dropdown :trigger="['click']" placement="bottomRight">
          <ColumnHeightOutlined class="icon-ccd" />
          <template #overlay>
            <a-menu @click="columnSize" :selectedKeys="state.columnSizeKey" >
              <a-menu-item key="middle" style="color: #409EFF;width: 100px;height: 40px;font-size: 16px">默认</a-menu-item>
              <a-menu-item key="default" style="color: #409EFF;width: 100px;height: 40px;font-size: 16px">中等</a-menu-item>
              <a-menu-item key="small" style="color: #409EFF;width: 100px;height: 40px;font-size: 16px">紧凑</a-menu-item>
            </a-menu>
          </template>
        </a-dropdown>
      </a-tooltip>
      <a-tooltip>
        <template #title>列设置</template>
        <a-popover placement="bottomRight" trigger="click">
          <template #content>
            <a-checkbox v-model:checked="state.checkAll" :indeterminate="state.indeterminate" style="margin-left: 24px;font-size: 16px;" @change="onCheckAllChange">列展示 / 排序</a-checkbox>
            <a-button style="font-size: 16px" type="link">重置</a-button>
            <a-divider style="margin: 10px 0" />
            <a-checkbox-group v-model:value="state.checkedList" style="width: 100%">
              <draggable
                  v-model="props.columns"
                  :animation="160"
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
                    <a-checkbox :value="index" style="font-size: 16px" @change="changeCheck">{{ element.title }}</a-checkbox>
                  </a-col>
                </template>
              </draggable>
            </a-checkbox-group>
          </template>
          <SettingOutlined class="icon-ccd" />
        </a-popover>
      </a-tooltip>
      <a-tooltip>
        <template #title>全屏</template>
        <FullscreenOutlined class="icon-ccd" />
      </a-tooltip>
    </div>
  </div>
</template>

<script setup>
import draggable from 'vuedraggable'
import { PlusOutlined, ReloadOutlined, MoreOutlined, FullscreenOutlined, SettingOutlined, ColumnHeightOutlined } from '@ant-design/icons-vue'
import {computed, reactive, watch} from 'vue'
const props = defineProps({
  columns: Array
})

const emit = defineEmits(['addClick','restClick','columnSize','switchCherked'])

const state = reactive({
  columnSizeKey: ['default'],
  checked: false,
  checkAll: true,
  indeterminate: false,
  plainOptions: [],
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
watch(
    () => props.columns,
    val => {
      state.plainOptions = val.map((s,index) => index)
      state.checkedList = state.plainOptions
    }
)
const columnSize = (value) => {
  state.columnSizeKey = [value.key]
  emit('columnSize', state.columnSizeKey[0])
}
const switchCherked = (value) => {
  if (value){
    emit('switchCherked', 'table-striped')
  }else {
    emit('switchCherked', null)
  }
}
const onCheckAllChange = (any) => {
  Object.assign(state, {
    checkedList: any.target.checked ? state.plainOptions : [],
    indeterminate: false,
  })
}
const changeCheck = (checkedValue) => {
  /*if (checkedValue.target.checked){
    props.columns[checkedValue.target.value].colSpan = 1
  }else {
    props.columns[checkedValue.target.value].colSpan = 0
  }*/
  console.log(checkedValue)
}
</script>

<style lang="less" scoped>
.icon-ccd{
  margin: 0 10px 20px 10px;
}
.selectChosenClass{
  opacity:0;
}
.selectdragClass{
  color: #409EFF;
  background-color: rgba(255, 255, 255, 0.59) !important;
  box-shadow:0 1px 10px rgba(0, 0, 0, 0.3);
}
.draggableSelect{
  height: 30px;
  line-height: 30px;
}
.icon-hover:hover{
  cursor: move;
}
</style>
