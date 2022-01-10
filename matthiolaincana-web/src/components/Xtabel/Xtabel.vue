<template>
  <a-card id="x-tabel">
    <div style="font-size: 20px">
      <div style="float: left;">
        <a-button type="primary" size="middle" style="margin-right: 10px">
          <template #icon>
            <DownloadOutlined />
          </template>
          导出
        </a-button>
        <a-button type="primary" size="middle" style="margin-right: 10px">
          <template #icon>
            <UploadOutlined />
          </template>
          导入
        </a-button>
        <a-button type="primary" size="middle" style="margin-right: 10px" @click="emit('addClick')">
          <template #icon>
            <PlusOutlined />
          </template>
          增加
        </a-button>
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
                <a-menu-item v-for="item in state.menus" :key="item.key" style="color: #409EFF;width: 100px;height: 40px;font-size: 16px">{{ item.title }}</a-menu-item>
              </a-menu>
            </template>
          </a-dropdown>
        </a-tooltip>
        <a-tooltip>
          <template #title>列设置</template>
          <a-popover placement="bottomRight" trigger="click" :getPopupContainer="triggerNode => triggerNode.parentNode">
            <template #content>
              <column-setting :columns="props.columns" @columnChange="columnChange" />
            </template>
            <SettingOutlined class="icon-ccd" />
          </a-popover>
        </a-tooltip>
        <a-tooltip>
          <template #title>全屏</template>
          <FullscreenOutlined v-if="!state.isFull" class="icon-ccd" @click="showFull" />
          <FullscreenExitOutlined v-else class="icon-ccd" @click="delFull" />
        </a-tooltip>
      </div>
    </div>
    <a-table
        v-bind="$attrs"
        :loading="props.loading"
        :columns="state.columns"
        :rowClassName="(_record, index) => (index % 2 === 1 ? state.rowClassName : null)"
        :size="state.columnSize"
        rowKey="id"
        bordered
    >
      <template v-for="(index, name) in $slots" #[name]="{ column, text, record }">
        <slot :name="name" :column="column" :text="text" :record="record" />
      </template>
    </a-table>
  </a-card>
</template>
<script setup>
import { PlusOutlined, ReloadOutlined, UploadOutlined, DownloadOutlined, FullscreenExitOutlined, FullscreenOutlined, SettingOutlined, ColumnHeightOutlined } from '@ant-design/icons-vue'
import { reactive, watch } from 'vue'
import ColumnSetting from './columnSetting.vue'
const emit = defineEmits(['addClick','restClick','columnSize','switchCherked'])
const props = defineProps({
  columns: Array,
  loading: Boolean
})
const state = reactive({
  columns: [],
  columnSizeKey: ['default'],
  checked: false,
  columnSize: 'default',
  rowClassName: null,
  isFull: false,
  menus: [
    {key: 'default',title: '默认'},
    {key: 'middle',title: '中等'},
    {key: 'small',title: '紧凑'},
  ]
})
watch(
    () => props.columns,
    (val) => {
      Object.assign(state, {
        columns: val
      })
    }
)
const switchCherked = (value) => {
  Object.assign(state, {
    rowClassName: value ? 'table-striped' : null
  })
}
const columnSize = (value) => {
  console.log(props.columns)
  Object.assign(state, {
    columnSize: value.key,
    columnSizeKey: value.keyPath
  })
}
const columnChange = (value) => {
  Object.assign(state, {
    columns: value
  })
}
const showFull = () => {
  const full = document.getElementById('x-tabel')
  if(full.requestFullscreen) {
    full.requestFullscreen();
  } else if(full.mozRequestFullScreen) {
    full.mozRequestFullScreen();
  } else if(full.webkitRequestFullscreen) {
    full.webkitRequestFullscreen();
  } else if(full.msRequestFullscreen) {
    full.msRequestFullscreen();
  }
  state.isFull = true
}
const delFull = () => {
  if(document.exitFullscreen) {
    document.exitFullscreen();
  } else if(document.mozCancelFullScreen) {
    document.mozCancelFullScreen();
  } else if(document.webkitExitFullscreen) {
    document.webkitExitFullscreen();
  }
  state.isFull = false
}
</script>
<style lang="less" scoped>
.icon-ccd{
  margin: 0 10px 20px 10px;
}
/deep/.ant-popover-inner-content{
  padding: 8px 0;
}
</style>
