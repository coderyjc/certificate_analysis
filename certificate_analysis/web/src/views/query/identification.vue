<template>
  <div class="container">
    <AddIdentification :dialogFormVisible="addIdentificationDialogVisibility"
      @visibilityChange="changeAddIdentificationDialogVisibility">
    </AddIdentification>
    <ExportIdentification :dialogVisible="exportIdentificationDialogVisibility"
      @visibilityChange="changeExportIdentificationDialogVisibility" :selectedItems="selectedItems"
      :searchModel="searchModal">
    </ExportIdentification>
    <ImportIdentification :dialogVisible="importIdentificationDialogVisibility"
      @visibilityChange="changeImportIdentificationDialogVisibility">
    </ImportIdentification>
    <UpdateIdentification :formVisible="updateIdentificationDialogVisibility"
      @visibilityChange="changeUpdateIdentificationDialogVisibility" :formData="updateFormData">
    </UpdateIdentification>


    <pro-table ref="table" :title="$t('query/identification.title')" :request="getList" :columns="columns"
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
        <el-button type="primary" icon="Plus" @click="changeAddIdentificationDialogVisibility">
          {{ $t('table.add') }}
        </el-button>
        <el-button type="primary" icon="Refresh" @click="refresh">
          {{ $t('table.refresh') }}
        </el-button>
        <el-button type="primary" icon="Upload" @click="changeImportIdentificationDialogVisibility">
          {{ $t('table.import') }}
        </el-button>
        <el-button type="primary" icon="Download" @click="changeExportIdentificationDialogVisibility">
          {{ $t('table.export') }}
        </el-button>
      </template>

      <template #gender="{ row }">
        {{ row.gender == 1 ? "男" : "女" }}
      </template>

      <template #operate="{ row }">
        <el-popconfirm title="确定删除这条数据吗?" @confirm="deleteIdentificationButton(row.id)">
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
import { listIdentification, deleteIdentification } from '@/api/query/identification'

import AddIdentification from './functional/AddIdentification.vue'
import ExportIdentification from './functional/ExportIdentification.vue'
import ImportIdentification from './functional/ImportIdentification.vue'
import UpdateIdentification from './functional/UpdateIdentification.vue'

