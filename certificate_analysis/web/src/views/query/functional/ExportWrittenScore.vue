<template>
  <el-dialog v-model="visible" title="导出数据" :close-on-click-modal="false">
    <el-form>
      <h2 class="title">选择导出列</h2>
      <el-form-item>
        <el-checkbox-group v-model="checkList">
          <el-checkbox label="姓名" border/>
          <el-checkbox label="性别" border/>
          <el-checkbox label="准考证号" border/>
          <el-checkbox label="身份证号" border/>
          <el-checkbox label="教育学成绩" border/>
          <el-checkbox label="教育心理学成绩" border/>
          <el-checkbox label="职业道德修养和高等教育法规成绩" border/>
          <el-checkbox label="教育学考试状态" border/>
          <el-checkbox label="教育心理学考试状态" border/>
          <el-checkbox label="职业道德修养和高等教育法规状态" border/>
          <el-checkbox label="工作单位" border/>
          <el-checkbox label="考试时间" border/>
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

export default defineComponent({
  // 属性
  props: {
    dialogVisible: {
      type: Boolean,
      default: false
    }
  },
  setup(props, { emit }) {
    const state = reactive({
      form: {
        name: true,
        examId: true,
        gender: true,
        identificationId: "",
        educationScore: "",
        educationPsychologyScore: "",
        professionalEthicScore: "",
        educationStatus: "",
        educationPsychologyStatus: "",
        professionalEthicStatus: "",
        workAddress: "",
        examDate: ""
      },
      checkList: ['姓名','性别','准考证号','身份证号','教育学成绩','教育心理学成绩','职业道德修养和高等教育法规成绩','教育学考试状态','教育心理学考试状态','职业道德修养和高等教育法规状态','工作单位','考试时间'],
      handleVisibilityChange() {
        emit('visibilityChange')
      },
      async exportSelectedData() {
        console.log(state.checkList)
      },
      async exportAllData() {
        console.log('submit export')
      }
    })

    const visible = computed({
      get: () => {
        return props.dialogVisible
      }
    })

    // const checkList = ref()

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
