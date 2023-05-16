<template>
  <el-dialog v-model="dialogFormVisible" title="修改考生信息" :close-on-click-modal="false" center>
    <el-form :model="form" :rules="rules">
      <el-form-item label="姓名" prop="name" :label-width="formShape.labelWidth">
        <el-input v-model="form.name" autocomplete="off" />
      </el-form-item>
      <el-form-item label="身份证号" prop="identificationId" :label-width="formShape.labelWidth">
        <el-input v-model="form.identificationId" autocomplete="off" />
      </el-form-item>
      <el-form-item label="合格证号" prop="qualificationId" :label-width="formShape.labelWidth">
        <el-input v-model="form.qualificationId" autocomplete="off" />
      </el-form-item>
      <el-form-item label="面试年份" prop="interviewYear" :label-width="formShape.labelWidth">
        <el-date-picker v-model="form.interviewYear" type="year" placeholder="选择面试年份" />
      </el-form-item>
      <el-form-item label="专业" prop="major" :label-width="formShape.labelWidth">
        <el-input v-model="form.major" autocomplete="off" />
      </el-form-item>
      <el-form-item label="有效期" prop="validateDate" :label-width="formShape.labelWidth">
        <el-date-picker v-model="form.validateDate" type="date" placeholder="有效期" />
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

import { updateCertification } from '@/api/query/certification'
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
      rules: {
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur', },
        ],
        interviewYear: [
          { required: true, message: '请输入面试年份', trigger: 'blur', },
        ],
        qualificationId: [
          { required: true, message: '请输入合格证号', trigger: 'blur', },
        ],
        major: [
          { required: true, message: '请输入专业', trigger: 'blur', },
        ],
        validateDate: [
          { required: true, message: '请输入有效期', trigger: 'blur', },
        ],
        identificationId: [
          { required: true, message: '请输入身份证号', trigger: 'blur', },
          { pattern: /^[1-9]\d{5}(18|19|20)\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/, message: '请输入正确的身份证号', trigger: 'blur', },
        ],
      }
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
        this.form.identificationId = this.formData.identificationId
        this.form.qualificationId = this.formData.qualificationId
        this.form.interviewYear = this.formData.interviewYear
        this.form.major = this.formData.major
        this.form.validateDate = this.formData.validateDate
      }
    }
  },
  methods: {
    handleVisibilityChange() {
      this.$emit('visibilityChange')
    },
    submit() {
      
      // 变换格式
      this.form.interviewYear = this.form.interviewYear.getFullYear()
      this.form.validateDate = this.form.validateDate.getFullYear() + '-' + (this.form.validateDate.getMonth() + 1) + '-' + this.form.validateDate.getDate()

      updateCertification({
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
      this.id = ""
      this.form.name = ""
      this.form.identificationId = ""
      this.form.qualificationId = ""
      this.form.interviewYear = ""
      this.form.major = ""
      this.form.validateDate = ""
    }
  }
}
</script>

<style scoped></style>