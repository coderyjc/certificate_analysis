<template>
  <el-dialog v-model="visible" title="导入数据" :close-on-click-modal="false">
    <el-upload class="upload-demo" drag :action="action_url" multiple>
      <el-icon class="el-icon--upload"><upload-filled /></el-icon>
      <div class="el-upload__text">
        拖动文件到此处或者 <em>点击以上传</em>
      </div>
      <template #tip>
        <div class="el-upload__tip">
          请上传xls或者xlsx文件，文件大小不要超过50MB。请确保文件至少包含以下列：姓名, 性别, 测试点, 身份证号, 工作单位, 申请专业, 等级, 考试时间
        </div>
      </template>
    </el-upload>
    <template late #footer>
      <span class="dialog-footer">
        <el-button @click="handleVisibilityChange">取消</el-button>
        <el-button type="primary" @click="exportSelectedData">导入文件</el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script>
import { toRefs, reactive, ref, defineComponent, computed } from 'vue';

export default defineComponent({
  // 属性
  props: {
    dialogVisible: {
      type: Boolean,
      default: false,
    },
  },
  setup(props, { emit }) {

    const state = reactive({
      handleVisibilityChange() {
        emit('visibilityChange')
      }
    })

    const visible = computed({
      get: () => {
        return props.dialogVisible
      }
    })

    const action_url = ref(import.meta.env.VITE_BASE_URL + '/certification/importExcel')

    return {
      ...toRefs(state),
      action_url,
      visible,
    }
  }
})

</script>
<style scoped>
.title {
  text-align: center;
  font-weight: 600;
}

.el-checkbox {
  margin: 10px;
}
</style>
