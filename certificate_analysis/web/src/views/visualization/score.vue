<template>
  <div class="container">
    <!-- 水球图  3门缺考率+面试缺考率-->
    <liquid-fill :id="'ball-1'" title="缺考率1" class="ball-1"></liquid-fill>
    <liquid-fill :id="'ball-2'" title="缺考率2" class="ball-2"></liquid-fill>
    <liquid-fill :id="'ball-3'" title="缺考率3" class="ball-3"></liquid-fill>
    <liquid-fill :id="'ball-4'" title="面试缺考" class="ball-4"></liquid-fill>
    <!-- 饼图 面试成绩-->
    <circle-ring class="ring"></circle-ring>
    <!-- 双向进度条 平均分，通过率 -->
    <bothway-progress-bar 
    class="bothway-progress-bar"
    :style="{width:'100%'}"></bothway-progress-bar>
  </div>
</template>

<script>
import LiquidFill from './components/LiquidFill.vue'
import BothwayProgressBar from './components/BothwayProgressBar.vue'
import CircleRing from './components/CircleRing.vue'

export default {
  components:{
    LiquidFill: LiquidFill,
    BothwayProgressBar: BothwayProgressBar,
    CircleRing: CircleRing
  }
}
</script>

<style lang="scss" scoped>

.container{
  margin: 0;
  padding: 0;
  height: 100%;
  width: 100%;

  background-color: $bigScreenBackgroundColor;
  
  display: grid;
  grid-template-columns: repeat(16, 1fr);
  grid-template-rows: repeat(9, 1fr);

  .ball-1{
    grid-column-start: 2;
    grid-column-end: 4;
    grid-row-end: 2;
    grid-row-start: 4;
  }
  .ball-2{
    grid-column-start: 4;
    grid-column-end: 6;
    grid-row-end: 2;
    grid-row-start: 4;
  }
  .ball-3{
    grid-column-start: 6;
    grid-column-end: 8;
    grid-row-end: 2;
    grid-row-start: 4;
  }
  .ball-4{
    grid-column-start: 8;
    grid-column-end: 10;
    grid-row-end: 2;
    grid-row-start: 4;
  }
  .bothway-progress-bar{
    grid-column-start: 5;
    grid-column-end: 12;
    grid-row-end: 6;
    grid-row-start: 9;
  }
  .ring{
    grid-column-start: 12;
    grid-column-end: 17;
    grid-row-end: 1;
    grid-row-start: 5;
  }

}


</style>