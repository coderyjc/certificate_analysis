// home.js
const Layout = () => import('@/layout/index.vue')

export default [
  {
    path: '/visualize',
    component: Layout,
    name: 'visualize',
    meta: {
      title: '数据可视化分析',
    },
    icon: 'PieChart',
    children: [
      {
        path: 'identification',
        name: 'identification',
        component: () => import('@/views/visualize/identification.vue'),
        meta: {
          title: '认定信息可视化分析',
          affix: true,
        },
      },
      {
        path: 'certification',
        name: 'certification',
        component: () => import('@/views/visualize/certification.vue'),
        meta: {
          title: '证书可视化分析',
          affix: true,
        },
      },
      {
        path: 'writtenScore',
        name: 'writtenScore',
        component: () => import('@/views/visualize/writtenScore.vue'),
        meta: {
          title: '笔试成绩可视化分析',
          affix: true,
        },
      },
      {
        path: 'interviewScore',
        name: 'interviewScore',
        component: () => import('@/views/visualize/interviewScore.vue'),
        meta: {
          title: '面试成绩可视化分析',
          affix: true,
        },
      },
    ],
  },
]
