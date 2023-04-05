// home.js
const Layout = () => import('@/layout/index.vue')

export default [
  {
    path: '/analysis',
    component: Layout,
    name: 'analysis',
    meta: {
      title: '数据可视化分析',
    },
    icon: 'CircleCheck',
    children: [
      {
        path: 'identification',
        name: 'identification',
        component: () => import('@/views/analysis/identification.vue'),
        meta: {
          title: '认定信息可视化分析',
          affix: true,
        },
      },
      {
        path: 'certification',
        name: 'certification',
        component: () => import('@/views/analysis/certification.vue'),
        meta: {
          title: '证书可视化分析',
          affix: true,
        },
      },
      {
        path: 'score',
        name: 'score',
        component: () => import('@/views/analysis/score.vue'),
        meta: {
          title: '成绩可视化分析',
          affix: true,
        },
      },
    ],
  },
]
