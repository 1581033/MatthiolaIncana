import { getUserMenu } from 'apis/login/login'
import { BasicLayout, RouteView } from '@/layouts'
import { shallowRef } from 'vue'

const constantRouterComponents = {
  BasicLayout: shallowRef(BasicLayout),
  RouteView: shallowRef(RouteView)
}

const rootRouter = [
  {
    path: '/',
    name: 'index',
    component: 'BasicLayout',
    redirect: '/dashboard',
    meta: {title: '首页'},
    children: [
      /*{
        path: '/dashboard',
        name: 'dashboard',
        component: RouteView,
        meta: {title: '工作台',icon: 'icon-dashboard'},
        redirect: '/dashboard/workplace',
        children: [{
            name: 'Workplace',
            path: '/dashboard/workplace',
            meta: {title: '工作台',icon: 'icon-dashboard'},
            component: () => import('@/views/dashboard/Workplace.vue')
        }]
      },
      {
        path: '/SystemManagement',
        name: 'SystemManagement',
        component: () => import('@/layouts/RouteView.vue'),
        meta: {title: '系统管理',icon: 'icon-dashboard'},
        redirect: '/SystemManagement/UserManagement',
        children: [{
          name: 'UserManagement',
          path: '/SystemManagement/UserManagement',
          meta: {title: '用户管理',icon: 'icon-dashboard'},
          component: () => import('@/views/SystemManagement/UserManagement/UserIndex.vue')
        },{
          name: 'MenuManagement',
          path: '/SystemManagement/MenuManagement',
          meta: {title: '菜单管理',icon: 'icon-dashboard'},
          component: () => import('@/views/SystemManagement/MenuManagement/MenuIndex.vue')
        }]
      }*/
    ]
  }
]

export const generatorDynamicRouter = roles => {
  return new Promise((resolve, reject) => {
    getUserMenu(roles.map(rs => rs.id)).then(res =>{
      const result = res.result
      const childrenNav = []
      listToTree(result, childrenNav, '0')
      rootRouter[0].children = childrenNav
      const routers = generator(rootRouter)
      resolve(routers)
    })
  })
}

export const generator = (routerMap, parent) => {
  return routerMap.map(item => {
    const { title, hideChildrenInMenu, hiddenHeaderContent, target, icon } = item.meta || {}
    const currentRouter = {
      path: item.path,
      name: item.name || '',
      component: constantRouterComponents[ item.component ] || (() => import(`../views/${item.component}`)),
      meta: {
        title: title,
        icon: icon || undefined,
        hiddenHeaderContent: hiddenHeaderContent,
        hideChildrenInMenu: hideChildrenInMenu,
        target: target,
        permission: item.name
      },
      hidden: item.hidden === 'true'
    }
    // 为了防止出现后端返回结果不规范，处理有可能出现拼接出两个 反斜杠
    if (!currentRouter.path.startsWith('http')) {
      currentRouter.path = currentRouter.path.replace('//', '/')
    }
    // 重定向
    item.redirect && (currentRouter.redirect = item.redirect)
    // 是否有子菜单，并递归处理
    if (item.children && item.children.length > 0) {
      // Recursion
      currentRouter.children = generator(item.children, currentRouter)
    }
    return currentRouter
  })
}

const listToTree = (list, tree, parentId) => {
  list.forEach(item => {
    if (item.parentId === parentId) {
      const child = {
        ...item,
        key: item.key || item.name,
        children: []
      }
      listToTree(list, child.children, item.id)
      if (child.children.length <= 0) {
        delete child.children
      }
      tree.push(child)
    }
  })
}
