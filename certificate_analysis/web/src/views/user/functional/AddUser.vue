<template>
  <el-dialog v-model="formVisible" title="添加用户" :close-on-click-modal="false" center>
    <el-form ref="myForm" :model="form" :rules="rules">
      <el-form-item label="用户名" prop="username" :label-width="formShape.labelWidth">
        <el-input v-model="form.username" autocomplete="off" />
      </el-form-item>
      <el-form-item label="密码" prop="password" :label-width="formShape.labelWidth">
        <el-input type="password" v-model="form.password" autocomplete="off" />
      </el-form-item>
      <el-form-item label="确认密码" prop="confirmPassword" :label-width="formShape.labelWidth">
        <el-input type="password" v-model="form.confirmPassword" autocomplete="off" />
      </el-form-item>

    </el-form>
    <template late #footer>
      <span class="dialog-footer">
        <el-button @click="handleVisibilityChange">取消</el-button>
        <el-button type="primary" @click="submit">添加用户</el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script>
import { toRefs, reactive, defineComponent, computed, ref, unref } from 'vue';
import { ElMessage } from 'element-plus';

import { checkRepeat, addUser } from '@/api/user'

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
      username: [
        { required: true, message: '请输入用户名', trigger: 'blur', },
        {
          validator: async (rule, value, callback) => {
            const { data } = await checkRepeat(value)
            if (data.data) {
              callback(new Error('用户名已被注册'))
            }
          },
          trigger: 'blur'
        }
      ],
      password: [
        { required: true, message: '请输入密码', trigger: 'blur', },
        {
          validator: (rule, value, callback) => {
            if (state.form.password.length < 6 || state.form.password.length > 16) {
              callback(new Error('密码为6到16位'))
            }
          },
          trigger: 'blur'
        }
      ],
      confirmPassword: [
        { required: true, message: '请确认密码', trigger: 'blur', },
        {
          validator: (rule, value, callback) => {
            if (state.form.password.length <= 0) {
              callback(new Error('请输入密码'))
            }
            else if (value.length <= 0) {
              callback(new Error('请再次输入密码'))
            } else if (value != state.form.password) {
              callback(new Error('两次密码输入不一致'))
            }
          },
          trigger: 'blur'
        }
      ],
    })

    const state = reactive({
      form: {
        username: "",
        password: "",
        confirmPassword: "",
      },
      rules: getRules(),
      formShape: {
        labelWidth: '250px',
      },
      clearForm() {
        state.form.username = ""
        state.form.password = ""
        state.form.confirmPassword = ""
      },
      handleVisibilityChange() {
        emit('visibilityChange')
      },
      async submit() {
        if(state.form.username == '' || state.form.password == '' || state.form.confirmPassword == ''){
          ElMessage({
            message: '请填写完整',
            type: 'warning'
          })
          return
        }

        const { data } = await addUser(state.form)
        if (data.data) {
          ElMessage({
            message: data.data,
            type: 'success',
          })
          state.clearForm()
        } else {
          ElMessage({
            message: data.data || "修改失败",
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

    const myForm = ref(null)

    return {
      ...toRefs(state),
      formVisible,
      myForm
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
