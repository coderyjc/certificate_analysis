<template>
  <el-dialog v-model="formVisible" title="添加考生信息" :close-on-click-modal="false" center>
    <el-form :model="form" :rules="rules">
      <el-form-item label="姓名" prop="name" :label-width="formShape.labelWidth">
        <el-input v-model="form.name" autocomplete="off" />
      </el-form-item>
      <el-form-item label="准考证号" prop="examId" :label-width="formShape.labelWidth">
        <el-input v-model="form.examId" autocomplete="off" />
      </el-form-item>
      <el-form-item label="身份证号" prop="identificationId" :label-width="formShape.labelWidth">
        <el-input v-model="form.identificationId" autocomplete="off" />
      </el-form-item>
      <el-form-item label="教育学考试状态" prop="educationStatus" :label-width="formShape.labelWidth">
        <el-select v-model="form.educationStatus" placeholder="选择教育学考试状态">
          <el-option label="正常" value="正常" />
          <el-option label="缺考" value="缺考" />
          <el-option label="作弊" value="作弊" />
        </el-select>
      </el-form-item>
      <el-form-item label="教育学成绩" prop="educationScore" v-if="form.educationStatus == '正常' || form.educationStatus == ''"
        :label-width="formShape.labelWidth">
        <el-input v-model="form.educationScore" autocomplete="off" />
      </el-form-item>
      <el-form-item label="教育心理学考试状态" prop="educationPsychologyStatus" :label-width="formShape.labelWidth">
        <el-select v-model="form.educationPsychologyStatus" placeholder="选择教育心理学考试状态">
          <el-option label="正常" value="正常" />
          <el-option label="缺考" value="缺考" />
          <el-option label="作弊" value="作弊" />
        </el-select>
      </el-form-item>
      <el-form-item label="教育心理学成绩" prop="educationPsychologyScore"
        v-if="form.educationPsychologyStatus == '正常' || form.educationPsychologyStatus == ''"
        :label-width="formShape.labelWidth">
        <el-input v-model="form.educationPsychologyScore" autocomplete="off" />
      </el-form-item>
      <el-form-item label="职业道德修养和高等教育法规状态" prop="professionalEthicStatus" :label-width="formShape.labelWidth">
        <el-select v-model="form.professionalEthicStatus" placeholder="选择职业道德修养和高等教育法规状态">
          <el-option label="正常" value="正常" />
          <el-option label="缺考" value="缺考" />
          <el-option label="作弊" value="作弊" />
        </el-select>
      </el-form-item>
      <el-form-item label="职业道德修养和高等教育法规成绩" prop="professionalEthicScore"
        v-if="form.professionalEthicStatus == '正常' || form.professionalEthicStatus == ''"
        :label-width="formShape.labelWidth">
        <el-input v-model="form.professionalEthicScore" autocomplete="off" />
      </el-form-item>
      <el-form-item label="工作地点" prop="workAddress" :label-width="formShape.labelWidth">
        <el-input v-model="form.workAddress" autocomplete="off" />
      </el-form-item>
      <el-form-item label="参考年份" prop="examDate" :label-width="formShape.labelWidth">
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
import { toRefs, reactive, defineComponent, computed, ref } from 'vue';
import { ElMessage } from 'element-plus';
import { addWrittenScore } from '@/api/query/written'

export default defineComponent({
  // 属性
  props: {
    dialogFormVisible: {
      type: Boolean,
      default: false
    }
  },
  setup(props, { emit }) {
    const getRules = () => ({
      name: [
        { required: true, message: '请输入姓名', trigger: 'blur', },
      ],
      examId: [
        { required: true, pattern: /\d{10}/, message: '请输入正确的准考证号（10位数字）', trigger: 'blur',},
      ],
      identificationId: [
        { required: true, message: '请输入身份证号', trigger: 'blur', },
        { pattern: /\d{15}(\d\d[0-9xX])?/, message: '请输入正确的身份证号', trigger: 'blur',},
      ],
      educationScore: [
        { 
          validator:  (rule, value, callback) => {
            if (state.form.educationStatus == '正常') {
              if(!value){
                // 没有输入成绩
                callback(new Error('请输入教育学成绩'))
              } else if(value < 0 || value > 100){
                // 成绩不在范围内
                callback(new Error('教育学成绩为0-100'))
              }
            } else {
              // 如果没输入成绩就按照0分，下同
              state.form.educationScore = 0
              callback()
            } 
          },
          trigger: 'blur'
        }
      ],
      educationPsychologyScore : [
        {
          validator:  (rule, value, callback) => {
            if (state.form.educationPsychologyStatus == '正常') {
              if(!value){
                callback(new Error('请输入教育心理学成绩'))
              } else if(value < 0 || value > 100){
                // 成绩不在范围内
                callback(new Error('教育心理学成绩为0-100'))
              }
            } else {
              state.form.educationPsychologyScore = 0
              callback()
            } 
          },
          trigger: 'blur'
        }
      ],
      professionalEthicScore: [
        { 
          validator:  (rule, value, callback) => {
            if (state.form.professionalEthicStatus == '正常') {
              if(!value){
                callback(new Error('请输入职业道德修养和高等教育法规成绩'))
              } else if (value < 0 || value > 60){
                 // 成绩不在范围内
                 callback(new Error('职业道德修养和高等教育法规成绩成绩为0-60'))
              }
            } else {
              state.form.professionalEthicScore = 0
              callback()
            } 
          },
          trigger: 'blur'
        }
      ],
      educationStatus: [
        { required: true, message: '请输入教育学考试状态', trigger: 'blur', },
      ],
      educationPsychologyStatus: [
        { required: true, message: '请输入教育心理学考试状态', trigger: 'blur', },
      ],
      professionalEthicStatus: [
        { required: true, message: '选择职业道德修养和高等教育法规状态', trigger: 'blur', },
      ],
      workAddress: [
        { required: true, message: '工作单位', trigger: 'blur', },
      ],
      examDate: [
        { required: true, message: '请输入考试时间', trigger: 'blur', },
      ]
    })

    const state = reactive({
      form: {
        name: "",
        examId: "",
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
      rules: getRules(),
      formShape: {
        labelWidth: '250px',
      },
      clearForm() {
        state.form.name = ""
        state.form.examId = ""
        state.form.identificationId = ""
        state.form.educationScore = ""
        state.form.educationPsychologyScore = ""
        state.form.professionalEthicScore = ""
        state.form.educationStatus = ""
        state.form.educationPsychologyStatus = ""
        state.form.professionalEthicStatus = ""
        state.form.workAddress = ""
        state.form.examDate = ""
      },
      handleVisibilityChange() {
        emit('visibilityChange')
      },
      async submit() {
        const { data } = await addWrittenScore(state.form)
        if (data.data) {
          ElMessage({
            message: data.data,
            type: 'success',
          })
          state.clearForm()
        } else {
          ElMessage({
            message: data.data || "添加失败",
            type: 'error',
          })
        }
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
