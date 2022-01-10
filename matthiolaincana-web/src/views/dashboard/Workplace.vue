<template>
<!--  <div style="width: 100%;height: 500px">
    <video id="my-player" width="100%" height="500px" class="video-js vjs-default-skin vjs-big-play-centered"/>
  </div>-->
  <a-popover placement="bottomRight" trigger="click" :overlayStyle="{ padding: '0 !important' }">
    <template #content >
      <a-checkbox v-model:checked="state.checkAll">
        <sapn style="font-size: 16px">列展示 / 排序</sapn>
        <a-button style="font-size: 16px" type="link">重置</a-button>
      </a-checkbox>
      <a-divider style="margin: 10px 0" />
      <a-checkbox-group v-model:value="value">
        <a-row>
          <draggable
              v-model="state.list"
              :animation="160"
              v-bind="state.dragOptions"
              dragClass="selectdragClass"
              chosenClass="selectChosenClass"
              :forceFallback="true"
              item-key="order"
          >
            <template #item="{ element }">
              <a-col :span="24" class="draggableSelect">
                <bars-outlined style="font-size: 20px;margin-right: 20px" />
                <a-checkbox value="A" style="font-size: 16px">{{ element.name }}</a-checkbox>
              </a-col>
            </template>
          </draggable>
        </a-row>
      </a-checkbox-group>
    </template>
    <SettingOutlined class="icon-ccd" />
  </a-popover>
  <a-button @click="player" >播放</a-button>
  <div style="width: 300px;background-color: white;padding: 10px">
    <a-checkbox v-model:checked="state.checkAll">
      <sapn style="font-size: 16px">列展示 / 排序</sapn>
      <a-button style="font-size: 16px" type="link">重置</a-button>
    </a-checkbox>
    <a-divider style="margin: 10px 0" />
    <a-checkbox-group v-model:value="value" style="width: 100%">
      <a-row style="width: 280px">
        <draggable
            v-model="state.list"
            :animation="160"
            v-bind="state.dragOptions"
            dragClass="selectdragClass"
            chosenClass="selectChosenClass"
            :forceFallback="true"
           item-key="order"
        >
          <template #item="{ element }">
            <a-col :span="24" class="draggableSelect">
              <bars-outlined style="font-size: 20px;margin-right: 20px" />
              <a-checkbox value="A" style="font-size: 16px">{{ element.name }}</a-checkbox>
            </a-col>
          </template>
        </draggable>
      </a-row>
    </a-checkbox-group>
  </div>
</template>

<script setup>
import { BarsOutlined, SettingOutlined } from '@ant-design/icons-vue'
import draggable from 'vuedraggable'
import Video from 'video.js'
import 'video.js/dist/video-js.css'
import {onMounted, ref, reactive, onBeforeUnmount, nextTick, computed} from 'vue'

const state = reactive({
  checkAll: false,
  indeterminate: true,
  checkedList: ['Apple', 'Orange'],
  plainOptions: ['Apple', 'Pear', 'Orange'],
  list: [
    { name: "Johnssss", id: 0 },
    { name: "Joao1111", id: 1 },
    { name: "Jean2222", id: 2 }
  ],
  dragging: false
})

const options = reactive({
  muted: true,
  autoplay: false,
  controls: true,
  width: '1200px',
  height: '500px',
  sources: [{
    src: 'http://stream.jinjiang.tv/1/sd/live.m3u8?_upt=2fb611a91608389152',
    type: 'application/x-mpegURL'
  }],
  dragOptions: computed(() => {
    return{
      animation: 10,
      group: 'description',
      disabled: false,
      ghostClass: 'ghost'
    }
  })
})

const value = ref([])

const video = ref()

onMounted(() => {
 /* nextTick(() => {
    video.value = Video('my-player', options)
  })*/
})

onBeforeUnmount(() => {
  if (video.value){
    video.value.dispose()
  }
})

function player() {
  video.value.play()
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
.flip-list-move {
  transition: transform 0.5s;
}
.no-move {
  transition: transform 0s;
}
.ghost {
  opacity: 0.5;
  background: #c8ebfb;
}
.list-group {
  min-height: 20px;
}
.list-group-item {
  cursor: move;
}
.list-group-item i {
  cursor: pointer;
}
.draggableSelect{
  height: 50px;
  width: 280px;
  line-height: 50px;
}
</style>
