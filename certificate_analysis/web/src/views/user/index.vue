<template>
  <div class="container">
    <ChangePassword :username="username" :dialogFormVisible="changePasswordDialogVisibility" @visibilityChange="changeChangePasswordDialogVisibility">
    </ChangePassword>
    <AddUser :dialogFormVisible="addUserDialogVisibility" @visibilityChange="changeAddUserDialogVisibility" >
    </AddUser>
    <pro-table ref="table" :title="用户管理" :request="getList" :columns="columns" :search="searchConfig"
      @selectionChange="handleSelectionChange" :pagination="paginationConfig" @getModel="getSearchModal">

      <!-- 工具栏 -->
      <template #toolbar>
        <el-popconfirm title="确定删除选中数据吗?" @confirm="batchDelete">
          <template #reference>
            <el-button type="danger" icon="Delete">
              {{ $t('table.batchDelete') }}
            </el-button>
          </template>
        </el-popconfirm>
        <el-button type="primary" icon="Plus" @click="changeAddUserDialogVisibility">
          {{ $t('table.add') }}
        </el-button>
        <el-button type="primary" icon="Refresh" @click="refresh">
          {{ $t('table.refresh') }}
        </el-button>
      </template>

      <template #createDate="{ row }">
          {{ dateFormat(row.createDate) }}
      </template>

      <template #operate="{ row }">
        <el-popconfirm title="确定删除这条数据吗?" @confirm="handleDeleteUser(row.id)">
          <template #reference>
            <el-button size="small" type="danger">
              {{ $t('public.delete') }}
            </el-button>
          </template>
        </el-popconfirm>
        <el-button size="small" type="primary" @click="changeChangePasswordDialogVisibility(row.username)">
          {{ $t('public.changepwd') }}
        </el-button>
      </template>
    </pro-table>
  </div>
</template>

<script>
import { defineComponent, reactive, ref, toRefs } from 'vue'
import { ElMessage } from 'element-plus'
import { listUser, deleteUser } from '@/api/user'

import ChangePassword from './functional/ChangePassword.vue'
import AddUser from './functional/AddUser.vue'


import { dateFormatYMDHMS } from '@/utils/timeUtil'
export default defineComponent({
  name: 'userManagement',
  components: {
    ChangePassword,
    AddUser
  },
  setup() {
    const state = reactive({
      // 字段配置
      columns: [
        { type: 'selection', width: 56 },
        {
          label: 'login.username',
          prop: 'username',
          width: 200,
        },
        {
          label: 'login.createDate',
          prop: 'createDate',
          tdSlot: 'createDate',
          minWidth: 270,
        },
        {
          label: 'public.operate',
          width: 200,
          align: 'center',
          fixed: 'right',
          tdSlot: 'operate', // 自定义单元格内容的插槽名称
        },
      ],
      // 分页配置
      paginationConfig: {
        layout: 'total, prev, pager, next, sizes', // 分页组件显示哪些功能
        pageSize: 30, // 每页条数
        pageSizes: [30, 50, 100],
        style: { 'justify-content': 'flex-end' },
      },
      // 多项选择
      selectedItems: [],
      // 选择
      handleSelectionChange(arr) {
        state.selectedItems = arr
      },
      // 请求函数
      async getList(params) {

        // params是从组件接收的，包含分页和搜索字段。
        const { data } = await listUser(params)

        // 必须要返回一个对象，包含data数组和total总数
        return {
          data: data.data.records,
          total: data.data.total,
        }
      },
      searchModal: {},
      getSearchModal: (model) => {
        state.searchModal = model
      },
      dateFormat: (date) => {
        return dateFormatYMDHMS(date)
      }
    })

    const table = ref(null)


    const refresh = () => {
      table.value.refresh()
    }

    // 删除单个用户
    const handleDeleteUser = async (id) => {
      const { data } = await deleteUser(id)
      ElMessage({
        message: data.data,
        type: 'success',
      })
      refresh()
    }

    // 删除多个用户
    const batchDelete = () => {
      state.selectedItems.forEach(item => {
        deleteUser(item.id)
      })
      ElMessage({
        message: '批量删除成功',
        type: 'success',
      })
      refresh()
    }

    // 修改密码
    const changePasswordDialogVisibility = ref(false)
    const username = ref("")
    const changeChangePasswordDialogVisibility = (_val) => {
      username.value = _val
      changePasswordDialogVisibility.value = !changePasswordDialogVisibility.value
    }

    // 添加用户
    const addUserDialogVisibility = ref(false)
    const changeAddUserDialogVisibility = () => {
      addUserDialogVisibility.value = !addUserDialogVisibility.value
    }

    return {
      ...toRefs(state),
      refresh,
      username,
      handleDeleteUser,
      batchDelete,
      changePasswordDialogVisibility, changeChangePasswordDialogVisibility,
      addUserDialogVisibility, changeAddUserDialogVisibility,
      table,
    }
  },
})
</script>
