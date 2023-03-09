// home.js
const Layout = () => import('@/layout/index.vue')

export default [
  {
    path: '/identity',
    component: Layout,
    name: 'identity',
    meta: {
      title: '认证信息可视化',
    },
    icon: 'icon-home',
    children: [
      {
        path: 'infomation',
        name: 'infomation',
        component: () => import('@/views/identify/infomation.vue'),
        meta: {
          title: '认证信息可视化',
          affix: true,
        },
      },
      {
        path: 'educate',
        name: 'educate',
        component: () => import('@/views/identify/educate.vue'),
        meta: {
          title: '教育背景可视化',
          affix: true,
        },
      },
    ],
  },
]
