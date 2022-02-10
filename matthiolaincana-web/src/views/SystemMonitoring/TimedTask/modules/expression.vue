<template>
  <a-modal v-model:visible="state.visible" :title="state.title"  width="50%" ok-text="保存" cancel-text="取消" :confirmLoading="state.confirmLoading" @cancel="" @ok="submitData">
    <a-tabs v-model:activeKey="state.activeKey">
      <a-tab-pane key="1" tab="秒">
        <a-radio-group v-model:value="state.value">
          <a-radio :style="radioStyle" :value="1">
            秒，允许的通配符[,-*/]
          </a-radio>
          <a-radio :style="radioStyle" :value="2">
            周期从
            <a-input-number v-model:value="state.otherValue" size="small" :min="1" :max="10" />
            -
            <a-input-number v-model:value="state.otherValue" size="small" :min="1" :max="10" />
            秒
          </a-radio>
          <a-radio :style="radioStyle" :value="3">
            从
            <a-input-number v-model:value="state.otherValue" size="small" :min="1" :max="10" />
            秒开始，每
            <a-input-number v-model:value="state.otherValue" size="small" :min="1" :max="10" />
            秒执行一次
          </a-radio>
          <a-radio :style="radioStyle" :value="4">
            指定
            <a-select v-model:value="state.otherValues" :options="state.options" mode="multiple" style="width: 200px"/>
          </a-radio>
        </a-radio-group>
      </a-tab-pane>
      <a-tab-pane key="2" tab="分钟" force-render>Content of Tab Pane 2</a-tab-pane>
      <a-tab-pane key="3" tab="小时">Content of Tab Pane 3</a-tab-pane>
      <a-tab-pane key="4" tab="日">Content of Tab Pane 3</a-tab-pane>
      <a-tab-pane key="5" tab="月">Content of Tab Pane 3</a-tab-pane>
      <a-tab-pane key="6" tab="周">Content of Tab Pane 3</a-tab-pane>
      <a-tab-pane key="7" tab="年">Content of Tab Pane 3</a-tab-pane>
    </a-tabs>
    <a-row :gutter="[2,16]" style="font-size: 12px;text-align: center">
      <a-col :span="1">
        <span class="express-span-type">秒</span>
        <a-input v-model:value="state.value" class="express-input-type" />
      </a-col>
      <a-col :span="1">
        分钟
        <a-input v-model:value="state.value" class="express-input-type" />
      </a-col>
      <a-col :span="1">
        小时
        <a-input v-model:value="state.value" class="express-input-type" />
      </a-col>
      <a-col :span="1">
        日
        <a-input v-model:value="state.value" class="express-input-type" />
      </a-col>
      <a-col :span="1">
        月
        <a-input v-model:value="state.value" class="express-input-type" />
      </a-col>
      <a-col :span="1">
        年
        <a-input v-model:value="state.value" class="express-input-type" />
      </a-col>
      <a-col :span="1">
        周
        <a-input v-model:value="state.value" class="express-input-type" />
      </a-col>
      <a-col :span="17">
        Cron表达式
        <a-input v-model:value="state.value" class="express-input-type" />
      </a-col>
    </a-row>
  </a-modal>
</template>

<script setup>
import {reactive} from "vue";

const state = reactive({
  visible: false,
  title: 'Cron表达式生成器',
  confirmLoading: false,
  activeKey: 1,
  value: 1,
  otherValue: '',
  otherValues: [1],
  options: [...Array(59)].map((_, i) => ({ value: i + 1 }))
})

const radioStyle = reactive({
  display: 'flex',
  height: '50px',
  lineHeight: '50px',
});

const showModal = () => {
  state.visible = true
  console.log([...Array(59)].map((_, i) => ({ value: i + 1 })))
}

const submitData = () => {

}

defineExpose({
  showModal
})

</script>

<style lang="less" scoped>
.express-input-type{
  text-align: center;
}
.express-span-type{
  height: 15px;
}
</style>
