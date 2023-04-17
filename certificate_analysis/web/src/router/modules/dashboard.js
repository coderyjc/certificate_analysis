// dashboard.js
const Dashboard = () => import('@/views/dashboard/index.vue')

export default [
  {
    path: '/dashboard',
    name: 'dashboard',
    component: Dashboard,
  },
]
