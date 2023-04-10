<template>
  <el-dialog v-model="formVisible" title="添加认定信息" :close-on-click-modal="false" center>
    <el-form :model="form" :rules="rules">
      <el-form-item label="姓名" prop="name" :label-width="formShape.labelWidth">
        <el-input v-model="form.name" autocomplete="off" />
      </el-form-item>
      <el-form-item label="专业类别" prop="majorType" :label-width="formShape.labelWidth">
        <el-input v-model="form.majorType" autocomplete="off" />
      </el-form-item>
      <el-form-item label="毕业学校" prop="graduationSchool" :label-width="formShape.labelWidth">
        <el-input v-model="form.graduationSchool" autocomplete="off" />
      </el-form-item>
      <el-form-item label="所学专业" prop="major" :label-width="formShape.labelWidth">
        <el-input v-model="form.major" autocomplete="off" />
      </el-form-item>
      <el-form-item label="最高学历" prop="highestEducationBackground" :label-width="formShape.labelWidth">
        <el-input v-model="form.highestEducationBackground" autocomplete="off" />
      </el-form-item>
      <el-form-item label="资格种类" prop="qualificationType" :label-width="formShape.labelWidth">
        <el-input v-model="form.qualificationType" autocomplete="off" />
      </el-form-item>
      <el-form-item label="证件号码" prop="identificationId" :label-width="formShape.labelWidth">
        <el-input v-model="form.identificationId" autocomplete="off" />
      </el-form-item>
      <el-form-item label="最高学位" prop="highestDegree" :label-width="formShape.labelWidth">
        <el-input v-model="form.highestDegree" autocomplete="off" />
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
        <el-input v-model="form.examType" autocomplete="off" />
      </el-form-item>
      <el-form-item label="机构类型" prop="organizationType" :label-width="formShape.labelWidth">
        <el-input v-model="form.organizationType" autocomplete="off" />
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
        <el-button type="primary" @click="submit">添加</el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script>
import { toRefs, reactive, defineComponent, computed } from 'vue';
import { ElMessage } from 'element-plus';

import { addIdentification } from '@/api/query/identification';

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
    })

    const state = reactive({
      form: {
        name: "",
        majorType: "",
        graduationSchool: "",
        major: "",
        highestEducationBackground: "",
        qualificationType: "",
        identificationId: "",
        highestDegree: "",
        affirmBatch: "",
        confirmAddress: "",
        affirmInstitution: "",
        examType: "",
        organizationType: "",
        subject: "",
        certificationId: "",
        city: "",
      },
      rules: getRules(),
      formShape: {
        labelWidth: '250px',
      },
      clearForm() {
        state.form.name = ""
        state.form.majorType = ""
        state.form.graduationSchool = ""
        state.form.major = ""
        state.form.highestEducationBackground = ""
        state.form.qualificationType = ""
        state.form.identificationId = ""
        state.form.highestDegree = ""
        state.form.affirmBatch = ""
        state.form.confirmAddress = ""
        state.form.affirmInstitution = ""
        state.form.examType = ""
        state.form.organizationType = ""
        state.form.subject = ""
        state.form.certificationId = ""
        state.form.city = ""
      },
      handleVisibilityChange() {
        emit('visibilityChange')
      },
      async submit() {
        const { data } = await addIdentification(state.form)
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
