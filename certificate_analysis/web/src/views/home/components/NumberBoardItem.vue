<template>
  <div class="box">
    <div class="img">
      <CircleCheck v-if="type=='identification'" :style="iconStyle" />
      <Edit v-if="type=='written'" :style="iconStyle" />
      <Postcard v-if="type=='certification'" :style="iconStyle" />
      <UserFilled v-if="type=='interview'" :style="iconStyle" />
    </div>
    <div class="info">
      <div class="title">{{ title }}</div>
      <div class="number">{{ num }}</div>
    </div>
  </div>
</template>

<script>

import { toRefs, reactive, defineComponent, computed, ref } from 'vue';

export default defineComponent({
  props: {
    type: {
      type: String
    },
    title: {
      type: String,
      default: ""
    },
    num:{
      type: Number,
      default: 0
    },
    icon: {
      type: String,
    },
  },
  setup(props, { emit }) {
    


    const iconStyle = ref("width: 60px; height: 60px; margin-right: 8px")

    return {
      iconStyle
    }
  }
})

</script>

<style lang="scss" scoped>
.box{
  background-color: #fff;
  border-radius: 5px;
  margin-bottom: 10px;
  height: 100px;
  width: 300px;

  display: flex;

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
    margin: 10px;

    .title{
      margin-top: 5px;
      width: 100%;
      font-size: 20px;
      font-weight: 500;
    }
    
    .number{
      width: 100%;
      font-size: 30px;
      font-weight: 550;
      margin: 10px 0;
    }

  }
}



</style>