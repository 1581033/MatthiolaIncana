import axios from 'axios'
import storage from 'store'
import store from '@/store/index'

import { ACCESS_TOKEN } from '@/store/mutation-types'
import notification from 'ant-design-vue/es/notification'

const service = axios.create({
    baseURL: import.meta.env.VITE_API_DOMAIN,
    withCredentials: false,
    timeout: 30000
})

service.interceptors.request.use(
    config => {
        config.headers[ACCESS_TOKEN] = storage.get(ACCESS_TOKEN)
        return config
    },
    error => {
        console.log(error)
        Promise.reject(error)
    }
)

service.interceptors.response.use(
    response => {
        const res = response.data
        switch (res.code){
            case 1000:
                notification.error({
                    message: '错误信息',
                    description: res.message
                })
                return null
            default:
                return res
        }
    },
    error => {
        console.log(1212313132)
        const data = error.response.data
        const token = storage.get(ACCESS_TOKEN)
        switch (error.response.status){
            case 1000:
                if (token) {
                    store.dispatch('Logout').then(() => {
                        setTimeout(() => {
                            window.location.reload()
                        }, 1500)
                    })
                }
                break
            default:
                notification.error({
                    message: '错误信息',
                    description: data.message
                })
                break
        }
        return Promise.reject(error)
    }
)

export default service
