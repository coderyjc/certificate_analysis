<template>
  <div class="container">
    <AddCertification :dialogFormVisible="addCertificationDialogVisibility"
      @visibilityChange="changeaddCertificationDialogVisibility">
    </AddCertification>
    <ExportCertification :dialogVisible="exportCertificationDialogVisibility"
      @visibilityChange="changeexportCertificationDialogVisibility" :selectedItems="selectedItems"
      :searchModel="searchModal">
    </ExportCertification>
    <ImportCertification :dialogVisible="importCertificationDialogVisibility"
      @visibilityChange="changeimportCertificationDialogVisibility">
    </ImportCertification>
    <UpdateCertification :formVisible="updateCertificationDialogVisibility"
      @visibilityChange="changeUpdateCertificationDialogVisibility" :formData="updateFormData">
    </UpdateCertification>

    <pro-table ref="table" :title="$t('query/certification.title')" :request="getList" :columns="columns"
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
        <el-button type="primary" icon="Plus" @click="changeaddCertificationDialogVisibility">
          {{ $t('table.add') }}
        </el-button>
        <el-button type="primary" icon="Refresh" @click="refresh">
          {{ $t('table.refresh') }}
        </el-button>
        <el-button type="primary" icon="Upload" @click="changeimportCertificationDialogVisibility">
          {{ $t('table.import') }}
        </el-button>
        <el-button type="primary" icon="Download" @click="changeexportCertificationDialogVisibility">
          {{ $t('table.export') }}
        </el-button>
      </template>

      <template #gender="{ row }">
        {{ row.gender == 1 ? "男" : "女" }}
      </template>

      <template #validateDate="{ row }">
        {{ dateFormat(row.validateDate) }}
      </template>

      <template #operate="{ row }">
        <el-popconfirm title="确定删除这条数据吗?" @confirm="deleteCertificationButton(row.id)">
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
import { listCertification, deleteCertification, listInterviewYear, listValidateYear } from '@/api/query/certification'

import { dateFormatYMD } from '@/utils/timeUtil'

import AddCertification from './functional/AddCertification.vue'
import ExportCertification from './functional/ExportCertification.vue'
import ImportCertification from './functional/ImportCertification.vue'
import UpdateCertification from './functional/UpdateCertification.vue'

export default defineComponent({
  name: 'CertificationManagement',
  components: {
    AddCertification,
    ExportCertification,
    ImportCertification,
    UpdateCertification
  },
  setup() {

    // 获取数据
    async function listInterviewYears() {
      let years = []
      await listInterviewYear().then(res => res.data.data.forEach(e => years.push({ name: e, value: e })))
      return years
    }
    const interviewYears = []
    listInterviewYears().then(res => res.forEach(e => interviewYears.push(e)))

    // 获取数据
    async function listYears() {
      let years = []
      await listValidateYear().then(res => res.data.data.forEach(e => years.push({ name: e, value: e })))
      return years
    }
    const validateYear = []
    listYears().then(res => res.forEach(e => validateYear.push(e)))


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
          label: 'query/certification.identificationId',
          prop: 'identificationId',
          minWidth: 220,
        },
        {
          label: 'query/certification.qualificationId',
          prop: 'qualificationId',
          minWidth: 220,
        },
        {
          label: 'query/certification.major',
          prop: 'major',
          minWidth: 220,
        },
        {
          label: 'query/certification.interviewYear',
          prop: 'interviewYear',
          width: 150,
        },
        {
          label: 'query/certification.validateDate',
          prop: 'validateDate',
          tdSlot: 'validateDate',
          sortable: true,
          width: 200,
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
            label: 'query/certification.name',
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
            label: 'query/certification.identificationId',
            name: 'identificationId',
            defaultValue: "",
          },
          {
            type: 'select',
            label: 'query/certification.interviewYear',
            name: 'interviewYear',
            options: interviewYears,
            defaultValue: "",
          },
          {
            type: 'text',
            label: 'query/certification.qualificationId',
            name: 'qualificationId',
            defaultValue: "",
          },
          {
            label: 'query/certification.validateDate',
            name: 'validateDate',
            type: 'select',
            options: validateYear,
            defaultValue: '',
          },
          {
            label: 'query/certification.major',
            name: 'major',
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
        const { data } = await listCertification(params)

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
        return dateFormatYMD(date)
      },
      updateFormData: {}
    })

    const table = ref(null)

    const refresh = () => {
      table.value.refresh()
    }

    // 删除单个证书
    const deleteCertificationButton = async (id) => {
      const { data } = await deleteCertification(id)
      ElMessage({
        message: data.data,
        type: 'success',
      })
      refresh()
    }

    // 删除多个证书
    const batchDelete = () => {
      state.selectedItems.forEach(item => {
        deleteCertification(item.id)
      })
      ElMessage({
        message: '批量删除成功',
        type: 'success',
      })
      refresh()
    }

    // 添加成绩
    const addCertificationDialogVisibility = ref(false)
    const changeaddCertificationDialogVisibility = () => {
      addCertificationDialogVisibility.value = !addCertificationDialogVisibility.value
    }

    // 导出成绩
    const exportCertificationDialogVisibility = ref(false)
    const changeexportCertificationDialogVisibility = () => {
      exportCertificationDialogVisibility.value = !exportCertificationDialogVisibility.value
    }

    // 导入成绩
    const importCertificationDialogVisibility = ref(false)
    const changeimportCertificationDialogVisibility = () => {
      importCertificationDialogVisibility.value = !importCertificationDialogVisibility.value
    }

    // 修改成绩
    const updateCertificationDialogVisibility = ref(false)
    const changeUpdateCertificationDialogVisibility = () => {
      updateCertificationDialogVisibility.value = !updateCertificationDialogVisibility.value
    }
    const updateScore = data => {
      state.updateFormData = data
      changeUpdateCertificationDialogVisibility()
    }

    return {
      ...toRefs(state),
      refresh,
      deleteCertificationButton,
      batchDelete,
      addCertificationDialogVisibility, changeaddCertificationDialogVisibility,
      exportCertificationDialogVisibility, changeexportCertificationDialogVisibility,
      importCertificationDialogVisibility, changeimportCertificationDialogVisibility,
      updateScore, updateCertificationDialogVisibility, changeUpdateCertificationDialogVisibility,
      table,
    }
  },
})
</script>
