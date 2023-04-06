<template>
  <el-dialog v-model="formVisible" title="添加考生信息" :close-on-click-modal="false" center>
    <el-form :model="form">
      <el-form-item label="姓名" :label-width="formShape.labelWidth">
        <el-input v-model="form.name" autocomplete="off" />
      </el-form-item>
      <el-form-item label="准考证号" :label-width="formShape.labelWidth">
        <el-input v-model="form.examId" autocomplete="off" />
      </el-form-item>
      <el-form-item label="身份证号" :label-width="formShape.labelWidth">
        <el-input v-model="form.identificationId" autocomplete="off" />
      </el-form-item>
      <el-form-item label="教育学考试状态" :label-width="formShape.labelWidth">
        <el-select v-model="form.educationStatus" placeholder="选择教育学考试状态">
          <el-option label="正常" value="正常" />
          <el-option label="缺考" value="缺考" />
          <el-option label="作弊" value="作弊" />
        </el-select>
      </el-form-item>
      <el-form-item label="教育学成绩" v-if="form.educationStatus == '正常' || form.educationStatus == '' " :label-width="formShape.labelWidth">
        <el-input v-model="form.educationScore" autocomplete="off" />
      </el-form-item>
      <el-form-item label="教育心理学考试状态" :label-width="formShape.labelWidth">
        <el-select v-model="form.educationPsychologyStatus" placeholder="选择教育心理学考试状态">
          <el-option label="正常" value="正常" />
          <el-option label="缺考" value="缺考" />
          <el-option label="作弊" value="作弊" />
        </el-select>
      </el-form-item>
      <el-form-item label="教育心理学考试状态" v-if="form.educationPsychologyStatus == '正常' || form.educationPsychologyStatus == '' " :label-width="formShape.labelWidth">
        <el-input v-model="form.educationPsychologyScore" autocomplete="off" />
      </el-form-item>
      <el-form-item label="职业道德修养和高等教育法规状态" :label-width="formShape.labelWidth">
        <el-select v-model="form.professionalEthicStatus" placeholder="选择职业道德修养和高等教育法规状态">
          <el-option label="正常" value="正常" />
          <el-option label="缺考" value="缺考" />
          <el-option label="作弊" value="作弊" />
        </el-select>
      </el-form-item>
      <el-form-item label="职业道德修养和高等教育法规成绩" v-if="form.professionalEthicStatus == '正常' || form.professionalEthicStatus == '' " :label-width="formShape.labelWidth">
        <el-input v-model="form.professionalEthicScore" autocomplete="off" />
      </el-form-item>
      <el-form-item label="工作地点" :label-width="formShape.labelWidth">
        <el-input v-model="form.workAddress" autocomplete="off" />
      </el-form-item>
      <el-form-item label="参考年份" :label-width="formShape.labelWidth">
        <el-input v-model="form.examDate" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template late #footer>
      <span class="dialog-footer">
        <el-button @click="handleVisibilityChange">取消</el-button>
        <el-button type="primary" @click="submit">添加</el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script>
import { toRefs, reactive, defineComponent, computed } from 'vue';
import { ElMessage } from 'element-plus';
import { addWrittenScore } from '@/api/query/written'



export default defineComponent({
  // 属性
  props: {
    dialogFormVisible:{
      type: Boolean,
      default: false
    }
  },
  setup(props, { emit }){

    const state = reactive({
      form:{
        name: "",
        examId: "",
        identificationId: "",
        educationScore: "",
        educationPsychologyScore: "",
        professionalEthicScore: "",
        educationStatus: "",
        educationPsychologyStatus:"",
        professionalEthicStatus:"",
        workAddress:"",
        examDate: ""
      },
      formShape: {
        labelWidth: '250px',
      },
      clearForm(){
        state.form.name = ""
        state.form.examId = ""
        state.form.identificationId = ""
        state.form.educationScore = ""
        state.form.educationPsychologyScore = ""
        state.form.professionalEthicScore = ""
        state.form.educationStatus = ""
        state.form.educationPsychologyStatus =""
        state.form.professionalEthicStatus =""
        state.form.workAddress =""
        state.form.examDate = ""
      },
      handleVisibilityChange(){
        emit('visibilityChange')
      },
      async submit(){
        const { data } = await addWrittenScore(state.form)
        if(data.data){
          ElMessage({
            message: data.data,
            type: 'success',
          })
        }else {
          ElMessage({
            message: data.data || "添加失败",
            type: 'error',
          })
        }
        state.clearForm()
      },
    })

    const formVisible = computed({
      get: () => {
        return props.dialogFormVisible
      }
    })

    return {
      ...toRefs(state),
      formVisible
    }
  }
})

</script>
<style scoped>
.el-button--text {
  margin-right: 15px;
}
.el-select {
  width: 300px;
}
.el-input {
  width: 300px;
}
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>
