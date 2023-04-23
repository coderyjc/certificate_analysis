<template>
  <el-dialog v-model="formVisible" title="修改及格分" :close-on-click-modal="false" center>
    <el-form :model="form" :rules="rules">
      <el-form-item label="时间" prop="year" :label-width="formShape.labelWidth">
        <el-input v-model="form.year" autocomplete="off" />
      </el-form-item>
      <el-form-item label="教育学及格分" prop="educationPassScore" :label-width="formShape.labelWidth">
        <el-input v-model="form.educationPassScore" autocomplete="off" />
      </el-form-item>
      <el-form-item label="心理学及格分" prop="psychologyPassScore" :label-width="formShape.labelWidth">
        <el-input v-model="form.psychologyPassScore" autocomplete="off" />
      </el-form-item>
      <el-form-item label="思想道德修养及格分" prop="ethicPassScore" :label-width="formShape.labelWidth">
        <el-input v-model="form.ethicPassScore" autocomplete="off" />
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

import { updateWrittenStatistic } from '@/api/query/writtenStatistic'

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
      year: [
        { required: true, message: '请输入年份', trigger: 'blur', },
      ],
      educationPassScore: [
        { required: true, message: '请输入及格分', trigger: 'blur', },
      ],
      psychologyPassScore: [
        { required: true, message: '请输入及格分', trigger: 'blur', },
      ],
      ethicPassScore: [
        { required: true, message: '请输入及格分', trigger: 'blur', },
      ],
    })

    const state = reactive({
      form: {
        year: '',
        educationPassScore: '',
        psychologyPassScore: '',
        ethicPassScore: '',
      },
      rules: getRules(),
      formShape: {
        labelWidth: '250px',
      },
      clearForm() {
        state.form.year = ""
        state.form.educationPassScore = ""
        state.form.psychologyPassScore = ""
        state.form.ethicPassScore = ""
      },
      handleVisibilityChange() {
        emit('visibilityChange')
      },
      async submit() {
        const { data } = await updateWrittenStatistic(state.form)
        if (data.data) {
          ElMessage({
            message: data.data ? "成功" : "失败",
            type: 'success',
          })
          state.clearForm()
          emit('visibilityChange')
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
