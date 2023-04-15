<template>
  <el-card>
    <div class="container">
      <div class="avatar">
        <el-avatar :size="100" src="http://blog.evilemperor.top/upload/2022/08/256.ico" />
      </div>
      <div class="text">
        <p>早上好，admin</p>
        <p>今天是2023年4月15日</p>
      </div>
    </div>
  </el-card>
</template>

<script>
export default {

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