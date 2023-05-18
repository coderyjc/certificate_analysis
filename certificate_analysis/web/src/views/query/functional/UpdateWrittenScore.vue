<template>
  <el-dialog v-model="dialogFormVisible" title="修改考生信息" :close-on-click-modal="false" center>
    <el-form :rules="rules" :model="form">
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
          <el-option label="没有报名考试" value="没有报名考试" />
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
          <el-option label="没有报名考试" value="没有报名考试" />
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
          <el-option label="没有报名考试" value="没有报名考试" />
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
        <el-date-picker v-model="form.examDate" type="year" placeholder="选择年份" />
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
      rules: {
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur', },
        ],
        examId: [
          { required: true, pattern: /\d{10}/, message: '请输入正确的准考证号（10位数字）', trigger: 'blur', },
        ],
        identificationId: [
          { required: true, message: '请输入身份证号', trigger: 'blur', },
          { pattern: /^[1-9]\d{5}(18|19|20)\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/, message: '请输入正确的身份证号', trigger: 'blur', },
        ],
        educationScore: [
          {
            validator: (rule, value, callback) => {
              if (this.form.educationStatus == '正常') {
                if (!value) {
                  // 没有输入成绩
                  callback(new Error('请输入教育学成绩'))
                } else if (value < 0 || value > 100) {
                  // 成绩不在范围内
                  callback(new Error('教育学成绩为0-100'))
                }
              } else {
                // 如果没输入成绩就按照0分，下同
                this.form.educationScore = 0
                callback()
              }
            },
            trigger: 'blur'
          }
        ],
        educationPsychologyScore: [
          {
            validator: (rule, value, callback) => {
              if (this.form.educationPsychologyStatus == '正常') {
                if (!value) {
                  callback(new Error('请输入教育心理学成绩'))
                } else if (value < 0 || value > 100) {
                  // 成绩不在范围内
                  callback(new Error('教育心理学成绩为0-100'))
                }
              } else {
                this.form.educationPsychologyScore = 0
                callback()
              }
            },
            trigger: 'blur'
          }
        ],
        professionalEthicScore: [
          {
            validator: (rule, value, callback) => {
              if (this.form.professionalEthicStatus == '正常') {
                if (!value) {
                  callback(new Error('请输入职业道德修养和高等教育法规成绩'))
                } else if (value < 0 || value > 60) {
                  // 成绩不在范围内
                  callback(new Error('职业道德修养和高等教育法规成绩成绩为0-60'))
                }
              } else {
                this.form.professionalEthicScore = 0
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

      // 考试状态为缺考或者作弊的时候分数为0,没有报名考试分数为-1
      if (this.form.educationStatus == '缺考' || this.form.educationStatus == '作弊') this.form.educationScore = 0
      if (this.form.educationPsychologyStatus == '缺考' || this.form.educationPsychologyStatus == '作弊') this.form.educationPsychologyScore = 0
      if (this.form.professionalEthicStatus == '缺考' || this.form.professionalEthicStatus == '作弊') this.form.professionalEthicScore = 0
      if (this.form.educationStatus == '没有报名考试') {
        this.form.educationScore = -1
        this.form.educationStatus = '正常'
      }
      if (this.form.educationPsychologyStatus == '没有报名考试') {
        this.form.educationPsychologyScore = -1
        this.form.educationPsychologyStatus = '正常'
      }
      if (this.form.professionalEthicStatus == '没有报名考试') {
        this.form.professionalEthicScore = -1
        this.form.professionalEthicStatus = '正常'
      }

      var s = new Date(this.form.examDate)
      this.form.examDate = String(s.getFullYear())

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