<template>
  <div class="brand">
    <!-- <img class="logo" src="~@/assets/logo.svg" @click="goHome" /> -->
    <div class="title">教师资格证数据分析系统</div>
  </div>
</template>
<script>
import { defineComponent } from 'vue'
import { useRouter } from 'vue-router'

export default defineComponent({
  setup() {
    const router = useRouter()
    const goHome = () => {
      router.push('/')
    }
    return { goHome }
  },
})
</script>
<style lang="scss" scoped>
.brand {
  height: 48px;
  padding: 0 8px;
  display: flex;
  align-items: center;
  justify-content: center;

  .logo {
    cursor: pointer;
    max-width: 32px;
    max-height: 32px;
  }
  .title {
    color: rgb(255, 255, 255);
    font-size: 14px;
    font-weight: 700;
    white-space: nowrap;
    margin-left: 8px;
    transition: all 0.5s;
  }
}
</style>
