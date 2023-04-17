<template>
  <div class="container">
    
    <div class="container-top">
      <WelcomeBoard></WelcomeBoard>
    </div>
    <div class="container-bottom">
      <div class="container-bottom-number">
        <NumberBoard></NumberBoard>
      </div>
      <div class="container-bottom-chart">
        <LinechartBoard ></LinechartBoard>
      </div>
    </div>

  </div>
</template>

<script>

import WelcomeBoard from "./components/WelcomeBoard.vue";
import NumberBoard from "./components/NumberBoard.vue";
import LinechartBoard from "./components/LinechartBoard.vue"

export default {
  components:{
    WelcomeBoard,
    NumberBoard,
    LinechartBoard,
  }
};
</script>

<style lang="scss" scoped>
.container {
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;

  &-top{
    padding: 20px;
  }

  &-bottom{
    padding: 0 20px;
    display: flex;
  }

}
</style>