export default defineComponent({
  name: 'IdentificationManagement',
  components: {
    AddIdentification,
    ExportIdentification,
    ImportIdentification,
    UpdateIdentification,
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
          width: 60,
          tdSlot: 'gender'
        },
        {
          label: 'query/identification.majorType',
          prop: 'majorType',
          minWidth: 100,
        },
        {
          label: 'query/identification.graduationSchool',
          prop: 'graduationSchool',
          minWidth: 200,
        },
        {
          label: 'query/identification.major',
          prop: 'major',
          minWidth: 180,
        },
        {
          label: 'query/identification.qualificationType',
          prop: 'qualificationType',
          width: 100,
        },
        {
          label: 'query/identification.identificationId',
          prop: 'identificationId',
          width: 200,
        },
        {
          label: 'query/identification.highestDegree',
          prop: 'highestDegree',
          width: 100,
        },
        {
          label: 'query/identification.highestEducationBackground',
          prop: 'highestEducationBackground',
          width: 100,
        },
        {
          label: 'query/identification.affirmBatch',
          prop: 'affirmBatch',
          width: 120,
        },
        {
          label: 'query/identification.confirmAddress',
          prop: 'confirmAddress',
          width: 150,
        },
        {
          label: 'query/identification.affirmInstitution',
          prop: 'affirmInstitution',
          width: 150,
        },
        {
          label: 'query/identification.examType',
          prop: 'examType',
          width: 100,
        },
        {
          label: 'query/identification.organizationType',
          prop: 'organizationType',
          width: 100,
        },
        {
          label: 'query/identification.subject',
          prop: 'subject',
          width: 150,
        },
        {
          label: 'query/identification.certificationId',
          prop: 'certificationId',
          width: 200,
        },
        {
          label: 'query/identification.city',
          prop: 'city',
          width: 120,
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
            label: 'query/identification.name',
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
            label: 'query/identification.majorType',
            name: 'majorType',
            defaultValue: "",
          },
          {
            type: 'text',
            label: 'query/identification.graduationSchool',
            name: 'graduationSchool',
            defaultValue: "",
          },
          {
            type: 'text',
            label: 'query/identification.major',
            name: 'major',
            defaultValue: "",
          },
          {
            type: 'text',
            label: 'query/identification.highestEducationBackground',
            name: 'highestEducationBackground',
            defaultValue: "",
          },
          {
            type: 'text',
            label: 'query/identification.qualificationType',
            name: 'qualificationType',
            defaultValue: "",
          },
          {
            type: 'text',
            label: 'query/identification.identificationId',
            name: 'identificationId',
            defaultValue: "",
          },
          {
            type: 'text',
            label: 'query/identification.highestDegree',
            name: 'highestDegree',
            defaultValue: "",
          },
          {
            type: 'text',
            label: 'query/identification.affirmBatch',
            name: 'affirmBatch',
            defaultValue: "",
          },
          {
            type: 'text',
            label: 'query/identification.confirmAddress',
            name: 'confirmAddress',
            defaultValue: "",
          },
          {
            type: 'text',
            label: 'query/identification.affirmInstitution',
            name: 'affirmInstitution',
            defaultValue: "",
          },
          {
            type: 'text',
            label: 'query/identification.examType',
            name: 'examType',
            defaultValue: "",
          },
          {
            type: 'text',
            label: 'query/identification.organizationType',
            name: 'organizationType',
            defaultValue: "",
          },
          {
            label: 'query/identification.subject',
            name: 'subject',
            type: 'text',
            defaultValue: '',
          },
          {
            label: 'query/identification.certificationId',
            name: 'certificationId',
            type: 'text',
            defaultValue: '',
          },
          {
            label: 'query/identification.city',
            name: 'city',
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
        const { data } = await listIdentification(params)

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

    // 删除单个认定信息
    const deleteIdentificationButton = async (id) => {
      const { data } = await deleteIdentification(id)
      ElMessage({
        message: data.data,
        type: 'success',
      })
      refresh()
    }

    // 删除多个认定信息
    const batchDelete = () => {
      state.selectedItems.forEach(item => {
        deleteIdentification(item.id)
      })
      ElMessage({
        message: '批量删除成功',
        type: 'success',
      })
      refresh()
    }

    // 添加成绩
    const addIdentificationDialogVisibility = ref(false)
    const changeAddIdentificationDialogVisibility = () => {
      addIdentificationDialogVisibility.value = !addIdentificationDialogVisibility.value
    }

    // 导出成绩
    const exportIdentificationDialogVisibility = ref(false)
    const changeExportIdentificationDialogVisibility = () => {
      exportIdentificationDialogVisibility.value = !exportIdentificationDialogVisibility.value
    }

    // 导入成绩
    const importIdentificationDialogVisibility = ref(false)
    const changeImportIdentificationDialogVisibility = () => {
      importIdentificationDialogVisibility.value = !importIdentificationDialogVisibility.value
    }

    // 修改成绩
    const updateIdentificationDialogVisibility = ref(false)
    const changeUpdateIdentificationDialogVisibility = () => {
      updateIdentificationDialogVisibility.value = !updateIdentificationDialogVisibility.value
    }
    const updateScore = data => {
      state.updateFormData = data
      changeUpdateIdentificationDialogVisibility()
    }

    return {
      ...toRefs(state),
      refresh,
      deleteIdentificationButton,
      batchDelete,
      addIdentificationDialogVisibility, changeAddIdentificationDialogVisibility,
      exportIdentificationDialogVisibility, changeExportIdentificationDialogVisibility,
      importIdentificationDialogVisibility, changeImportIdentificationDialogVisibility,
      updateScore, updateIdentificationDialogVisibility, changeUpdateIdentificationDialogVisibility,
      table,
    }
  },
})
</script>
