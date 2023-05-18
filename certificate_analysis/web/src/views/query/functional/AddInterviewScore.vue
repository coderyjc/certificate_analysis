<template>
  <el-dialog v-model="formVisible" title="添加考生面试成绩" :close-on-click-modal="false" center>
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
        <el-date-picker v-model="form.examDate" type="year" placeholder="选择年份" />
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

import { addInterviewScore } from '@/api/query/interview'

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
    })

    const state = reactive({
      form: {
        name: "",
        identificationId: "",
        workAddress: "",
        examDate: "",
        examAddress: "",
        applyMajor: "",
        level: "",
      },
      rules: getRules(),
      formShape: {
        labelWidth: '250px',
      },
      clearForm() {
        state.form.name = ""
        state.form.identificationId = ""
        state.form.workAddress = ""
        state.form.examDate = ""
        state.form.examAddress = ""
        state.form.applyMajor = ""
        state.form.level = ""
      },
      handleVisibilityChange() {
        emit('visibilityChange')
      },
      async submit() {
        var s = new Date(state.form.examDate)
        state.form.examDate = s.getFullYear()

        const { data } = await addInterviewScore(state.form)
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
