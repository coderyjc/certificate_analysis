// home.js
const Layout = () => import('@/layout/index.vue')

export default [
  {
    path: '/visualization',
    component: Layout,
    name: 'visualization',
    meta: {
      title: '数据可视化',
    },
    icon: 'CircleCheck',
    children: [
      {
        path: 'infomation',
        name: 'infomation',
        component: () => import('@/views/visualization/infomation.vue'),
        meta: {
          title: '认证信息可视化',
          affix: true,
        },
      },
      {
        path: 'educate',
        name: 'educate',
        component: () => import('@/views/visualization/educate.vue'),
        meta: {
          title: '教育背景可视化',
          affix: true,
        },
      },
      {
        path: 'score',
        name: 'score',
        component: () => import('@/views/visualization/score.vue'),
        meta: {
          title: '成绩可视化',
          affix: true,
        },
      },
    ],
  },
]
