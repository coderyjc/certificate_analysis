<template>
  <div class="container">
    <AddInterviewScore :dialogFormVisible="addInterviewScoreDialogVisibility"
      @visibilityChange="changeaddInterviewScoreDialogVisibility">
    </AddInterviewScore>
    <ExportInterviewScore :dialogVisible="exportInterviewScoreDialogVisibility"
      @visibilityChange="changeexportInterviewScoreDialogVisibility" :selectedItems="selectedItems"
      :searchModel="searchModal">
    </ExportInterviewScore>
    <ImportInterviewScore :dialogVisible="importInterviewScoreDialogVisibility"
      @visibilityChange="changeimportInterviewScoreDialogVisibility">
    </ImportInterviewScore>
    <UpdateInterviewScore :formVisible="updateInterviewScoreDialogVisibility"
      @visibilityChange="changeUpdateInterviewScoreDialogVisibility" :formData="updateFormData">
    </UpdateInterviewScore>

    <pro-table ref="table" :title="$t('query/interview.title')" :request="getList" :columns="columns"
      :search="searchConfig" @selectionChange="handleSelectionChange" :pagination="paginationConfig"
      @getModel="getSearchModal">
      <!-- 工具栏 -->
      <template #toolbar>
        <el-popconfirm title="确定删除选中数据吗?" @confirm="batchDelete">
          <template #reference>
            <el-button type="danger" icon="Delete">
              {{ $t('table.batchDelete') }}
            </el-button>
          </template>
        </el-popconfirm>
        <el-button type="primary" icon="Plus" @click="changeaddInterviewScoreDialogVisibility">
          {{ $t('table.add') }}
        </el-button>
        <el-button type="primary" icon="Refresh" @click="refresh">
          {{ $t('table.refresh') }}
        </el-button>
        <el-button type="primary" icon="Upload" @click="changeimportInterviewScoreDialogVisibility">
          {{ $t('table.import') }}
        </el-button>
        <el-button type="primary" icon="Download" @click="changeexportInterviewScoreDialogVisibility">
          {{ $t('table.export') }}
        </el-button>
      </template>

      <template #gender="{ row }">
        {{ row.gender == 1 ? "男" : "女" }}
      </template>

      <template #operate="{ row }">
        <el-popconfirm title="确定删除这条数据吗?" @confirm="deleteScore(row.id)">
          <template #reference>
            <el-button size="small" type="danger">
              {{ $t('public.delete') }}
            </el-button>
          </template>
        </el-popconfirm>
        <el-button size="small" type="primary" @click="updateScore(row)">
          {{ $t('public.edit') }}
        </el-button>
      </template>
    </pro-table>
  </div>
</template>

<script>
import { defineComponent, reactive, ref, toRefs } from 'vue'
import { ElMessage } from 'element-plus'
import { listInterviewScore, deleteInterviewScore, listInterviewYear } from '@/api/query/interview'

import AddInterviewScore from './functional/AddInterviewScore.vue'
import ExportInterviewScore from './functional/ExportInterviewScore.vue'
import ImportInterviewScore from './functional/ImportInterviewScore.vue'
import UpdateInterviewScore from './functional/UpdateInterviewScore.vue'

