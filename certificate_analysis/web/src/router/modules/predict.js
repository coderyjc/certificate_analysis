// home.js
const Layout = () => import('@/layout/index.vue')

export default [
  {
    path: '/predict',
    component: Layout,
    name: 'predict',
    meta: {
      title: '数据预测',
    },
    icon: 'CircleCheck',
    children: [
      {
        path: 'datapredict',
        name: 'predict',
        component: () => import('@/views/predict/index.vue'),
        meta: {
          title: '数据预测',
          affix: true,
        },
      },
    ],
  },
]
