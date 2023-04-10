<template>
  <el-dialog v-model="visible" title="导出数据" :close-on-click-modal="false">
    <el-form>
      <h2 class="title">选择导出列</h2>
      <el-form-item>
        <el-checkbox-group v-model="checkList">
          <el-checkbox label="姓名" border/>
          <el-checkbox label="性别" border/>
          <el-checkbox label="专业类别" border/>
          <el-checkbox label="毕业学校" border/>
          <el-checkbox label="所学专业" border/>
          <el-checkbox label="最高学历" border/>
          <el-checkbox label="资格种类" border/>
          <el-checkbox label="证件号码" border/>
          <el-checkbox label="最高学位" border/>
          <el-checkbox label="认定批次" border/>
          <el-checkbox label="确认点" border/>
          <el-checkbox label="认定机构" border/>
          <el-checkbox label="考试类型" border/>
          <el-checkbox label="机构类型" border/>
          <el-checkbox label="任教学科" border/>
          <el-checkbox label="教师资格证书号码" border/>
          <el-checkbox label="市" border/>
        </el-checkbox-group>
      </el-form-item>
    </el-form>
    <template late #footer>
      <span class="dialog-footer">
        <el-button @click="handleVisibilityChange">取消</el-button>
        <el-button type="primary" @click="exportSelectedData">导出选中数据</el-button>
        <el-button type="primary" @click="exportAllData">导出所有数据</el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script>
import { toRefs, reactive, defineComponent, computed, ref } from 'vue';
import { ElMessage } from 'element-plus';
import { exportIdentification } from '@/api/query/identification';

export default defineComponent({
  // 属性
  props: {
    dialogVisible: {
      type: Boolean,
      default: false,
    },
    selectedItems: {
      type: Array,
    },
    searchModel: {
      type: Object,
    }
  },
  setup(props, { emit }) {

    const state = reactive({
      checkList: ["姓名","性别","专业类别","毕业学校","所学专业","最高学历","资格种类","证件号码","最高学位","认定批次","确认点","认定机构","考试类型","机构类型","任教学科","教师资格证书号码","市"],
      handleVisibilityChange() {
        emit('visibilityChange')
      },
      async exportSelectedData() {
        // 导出选择的数据（不用查询参数）
        const { selectedItems } = toRefs(props)
        const ids = []
        selectedItems.value.forEach(item => ids.push(item.id))
        if(ids.length == 0){
          ElMessage({
            message: '请先选择或者筛选数据',
            type: 'warning',
          })
          return
        }
        const outlink = await exportIdentification(state.checkList, ids, JSON.stringify({}))
        // 通过在浏览器打开外部链接进行下载
        window.open(outlink,"_blank")
        ElMessage({
          message: '开始下载',
          type: 'success',
        })
      },
      async exportAllData() {
        const model = props.searchModel
        const outlink = await exportIdentification(state.checkList,[],JSON.stringify({...model}))
        // 通过在浏览器打开外部链接进行下载
        window.open(outlink,"_blank")
        ElMessage({
          message: '开始下载',
          type: 'success',
        })
      },
    })

    const visible = computed({
      get: () => {
        return props.dialogVisible
      }
    })

    return {
      ...toRefs(state),
      visible,
    }
  }
})

</script>
<style scoped>

.title{
  text-align: center;
  font-weight: 600;
}
.el-checkbox{
  margin: 10px;
}

</style>
