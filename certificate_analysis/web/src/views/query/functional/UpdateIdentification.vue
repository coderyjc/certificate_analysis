<template>
  <el-dialog v-model="dialogFormVisible" title="修改考生信息" :close-on-click-modal="false" center>
    <el-form :model="form" :rules="rules">
      <el-form-item label="姓名" prop="name" :label-width="formShape.labelWidth">
        <el-input v-model="form.name" autocomplete="off" />
      </el-form-item>
      <el-form-item label="专业类别" prop="majorType" :label-width="formShape.labelWidth">
        <el-select v-model="form.majorType">
          <el-option v-for="item in ['师范类', '非师范类']" :key="item" :label="item" :value="item" />
        </el-select>
      </el-form-item>
      <el-form-item label="毕业学校" prop="graduationSchool" :label-width="formShape.labelWidth">
        <el-input v-model="form.graduationSchool" autocomplete="off" />
      </el-form-item>
      <el-form-item label="所学专业" prop="major" :label-width="formShape.labelWidth">
        <el-input v-model="form.major" autocomplete="off" />
      </el-form-item>
      <el-form-item label="最高学历" prop="highestEducationBackground" :label-width="formShape.labelWidth">
        <el-select v-model="form.highestEducationBackground">
          <el-option v-for="item in ['大学本科','大学专科','研究生','其他中专','幼师','中师']" :key="item" :label="item" :value="item" />
        </el-select>
      </el-form-item>
      <el-form-item label="资格种类" prop="qualificationType" :label-width="formShape.labelWidth">
        <el-select v-model="form.qualificationType">
          <el-option v-for="item in ['高校','高中','幼儿园','小学','中职','初中','中职实习']" :key="item" :label="item" :value="item" />
        </el-select>
      </el-form-item>
      <el-form-item label="证件号码" prop="identificationId" :label-width="formShape.labelWidth">
        <el-input v-model="form.identificationId" autocomplete="off" />
      </el-form-item>
      <el-form-item label="最高学位" prop="highestDegree" :label-width="formShape.labelWidth">
        <el-select v-model="form.highestDegree">
          <el-option v-for="item in ['硕士','无学位','学士','博士']" :key="item" :label="item" :value="item" />
        </el-select>
      </el-form-item>
      <el-form-item label="认定批次" prop="affirmBatch" :label-width="formShape.labelWidth">
        <el-input v-model="form.affirmBatch" autocomplete="off" />
      </el-form-item>
      <el-form-item label="确认点" prop="confirmAddress" :label-width="formShape.labelWidth">
        <el-input v-model="form.confirmAddress" autocomplete="off" />
      </el-form-item>
      <el-form-item label="认定机构" prop="affirmInstitution" :label-width="formShape.labelWidth">
        <el-input v-model="form.affirmInstitution" autocomplete="off" />
      </el-form-item>
      <el-form-item label="考试类型" prop="examType" :label-width="formShape.labelWidth">
        <el-select v-model="form.examType">
          <el-option v-for="item in ['国考', '非国考']" :key="item" :label="item" :value="item" />
        </el-select>
      </el-form-item>
      <el-form-item label="机构类型" prop="organizationType" :label-width="formShape.labelWidth">
        <el-select v-model="form.organizationType">
          <el-option v-for="item in ['省级机构', '市级机构']" :key="item" :label="item" :value="item" />
        </el-select>
      </el-form-item>
      <el-form-item label="任教学科" prop="subject" :label-width="formShape.labelWidth">
        <el-input v-model="form.subject" autocomplete="off" />
      </el-form-item>
      <el-form-item label="教师资格证书号码" prop="certificationId" :label-width="formShape.labelWidth">
        <el-input v-model="form.certificationId" autocomplete="off" />
      </el-form-item>
      <el-form-item label="市" prop="city" :label-width="formShape.labelWidth">
        <el-input v-model="form.city" autocomplete="off" />
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

import { updateIdentification } from '@/api/query/identification'
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
        majorType: [
          { required: true, message: '请输入专业类别', trigger: 'blur', },
        ],
        graduationSchool: [
          { required: true, message: '请输入毕业学校', trigger: 'blur', },
        ],
        major: [
          { required: true, message: '请输入所学专业', trigger: 'blur', },
        ],
        highestEducationBackground: [
          { required: true, message: '请输入最高学历', trigger: 'blur', },
        ],
        qualificationType: [
          { required: true, message: '请输入资格种类', trigger: 'blur', },
        ],
        identificationId: [
          { required: true, message: '请输入证件号码', trigger: 'blur', },
          { pattern: /\d{15}(\d\d[0-9xX])?/, message: '请输入正确的身份证号', trigger: 'blur', },
        ],
        highestDegree: [
          { required: true, message: '请输入最高学位', trigger: 'blur', },
        ],
        affirmBatch: [
          { required: true, message: '请输入认定批次', trigger: 'blur', },
        ],
        confirmAddress: [
          { required: true, message: '请输入确认点', trigger: 'blur', },
        ],
        affirmInstitution: [
          { required: true, message: '请输入认定机构', trigger: 'blur', },
        ],
        examType: [
          { required: true, message: '请输入考试类型', trigger: 'blur', },
        ],
        organizationType: [
          { required: true, message: '请输入机构类型', trigger: 'blur', },
        ],
        subject: [
          { required: true, message: '请输入任教学科', trigger: 'blur', },
        ],
        certificationId: [
          { required: true, message: '请输入教师资格证书号码', trigger: 'blur', },
        ],
        city: [
          { required: true, message: '请输入市', trigger: 'blur', },
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
        this.form.majorType = this.formData.majorType
        this.form.graduationSchool = this.formData.graduationSchool
        this.form.major = this.formData.major
        this.form.highestEducationBackground = this.formData.highestEducationBackground
        this.form.qualificationType = this.formData.qualificationType
        this.form.identificationId = this.formData.identificationId
        this.form.highestDegree = this.formData.highestDegree
        this.form.affirmBatch = this.formData.affirmBatch
        this.form.confirmAddress = this.formData.confirmAddress
        this.form.affirmInstitution = this.formData.affirmInstitution
        this.form.examType = this.formData.examType
        this.form.organizationType = this.formData.organizationType
        this.form.subject = this.formData.subject
        this.form.certificationId = this.formData.certificationId
        this.form.city = this.formData.city
      }
    }
  },
  methods: {
    handleVisibilityChange() {
      this.$emit('visibilityChange')
    },
    submit() {
      updateIdentification({
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
      this.form.majorType = ""
      this.form.graduationSchool = ""
      this.form.major = ""
      this.form.highestEducationBackground = ""
      this.form.qualificationType = ""
      this.form.identificationId = ""
      this.form.highestDegree = ""
      this.form.affirmBatch = ""
      this.form.confirmAddress = ""
      this.form.affirmInstitution = ""
      this.form.examType = ""
      this.form.organizationType = ""
      this.form.subject = ""
      this.form.certificationId = ""
      this.form.city = ""
    }
  }
}
</script>

<style scoped></style>