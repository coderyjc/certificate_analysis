// home.js
const Layout = () => import('@/layout/index.vue')

export default [
  {
    path: '/query',
    component: Layout,
    name: 'query',
    meta: {
      title: '数据查询',
    },
    icon: 'CircleCheck',
    children: [
      {
        path: 'written',
        name: 'written',
        component: () => import('@/views/query/written.vue'),
        meta: {
          title: '笔试信息查询',
          affix: true,
        },
      },
      {
        path: 'interview',
        name: 'interview',
        component: () => import('@/views/query/interview.vue'),
        meta: {
          title: '面试信息查询',
          affix: true,
        },
      },
      {
        path: 'identify',
        name: 'identify',
        component: () => import('@/views/query/identify.vue'),
        meta: {
          title: '认定信息查询',
          affix: true,
        },
      },
      {
        path: 'certification',
        name: 'certification',
        component: () => import('@/views/query/certification.vue'),
        meta: {
          title: '证书信息查询',
          affix: true,
        },
      },
    ],
  },
]
