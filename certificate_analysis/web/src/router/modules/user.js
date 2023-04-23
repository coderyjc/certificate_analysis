const Layout = () => import('@/layout/index.vue')

export default [
  {
    path: '/user',
    component: Layout,
    name: 'user',
    meta: {
      title: '用户管理',
    },
    icon: 'User',
    children: [
      {
        path: '',
        name: 'userManagement',
        component: () => import('@/views/user/index.vue'),
        meta: {
          title: '用户管理',
          affix: true,
        },
      },
    ]
  },
]
