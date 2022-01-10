<template>
  <div class="main">
    <a-form id="formLogin" class="user-layout-login" ref="formLogin" :model="listQury" :rules="rules">
      <a-alert v-if="state.isLoginError" type="error" showIcon style="margin-bottom: 24px;" message="账户或密码错误" />
      <a-form-item name="username">
        <a-input v-model:value="listQury.username" size="large" type="text" placeholder="账号">
          <template #prefix>
            <UserOutlined style="color: #cecece" />
          </template>
        </a-input>
      </a-form-item>
      <a-form-item name="password">
        <a-input-password v-model:value="listQury.password" size="large" placeholder="密码">
          <template #prefix>
            <UnlockOutlined style="color: #cecece" />
          </template>
        </a-input-password>
      </a-form-item>
      <a-form-item style="margin-top:24px">
        <a-button size="large" type="primary" class="login-button" :loading="state.loginBtn" @click.prevent="handleSubmit">登录</a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<script setup>
import { useStore } from 'vuex'
import { notification } from 'ant-design-vue'
import { useRouter } from 'vue-router'
import { UnlockOutlined, UserOutlined } from '@ant-design/icons-vue';
import { reactive, computed, ref } from 'vue'
const state = reactive({
  loginBtn: false,
  isLoginError: false
})

const listQury = reactive({
  username: '',
  password: ''
})

const rules = {
  username: [
    { required: true, message: '请输入帐户名', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码！', trigger: 'blur' }
  ]
}

const formLogin = ref()

const store = useStore()

const router = useRouter()

const name = computed(() => store.getters.userInfo.name)

function handleSubmit(){
  state.loginBtn = true
  formLogin.value.validate()
      .then((values) => {
        store.dispatch('Login',values)
            .then((res) => loginSuccess(res))
            .catch(err => requestFailed(err))
            .finally(() => {
              state.loginBtn = false
            })
      })
      .catch(err => {
        setTimeout(() => {
          state.loginBtn = false
        },600)
      })
}

function loginSuccess(res){
  if (res.code !== 200) {
    state.isLoginError = true
  } else {
    router.push({ path: '/' })
  }
}

function requestFailed(err){
  notification.error({message: '错误', description: ((err.response || {}).data || {}).message || '请求出现错误，请稍后再试',duration: 4})
}


/*
export default {
  components: {
    UnlockOutlined,
    UserOutlined
  },
  data () {
    return {
      listQury: {
        username: '',
        password: ''
      }
    }
  },
  created () {},
  methods: {
    ...mapActions(['Login']),
    handleSubmit() {
      const { listQury, Login} = this
      Login(listQury)
          .then((res) => this.loginSuccess(res))
          .catch(err => this.requestFailed(err))
          .finally(() => {
          })
    },
    loginSuccess(res) {
      console.log(res)
      if (res.code !== 200) {
        console.log('不是路由')
      } else {
        this.$router.push({ path: '/' }, () => {
          console.log('路由')
        })
      }
      console.log(res)
    },
    requestFailed(res) {
      console.log(res)
    }
  }
}
*/
</script>

<style lang="less" scoped>
.user-layout-login {
  .login-button{
    width: 100%;
  }
}
.laber-tiltle{
  text-align: center;
  font-size: 33px;
  color: rgba(0,0,0,.85);
  font-family: Avenir,Helvetica Neue,Arial,Helvetica,sans-serif;
  font-weight: 600;
}
</style>
