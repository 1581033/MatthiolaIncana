import Layout from '@/layouts';
import ListIcon from '@/assets/assets-slide-list.svg';
import FormIcon from '@/assets/assets-slide-form.svg';
import DetailIcon from '@/assets/assets-slide-detail.svg';
import LogIcon from '@/assets/assets-log.svg';

export default [
  {
    path: '/list',
    name: 'list',
    component: Layout,
    redirect: '/list/base',
    meta: { title: '列表页', icon: ListIcon },
    children: [
      {
        path: 'base',
        name: 'ListBase',
        component: () => import('@/pages/list/base/index.vue'),
        meta: { title: '基础列表页' },
      },
      {
        path: 'card',
        name: 'ListCard',
        component: () => import('@/pages/list/card/index.vue'),
        meta: { title: '卡片列表页' },
      },
      {
        path: 'filter',
        name: 'ListFilter',
        component: () => import('@/pages/list/filter/index.vue'),
        meta: { title: '筛选列表页' },
      },
      {
        path: 'tree',
        name: 'ListTree',
        component: () => import('@/pages/list/tree/index.vue'),
        meta: { title: '树状筛选列表页' },
      },
    ],
  },
  {
    path: '/form',
    name: 'form',
    component: Layout,
    redirect: '/form/base',
    meta: { title: '表单页', icon: FormIcon },
    children: [
      {
        path: 'base',
        name: 'FormBase',
        component: () => import('@/pages/form/base/index.vue'),
        meta: { title: '基础表单页' },
      },
      {
        path: 'step',
        name: 'FormStep',
        component: () => import('@/pages/form/step/index.vue'),
        meta: { title: '分步表单页' },
      },
    ],
  },
  {
    path: '/detail',
    name: 'detail',
    component: Layout,
    redirect: '/detail/base',
    meta: { title: '详情页', icon: DetailIcon },
    children: [
      {
        path: 'base',
        name: 'DetailBase',
        component: () => import('@/pages/detail/base/index.vue'),
        meta: { title: '基础详情页' },
      },
      {
        path: 'advanced',
        name: 'DetailAdvanced',
        component: () => import('@/pages/detail/advanced/index.vue'),
        meta: { title: '多卡片详情页' },
      },
      {
        path: 'deploy',
        name: 'DetailDeploy',
        component: () => import('@/pages/detail/deploy/index.vue'),
        meta: { title: '数据详情页' },
      },
      {
        path: 'secondary',
        name: 'DetailSecondary',
        component: () => import('@/pages/detail/secondary/index.vue'),
        meta: { title: '二级详情页' },
      },
    ],
  },
  {
    path: '/system',
    name: 'system',
    component: Layout,
    redirect: '/system/user',
    meta: { title: '系统管理', icon: 'chart-bubble' },
    children: [
      {
        path: 'user',
        name: 'SystemUser',
        component: () => import('@/pages/system/user/index.vue'),
        meta: { title: '用户管理' },
      },
      {
        path: 'role',
        name: 'SystemRole',
        component: () => import('@/pages/system/role/index.vue'),
        meta: { title: '角色管理' },
      },
      {
        path: 'menu',
        name: 'SystemMenu',
        component: () => import('@/pages/system/menu/index.vue'),
        meta: { title: '菜单管理' },
      },
      {
        path: 'department',
        name: 'SystemDepartment',
        component: () => import('@/pages/system/department/index.vue'),
        meta: { title: '部门管理' },
      },
    ],
  },
  {
    path: '/monitoring',
    name: 'monitoring',
    component: Layout,
    redirect: '/monitoring/task',
    meta: { title: '系统监控', icon: 'server' },
    children: [
      {
        path: 'task',
        name: 'MonitoringTask',
        component: () => import('@/pages/monitoring/task/index.vue'),
        meta: { title: '定时任务' },
      },
      {
        path: 'data',
        name: 'MonitoringData',
        component: () => import('@/pages/monitoring/data/index.vue'),
        meta: { title: '数据监控' },
      },
      {
        path: 'serve',
        name: 'MonitoringServe',
        component: () => import('@/pages/monitoring/serve/index.vue'),
        meta: { title: '服务监控' },
      },
    ],
  },
  {
    path: '/log',
    name: 'log',
    component: Layout,
    redirect: '/log/operates',
    meta: { title: '日志管理', icon: LogIcon },
    children: [
      {
        path: 'operates',
        name: 'LogOperates',
        component: () => import('@/pages/log/operates/index.vue'),
        meta: { title: '操作日志' },
      },
      {
        path: 'logins',
        name: 'LogLogins',
        component: () => import('@/pages/log/logins/index.vue'),
        meta: { title: '登录日志' },
      },
    ],
  },
  {
    path: '/result',
    name: 'result',
    component: Layout,
    redirect: '/result/success',
    meta: { title: '结果页', icon: 'check-circle', hidden: true },
    children: [
      {
        path: 'success',
        name: 'ResultSuccess',
        component: () => import('@/pages/result/success/index.vue'),
        meta: { title: '成功页' },
      },
      {
        path: 'fail',
        name: 'ResultFail',
        component: () => import('@/pages/result/fail/index.vue'),
        meta: { title: '失败页' },
      },
      {
        path: 'network-error',
        name: 'ResultNetworkError',
        component: () => import('@/pages/result/network-error/index.vue'),
        meta: { title: '网络异常' },
      },
      {
        path: '403',
        name: 'Result403',
        component: () => import('@/pages/result/403/index.vue'),
        meta: { title: '无权限' },
      },
      {
        path: '404',
        name: 'Result404',
        isAlive: false,
        component: () => import('@/pages/result/404/index.vue'),
        meta: { title: '访问页面不存在页', isAlive: false },
      },
      {
        path: '500',
        name: 'Result500',
        component: () => import('@/pages/result/500/index.vue'),
        meta: { title: '服务器出错页' },
      },
      {
        path: 'browser-incompatible',
        name: 'ResultBrowserIncompatible',
        component: () => import('@/pages/result/browser-incompatible/index.vue'),
        meta: { title: '浏览器不兼容页' },
      },
      {
        path: 'maintenance',
        name: 'ResultMaintenance',
        component: () => import('@/pages/result/maintenance/index.vue'),
        meta: { title: '系统维护页' },
      },
    ],
  },
];
