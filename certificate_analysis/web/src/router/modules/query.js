// home.js
const Layout = () => import('@/layout/index.vue')

export default [
  {
    path: '/query',
    component: Layout,
    name: 'query',
    meta: {
      title: '数据管理',
    },
    icon: 'Search',
    children: [
      {
        path: 'writtenManagement',
        name: 'writtenManagement',
        component: () => import('@/views/query/written.vue'),
        meta: {
          title: '笔试信息管理',
          affix: true,
        },
      },
      {
        path: 'interviewManagement',
        name: 'interviewManagement',
        component: () => import('@/views/query/interview.vue'),
        meta: {
          title: '面试信息管理',
          affix: true,
        },
      },
      {
        path: 'identificationManagement',
        name: 'identificationManagement',
        component: () => import('@/views/query/identification.vue'),
        meta: {
          title: '认定信息管理',
          affix: true,
        },
      },
      {
        path: 'certificationManagement',
        name: 'certificationManagement',
        component: () => import('@/views/query/certification.vue'),
        meta: {
          title: '证书信息管理',
          affix: true,
        },
      },
    ],
  },
]
