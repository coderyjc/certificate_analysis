<template>
  <el-dialog v-model="formVisible" title="添加考生面试成绩" :close-on-click-modal="false" center>
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
        <el-input v-model="form.interviewYear" autocomplete="off" />
      </el-form-item>
      <el-form-item label="专业" prop="major" :label-width="formShape.labelWidth">
        <el-input v-model="form.major" autocomplete="off" />
      </el-form-item>
      <el-form-item label="有效期" prop="validateDate" :label-width="formShape.labelWidth">
        <el-input v-model="form.validateDate" autocomplete="off" />
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

import { addCertification } from '@/api/query/certification';

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
        { required: true, message: '请输入等级', trigger: 'blur', },
      ],
      identificationId: [
        { required: true, message: '请输入身份证号', trigger: 'blur', },
        { pattern: /\d{15}(\d\d[0-9xX])?/, message: '请输入正确的身份证号', trigger: 'blur',},
      ],
    })

    const state = reactive({
      form: {
        name: "",
        identificationId: "",
        qualificationId: "",
        interviewYear: "",
        major: "",
        validateDate: "",
      },
      rules: getRules(),
      formShape: {
        labelWidth: '250px',
      },
      clearForm() {
        state.form.name = ""
        state.form.identificationId = ""
        state.form.qualificationId = ""
        state.form.interviewYear = ""
        state.form.major = ""
        state.form.validateDate = "" 
      },
      handleVisibilityChange() {
        emit('visibilityChange')
      },
      async submit() {
        const { data } = await addCertification(state.form)
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
