import {createRouter, createWebHashHistory} from 'vue-router'

export const constantRouterMap = [
    {
        path: '/user',
        component: () => import('@/layouts/UserLayout.vue'),
        redirect: '/user/login',
        hidden: true,
        children: [
            {
                path: 'login',
                name: 'login',
                component: () => import('@/views/user/Login.vue')
            }
        ]
    }
]

export default createRouter({
    history: createWebHashHistory(),
    routes: constantRouterMap
})
