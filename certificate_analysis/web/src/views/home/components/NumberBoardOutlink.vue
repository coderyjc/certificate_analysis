<template>
  <div class="box" @click="handleClick">
    <div class="img">
      <Link :style="iconStyle" />
    </div>
    <div class="info">
      <router-link to="/dashboard" target="_blank" class="title">查看数据大屏</router-link>
    </div>
  </div>
</template>

<script>

export default{
  data(){
    return {
      iconStyle:"width: 60px; height: 60px; margin-right: 8px"

    }
  },
  methods:{
    handleClick(){
      this.$router.push('/dashboard')
    }
  }
}

</script>

<style lang="scss" scoped>
.box{
  background-color: #fff;
  border-radius: 5px;
  margin-bottom: 10px;
  height: 100px;
  width: 300px;
  box-shadow: $globalShadow;
  display: flex;
  cursor: pointer;

  .img{
    height: 100px;
    width: 100px;
    line-height: 100px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    background-color: rgb(132, 178, 241);
    color: #fff;
    border-radius: 5px;
  }
  
  .info{
    display: flex;
    justify-content: center;
    align-items: center;

    .title{
      margin-left: 10px;
      width: 100%;
      font-size: 20px;
      font-weight: 500;
    }

  }
}



</style>