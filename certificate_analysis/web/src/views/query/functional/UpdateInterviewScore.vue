<template>
  <el-dialog v-model="dialogFormVisible" title="修改考生信息" :close-on-click-modal="false" center>
    <el-form :model="form" :rules="rules">
      <el-form-item label="姓名" prop="name" :label-width="formShape.labelWidth">
        <el-input v-model="form.name" autocomplete="off" />
      </el-form-item>
      <el-form-item label="身份证号" prop="identificationId" :label-width="formShape.labelWidth">
        <el-input v-model="form.identificationId" autocomplete="off" />
      </el-form-item>
      <el-form-item label="工作地点" prop="workAddress" :label-width="formShape.labelWidth">
        <el-input v-model="form.workAddress" autocomplete="off" />
      </el-form-item>
      <el-form-item label="测试点" prop="examAddress" :label-width="formShape.labelWidth">
        <el-input v-model="form.examAddress" autocomplete="off" />
      </el-form-item>
      <el-form-item label="等级" prop="level" :label-width="formShape.labelWidth">
        <el-select v-model="form.level">
          <el-option v-for="item in ['A', 'B', 'C', 'D', '缺考']" :key="item" :label="item" :value="item" />
        </el-select>
      </el-form-item>
      <el-form-item label="申请专业" prop="applyMajor" :label-width="formShape.labelWidth">
        <el-input v-model="form.applyMajor" autocomplete="off" />
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

import { updateInterviewScore } from '@/api/query/interview'
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
        examAddress: [
          { required: true, message: '请输入测试点', trigger: 'blur', },
        ],
        applyMajor: [
          { required: true, message: '请输入申请专业', trigger: 'blur', },
        ],
        level: [
          { required: true, message: '请输入等级', trigger: 'blur', },
        ],
        identificationId: [
          { required: true, message: '请输入身份证号', trigger: 'blur', },
          { pattern: /\d{15}(\d\d[0-9xX])?/, message: '请输入正确的身份证号', trigger: 'blur', },
        ],
        workAddress: [
          { required: true, message: '工作单位', trigger: 'blur', },
        ],
        examDate: [
          { required: true, message: '请输入考试时间', trigger: 'blur', },
        ]
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
        this.form.workAddress = this.formData.workAddress
        this.form.examAddress = this.formData.examAddress
        this.form.level = this.formData.level
        this.form.applyMajor = this.formData.applyMajor
        this.form.examDate = this.formData.examDate
      }
    }
  },
  methods: {
    handleVisibilityChange() {
      this.$emit('visibilityChange')
    },
    submit() {
      updateInterviewScore({
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
      this.form.workAddress = ""
      this.form.examAddress = ""
      this.form.level = ""
      this.form.applyMajor = ""
      this.form.examDate = ""
    }
  }
}
</script>

<style scoped></style>