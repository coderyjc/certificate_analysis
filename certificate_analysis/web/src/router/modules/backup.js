const Layout = () => import('@/layout/index.vue')

export default [
  {
    path: '/backup',
    component: Layout,
    name: 'backup',
    meta: {
      title: '数据备份和恢复',
    },
    icon: 'FolderChecked',
    children: [
      {
        path: '',
        name: 'backupManagement',
        component: () => import('@/views/backup/index.vue'),
        meta: {
          title: '数据备份和恢复',
          affix: true,
        },
      },
    ]
  },
]
