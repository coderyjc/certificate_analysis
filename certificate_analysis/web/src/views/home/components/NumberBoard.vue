<template>
  <div class="container">
    <NumberBoardItem :type="'identification'" :num="223545" :title="'认定信息总数'" class="box"></NumberBoardItem>
    <NumberBoardItem :type="'written'" :num="21230" :title="'笔试成绩总数'" class="box"></NumberBoardItem>
    <NumberBoardItem :type="'interview'" :num="31200" :title="'面试成绩总数'" class="box"></NumberBoardItem>
    <NumberBoardItem :type="'certification'" :num="32400" :title="'证书信息总数'" class="box"></NumberBoardItem>
  </div>
</template>

<script>
import NumberBoardItem from './NumberBoardItem.vue'

export default {
  components: {
    NumberBoardItem
  }
}
</script>

<style lang="scss" scoped>

</style>