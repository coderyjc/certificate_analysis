<template>
  <div class="container">

    <el-dialog v-model="dialogFormVisible" title="添加备份">
      <el-form :model="addForm">
        <el-form-item label="备份数据" label-width="100px">
          <el-select v-model="addForm.tableName" placeholder="选择备份信息">
            <el-option label="面试信息" value="tbl_interview_score" />
            <el-option label="笔试信息" value="tbl_written_score" />
            <el-option label="认定信息" value="tbl_identification" />
            <el-option label="证书信息" value="tbl_certification" />
          </el-select>
        </el-form-item>
        <el-form-item label="备注" label-width="100px">
          <el-input v-model="addForm.description" autocomplete="off" />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取消</el-button>
          <el-button type="primary" @click="handleAddBackup">
            添加备份
          </el-button>
        </span>
      </template>
    </el-dialog>

    <pro-table ref="table" title="数据备份" :request="getList" :columns="columns" :search="searchConfig"
      @selectionChange="handleSelectionChange" :pagination="paginationConfig" @getModel="getSearchModal">
      <!-- 工具栏 -->
      <template #toolbar>
        <el-popconfirm title="确定删除选中数据吗?" @confirm="handleBatchDelete">
          <template #reference>
            <el-button type="danger" icon="Delete">
              {{ $t('table.batchDelete') }}
            </el-button>
          </template>
        </el-popconfirm>
        <el-button type="primary" icon="Plus" @click="handleDialogVisibleChange">
          {{ $t('backup.add') }}
        </el-button>
        <el-button type="primary" icon="Refresh" @click="refresh">
          {{ $t('table.refresh') }}
        </el-button>
      </template>

      <template #tableName="{ row }">
        {{ tableMap_T[row.tableName] }}
      </template>
      <template #backupDate="{ row }">
        {{ dateFormatYMDHMS(row.backupDate) }}
      </template>

      <template #operate="{ row }">
        <el-popconfirm title="确定删除这条数据吗?" @confirm="handleDeleteBackup(row.id)">
          <template #reference>
            <el-button size="small" type="danger">
              {{ $t('public.delete') }}
            </el-button>
          </template>
        </el-popconfirm>
        <el-popconfirm title="恢复记录会覆盖以前的数据，建议恢复之前备份一次数据，确定恢复这条记录吗?" @confirm="handleRecover(row.id)">
          <template #reference>
            <el-button size="small" type="danger">
              {{ $t('backup.recover') }}
            </el-button>
          </template>
        </el-popconfirm>
      </template>
    </pro-table>
  </div>
</template>

<script>
import { defineComponent, reactive, ref, toRefs } from 'vue'
import { ElMessage } from 'element-plus'

import { listBackupAll, addBackup, deleteBackup, recoverBackup } from '@/api/query/backup'

import { dateFormatYMDHMS } from '@/utils/timeUtil.js'

export default defineComponent({
  name: 'backupManagement',
  components: {
  },
  setup() {
    const state = reactive({
      // 字段配置
      columns: [
        { type: 'selection', width: 56 },
        {
          label: 'backup.tableName',
          prop: 'tableName',
          width: 180,
          tdSlot: 'tableName'
        },
        {
          label: 'backup.backupDate',
          prop: 'backupDate',
          width: 220,
          tdSlot: 'backupDate'
        },
        {
          label: 'backup.description',
          prop: 'description',
          width: 220,
        },
        {
          label: 'public.operate',
          width: 200,
          align: 'center',
          fixed: 'right',
          tdSlot: 'operate', // 自定义单元格内容的插槽名称
        },
      ],
      // 搜索配置
      searchConfig: {
        labelWidth: '100px', // 必须带上单位
        inputWidth: '200px', // 必须带上单位
        fields: [
          {
            type: 'select',
            label: 'backup.tableName',
            name: 'name',
            defaultValue: "",
            options: [
              { value: '面试信息' },
              { value: '笔试信息' },
              { value: '认定信息' },
              { value: '证书信息' },
            ]
          },
        ],
      },
      // 分页配置
      paginationConfig: {
        layout: 'total, prev, pager, next, sizes', // 分页组件显示哪些功能
        pageSize: 10, // 每页条数
        pageSizes: [5, 10, 20, 30],
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
        const tableMap = {
          '面试信息': "tbl_interview_score",
          '笔试信息': "tbl_written_score",
          '认定信息': "tbl_identification",
          '证书信息': "tbl_certification",
        }
        params.name = tableMap[params.name]

        // params是从组件接收的，包含分页和搜索字段。
        const { data } = await listBackupAll(params)

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
    })

    const tableMap_T = reactive({
      "tbl_interview_score": '面试信息',
      "tbl_written_score": '笔试信息',
      "tbl_identification": '认定信息',
      "tbl_certification": '证书信息',
    })

    const table = ref(null)

    const refresh = () => {
      table.value.refresh()
    }

    const dialogFormVisible = ref(false)
  
    const handleDialogVisibleChange = () => {
      dialogFormVisible.value = !dialogFormVisible.value
    }

    const addForm = ref({
      tableName: '',
      description: ''
    })

    const handleAddBackup = () => {
      if(addForm.value.tableName == '' || addForm.value.description == ''){
        ElMessage({
          message: '请填写完整',
          type: 'warning'
        })
        return
      }
      addBackup(addForm.value.tableName, addForm.value.description).then(res => {
        console.log(res)
        ElMessage({
          message: '备份成功',
          type: 'success'
        })
        addForm.value.tableName = ''
        addForm.value.description = ''
        dialogFormVisible.value = false
        refresh()
      })
    }

    const handleRecover = (id) => {
      recoverBackup(id).then(res => {
        ElMessage({
          message: '恢复成功',
          type: 'success'
        })
      })
    }

    // 删除单个记录
    const handleDeleteBackup = async (id) => {
      const { data } = await deleteBackup(id)
      ElMessage({
        message: data.data,
        type: 'success',
      })
      refresh()
    }

    // 删除多个记录
    const handleBatchDelete = () => {
      state.selectedItems.forEach(item => {
        handleDeleteBackup(item.id)
      })
      ElMessage({
        message: '批量删除成功',
        type: 'success',
      })
      refresh()
    }

    return {
      ...toRefs(state),
      refresh,
      addForm,
      dialogFormVisible,
      handleDialogVisibleChange,
      handleAddBackup,
      handleDeleteBackup,
      dateFormatYMDHMS,
      handleBatchDelete,
      handleRecover,
      tableMap_T,
      table,
    }
  },
})
</script>
