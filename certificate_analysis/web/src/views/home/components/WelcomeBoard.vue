<template>
  <el-card>
    <div class="container">
      <div class="avatar">
      </div>
      <div class="text">
        <p>今天是{{ nowTime }}</p>
      </div>
    </div>
  </el-card>
</template>

<script>
export default {
  computed:{
    nowTime(){
      var date = new Date()
      const year = date.getFullYear()
      const month = date.getMonth() + 1
      const day = date.getDate()
      const currStr = year + '年' + month + '月' + day + '日'
      return currStr
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  background-color: #fff;
  display: flex;
}

.text{
  margin: 0 30px;
  font-size: 20px;

  p{
    margin: 10px 0;
  }
}
</style>