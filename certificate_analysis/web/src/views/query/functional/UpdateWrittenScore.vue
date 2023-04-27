<template>
  <el-dialog v-model="dialogFormVisible" title="修改考生信息" :close-on-click-modal="false" center>
    <el-form :model="form">
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
        <el-button type="primary" @click="submit">修改</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>

import { updateWrittenScore } from '@/api/query/written'
import { ElMessage } from 'element-plus'


export default {
  props: {
    formData: {
      type: Object
    },
    formVisible: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      formShape: {
        labelWidth: '250px',
      },
      form: {},
      id: null,
    }
  },
  computed: {
    dialogFormVisible() {
      return this.formVisible
    }
  },
  watch: {
    formVisible(newVal, oldVal) {
      if (newVal == false) {
        this.reset()
      }
      if (newVal == true) {
        this.id = this.formData.id
        this.form.name = this.formData.name
        this.form.examId = this.formData.examId
        this.form.identificationId = this.formData.identificationId
        this.form.educationScore = this.formData.educationScore
        this.form.educationPsychologyScore = this.formData.educationPsychologyScore
        this.form.professionalEthicScore = this.formData.professionalEthicScore
        this.form.educationStatus = this.formData.educationStatus
        this.form.educationPsychologyStatus = this.formData.educationPsychologyStatus
        this.form.professionalEthicStatus = this.formData.professionalEthicStatus
        this.form.workAddress = this.formData.workAddress
        this.form.examDate = this.formData.examDate
      }
    }
  },
  methods: {
    handleVisibilityChange() {
      this.$emit('visibilityChange')
    },
    submit() {
      updateWrittenScore({
        id: this.id,
        ...this.form,
      }).then(res => {
        ElMessage({
          message: res.data.data,
          type: 'success'
        })
      })
      this.reset()
      this.$emit('visibilityChange')
    },
    reset() {
      this.form.name = ""
      this.form.examId = ""
      this.form.identificationId = ""
      this.form.educationScore = ""
      this.form.educationPsychologyScore = ""
      this.form.professionalEthicScore = ""
      this.form.educationStatus = ""
      this.form.educationPsychologyStatus = ""
      this.form.professionalEthicStatus = ""
      this.form.workAddress = ""
      this.form.examDate = ""
    }
  }
}
</script>

<style scoped></style>