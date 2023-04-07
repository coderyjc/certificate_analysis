<template>
  <div class="container">
    <AddWrittenScore :dialogFormVisible="addWrittenScoreDialogVisibility" @visibilityChange="changeAddWrittenScoreDialogVisibility">
    </AddWrittenScore>
    <ExportWrittenScore :dialogVisible="exportWrittenScoreDialogVisibility" @visibilityChange="changeExportWrittenScoreDialogVisibility"
    :selectedItems="selectedItems"
    >
    </ExportWrittenScore>
    <pro-table ref="table" :title="$t('query/written.title')" :request="getList" :columns="columns" :search="searchConfig"
      @selectionChange="handleSelectionChange" :pagination="paginationConfig">
      <!-- 工具栏 -->
      <template #toolbar>
        <el-popconfirm title="确定删除选中数据吗?" @confirm="batchDelete">
          <template #reference>
            <el-button type="danger" icon="Delete">
              {{ $t('table.batchDelete') }}
            </el-button>
          </template>
        </el-popconfirm>
        <el-button type="primary" icon="Plus" @click="changeAddWrittenScoreDialogVisibility">
          {{ $t('table.add') }}
        </el-button>
        <el-button type="primary" icon="Refresh" @click="refresh">
          {{ $t('table.refresh') }}
        </el-button>
        <el-button type="primary" icon="Upload" @click="importFile">
          {{ $t('table.import') }}
        </el-button>
        <el-button type="primary" icon="Download" @click="changeExportWrittenScoreDialogVisibility">
          {{ $t('table.export') }}
        </el-button>
      </template>

      <template #educationStatus="{ row }">
        <el-tag :type="row.educationStatus === '正常' ? 'success' : 'error'">
          {{ row.educationStatus }}
        </el-tag>
      </template>

      <template #educationPsychologyStatus="{ row }">
        <el-tag :type="row.educationPsychologyStatus === '正常' ? 'success' : 'error'">
          {{ row.educationPsychologyStatus }}
        </el-tag>
      </template>

      <template #professionalEthicStatus="{ row }">
        <el-tag :type="row.professionalEthicStatus === '正常' ? 'success' : 'error'">
          {{ row.professionalEthicStatus }}
        </el-tag>
      </template>

      <template #operate="{ row }">
        <el-popconfirm title="确定删除这条数据吗?" @confirm="deleteScore(row.id)">
          <template #reference>
            <el-button size="small" type="danger">
              {{ $t('public.delete') }}
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
import { listWrittenScore, deleteWrittenScore } from '@/api/query/written'

import AddWrittenScore from './functional/AddWrittenScore.vue'
import ExportWrittenScore from './functional/ExportWrittenScore.vue'

