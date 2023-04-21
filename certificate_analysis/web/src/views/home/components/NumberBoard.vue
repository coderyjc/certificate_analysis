<template>
  <div class="container">
    <NumberBoardOutlink class="box"></NumberBoardOutlink>
    <NumberBoardItem :type="'identification'" :num="total_identification" :title="'认定信息总数'" class="box"></NumberBoardItem>
    <NumberBoardItem :type="'written'" :num="total_written" :title="'笔试成绩总数'" class="box"></NumberBoardItem>
    <NumberBoardItem :type="'interview'" :num="total_interview" :title="'面试成绩总数'" class="box"></NumberBoardItem>
    <NumberBoardItem :type="'certification'" :num="total_certification" :title="'证书信息总数'" class="box"></NumberBoardItem>
  </div>
</template>

<script>
import NumberBoardItem from './NumberBoardItem.vue'
import NumberBoardOutlink from './NumberBoardOutlink.vue'

import { getDashboardTotalData } from '@/api/dashboard'

export default {
  components: {
    NumberBoardItem,
    NumberBoardOutlink
  },
  data(){
    return {
      total_identification: "正在加载",
      total_written: "正在加载",
      total_interview: "正在加载",
      total_certification: "正在加载",
    }
  },
  created() {
    getDashboardTotalData().then(res => {
      this.total_certification = res.data.data.total_certification
      this.total_written = res.data.data.total_written
      this.total_interview = res.data.data.total_interview
      this.total_identification = res.data.data.total_identification
    })
  },
}
</script>

<style lang="scss" scoped>

</style>