export default defineComponent({
  name: 'interviewScoreManagement',
  components: {
    AddInterviewScore,
    ExportInterviewScore,
    ImportInterviewScore,
    UpdateInterviewScore
  },
  setup() {

    const state = reactive({
      // 字段配置
      columns: [
        { type: 'selection', width: 56 },
        {
          label: 'query/interview.name',
          prop: 'name',
          width: 80,
        },
        {
          label: 'public.gender',
          prop: 'gender',
          width: 80,
          tdSlot: 'gender'
        },
        {
          label: 'query/interview.identificationId',
          prop: 'identificationId',
          minWidth: 220,
        },
        {
          label: 'query/interview.examAddress',
          prop: 'examAddress',
          width: 150,
        },
        {
          label: 'query/interview.workAddress',
          prop: 'workAddress',
          minWidth: 220,
        },
        {
          label: 'query/interview.applyMajor',
          prop: 'applyMajor',
          minWidth: 270,
        },
        {
          label: 'query/interview.level',
          prop: 'level',
          sortable: true,
          width: 100,
        },
        {
          label: 'query/interview.examDate',
          prop: 'examDate',
          sortable: true,
          width: 100,
        },
        {
          label: 'public.operate',
          width: 150,
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
            type: 'text',
            label: 'query/interview.name',
            name: 'name',
            defaultValue: ""
          },
          {
            label: 'public.gender',
            name: 'gender',
            type: 'radio',
            defaultValue: "",
            options: [
              { name: 'public.male', value: 1, },
              { name: 'public.female', value: 0, },
            ],
          },
          {
            type: 'text',
            label: 'query/interview.identificationId',
            name: 'identificationId',
            defaultValue: "",
          },
          {
            type: 'text',
            label: 'query/interview.workAddress',
            name: 'workAddress',
            defaultValue: "",
          },
          {
            type: 'text',
            label: 'query/interview.examAddress',
            name: 'examAddress',
            defaultValue: "",
          },
          {
            type: 'select',
            label: 'query/interview.level',
            name: 'level',
            defaultValue: "",
            options: [
              { name: 'A', value: 'A', },
              { name: 'B', value: 'B', },
              { name: 'C', value: 'C', },
              { name: 'D', value: 'D', },
              { name: '缺考', value: '缺考', },
            ]
          },
          {
            label: 'query/interview.examDate',
            name: 'examDate',
            type: 'year',
            defaultValue: '',
          },
          {
            label: 'query/interview.applyMajor',
            name: 'applyMajor',
            type: 'text',
            defaultValue: '',
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
        const { data } = await listInterviewScore(params)

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
      updateFormData: {}
    })

    const table = ref(null)

    const refresh = () => {
      table.value.refresh()
    }

    // 删除单个成绩
    const deleteScore = async (id) => {
      const { data } = await deleteInterviewScore(id)
      ElMessage({
        message: data.data,
        type: 'success',
      })
      refresh()
    }

    // 删除多个成绩
    const batchDelete = () => {
      state.selectedItems.forEach(item => {
        deleteInterviewScore(item.id)
      })
      ElMessage({
        message: '批量删除成功',
        type: 'success',
      })
      refresh()
    }

    // 添加成绩
    const addInterviewScoreDialogVisibility = ref(false)
    const changeaddInterviewScoreDialogVisibility = () => {
      addInterviewScoreDialogVisibility.value = !addInterviewScoreDialogVisibility.value
    }

    // 导出成绩
    const exportInterviewScoreDialogVisibility = ref(false)
    const changeexportInterviewScoreDialogVisibility = () => {
      exportInterviewScoreDialogVisibility.value = !exportInterviewScoreDialogVisibility.value
    }

    // 导入成绩
    const importInterviewScoreDialogVisibility = ref(false)
    const changeimportInterviewScoreDialogVisibility = () => {
      importInterviewScoreDialogVisibility.value = !importInterviewScoreDialogVisibility.value
    }

    // 修改成绩
    const updateInterviewScoreDialogVisibility = ref(false)
    const changeUpdateInterviewScoreDialogVisibility = () => {
      updateInterviewScoreDialogVisibility.value = !updateInterviewScoreDialogVisibility.value
    }
    const updateScore = data => {
      state.updateFormData = data
      changeUpdateInterviewScoreDialogVisibility()
    }

    return {
      ...toRefs(state),
      refresh,
      deleteScore,
      batchDelete,
      addInterviewScoreDialogVisibility, changeaddInterviewScoreDialogVisibility,
      exportInterviewScoreDialogVisibility, changeexportInterviewScoreDialogVisibility,
      importInterviewScoreDialogVisibility, changeimportInterviewScoreDialogVisibility,
      updateScore, updateInterviewScoreDialogVisibility, changeUpdateInterviewScoreDialogVisibility,
      table,
    }
  },
})
</script>