export default defineComponent({
  name: 'writtenScoreManagement',
  components: {
    AddWrittenScore,
    ExportWrittenScore
  },
  setup() {

    const state = reactive({
      // 字段配置
      columns: [
        { type: 'selection', width: 56 },
        {
          label: 'query/written.name',
          prop: 'name',
          width: 80,
        },
        {
          label: 'query/written.examId',
          prop: 'examId',
          sortable: true,
          minWidth: 120,
        },
        {
          label: 'query/written.identificationId',
          prop: 'identificationId',
          minWidth: 170,
        },
        {
          label: 'query/written.educationScore',
          prop: 'educationScore',
          sortable: true,
          minWidth: 100,
        },
        {
          label: 'query/written.educationPsychologyScore',
          prop: 'educationPsychologyScore',
          sortable: true,
          minWidth: 130,
        },
        {
          label: 'query/written.professionalEthicScore',
          prop: 'professionalEthicScore',
          sortable: true,
          minWidth: 120,
        },
        {
          label: 'query/written.educationStatus',
          tdSlot: 'educationStatus',
          width: 90,
        },
        {
          label: 'query/written.educationPsychologyStatus',
          tdSlot: 'educationPsychologyStatus',
          width: 100,
        },
        {
          label: 'query/written.professionalEthicStatus',
          tdSlot: 'professionalEthicStatus',
          width: 100,
        },
        {
          label: 'query/written.workAddress',
          prop: 'workAddress',
          width: 120,
        },
        {
          label: 'query/written.examDate',
          prop: 'examDate',
          sortable: true,
          width: 100,
        },
        {
          label: 'public.operate',
          width: 100,
          align: 'center',
          fixed: 'right',
          tdSlot: 'operate', // 自定义单元格内容的插槽名称
        },
      ],
      // 搜索配置
      searchConfig: {
        labelWidth: '230px', // 必须带上单位
        inputWidth: '200px', // 必须带上单位
        fields: [
          {
            type: 'text',
            label: 'query/written.name',
            name: 'name',
            defaultValue: ""
          },
          {
            label: 'public.gender',
            name: 'gender',
            type: 'radio',
            defaultValue: "",
            options: [
              {
                name: 'public.male',
                value: 1,
              },
              {
                name: 'public.female',
                value: 0,
              },
            ],
          },
          {
            type: 'text',
            label: 'query/written.examId',
            name: 'examId',
            defaultValue: "",
          },
          {
            type: 'text',
            label: 'query/written.identificationId',
            name: 'identificationId',
            defaultValue: "",
          },
          {
            type: 'text',
            label: 'query/written.workAddress',
            name: 'workAddress',
            defaultValue: "",
          },
          {
            label: 'query/written.examDate',
            name: 'examDate',
            type: 'text',
            defaultValue: '',
          },
          {
            label: 'query/written.educationStatus',
            name: 'educationStatus',
            type: 'select',
            defaultValue: '',
            options: [
              {
                name: 'query/written.attend',
                value: '正常',
              },
              {
                name: 'query/written.miss',
                value: '缺考',
              },
              {
                name: 'query/written.cheat',
                value: '作弊',
              },
            ],
          },
          {
            label: 'query/written.educationPsychologyStatus',
            name: 'educationPsychologyStatus',
            type: 'select',
            defaultValue: '',
            options: [
              {
                name: 'query/written.attend',
                value: '正常',
              },
              {
                name: 'query/written.miss',
                value: '缺考',
              },
              {
                name: 'query/written.cheat',
                value: '作弊',
              },
            ],
          },
          {
            label: 'query/written.professionalEthicStatus',
            name: 'professionalEthicStatus',
            type: 'select',
            defaultValue: '',
            options: [
              {
                name: 'query/written.attend',
                value: '正常',
              },
              {
                name: 'query/written.miss',
                value: '缺考',
              },
              {
                name: 'query/written.cheat',
                value: '作弊',
              },
            ],
          },
          {
            label: 'query/written.educationSort',
            name: 'educationSort',
            type: 'radio',
            defaultValue: "",
            options: [
              {
                name: 'public.asc',
                value: 1,
              },
              {
                name: 'public.desc',
                value: 0,
              },
            ],
          },
          {
            label: 'query/written.educationPsychologySort',
            name: 'educationPsychologySort',
            type: 'radio',
            defaultValue: "",
            options: [
              {
                name: 'public.asc',
                value: 1,
              },
              {
                name: 'public.desc',
                value: 0,
              },
            ],
          },
          {
            label: 'query/written.professionalEthicSort',
            name: 'professionalEthicSort',
            type: 'radio',
            defaultValue: "",
            options: [
              {
                name: 'public.asc',
                value: 1,
              },
              {
                name: 'public.desc',
                value: 0,
              },
            ],
          },
        ],
      },
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
        const { data } = await listWrittenScore(params)

        // 必须要返回一个对象，包含data数组和total总数
        return {
          data: data.data.records,
          total: data.data.total,
        }
      },
    })

    const table = ref(null)

    const refresh = () => {
      table.value.refresh()
    }

    // 删除单个成绩
    const deleteScore = async (id) => {
      const { data } = await deleteWrittenScore(id)
      ElMessage({
        message: data.data,
        type: 'success',
      })
      refresh()
    }

    // 删除多个成绩
    const batchDelete = () => {
      state.selectedItems.forEach(item => {
        deleteWrittenScore(item.id)
      })
      ElMessage({
        message: '批量删除成功',
        type: 'success',
      })
      refresh()
    }

    // 添加成绩
    const addWrittenScoreDialogVisibility = ref(false)
    const changeAddWrittenScoreDialogVisibility = () => {
      addWrittenScoreDialogVisibility.value = !addWrittenScoreDialogVisibility.value
    }

    // 导出成绩
    const exportWrittenScoreDialogVisibility = ref(false)
    const changeExportWrittenScoreDialogVisibility = () => {
      exportWrittenScoreDialogVisibility.value = !exportWrittenScoreDialogVisibility.value
    }

    // 导入文件
    const importFile = () => {
      console.log("this is import file");
    }

    return {
      ...toRefs(state),
      refresh,
      importFile,
      deleteScore,
      batchDelete,
      addWrittenScoreDialogVisibility, 
      changeAddWrittenScoreDialogVisibility,
      exportWrittenScoreDialogVisibility, 
      changeExportWrittenScoreDialogVisibility,
      table,
    }
  },
})
</script>
