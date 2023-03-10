<template>
  <div class="container">
    <!-- 多饼图 -->
    <multi-pie-basic class="multi-pie"></multi-pie-basic>
    <!-- 圆环图 -->
    <circle-ring class="ring"></circle-ring>
    <!-- 河北地图 -->
    <hebei-map class="hebei"></hebei-map>
  </div>
</template>

<script>
import MultiPieBasic from './components/MultiPieBasic.vue'
import CircleRing from './components/CircleRing.vue'
import HebeiMap from './components/HebeiMap.vue'

export default {
  components: {
    MultiPieBasic: MultiPieBasic,
    CircleRing: CircleRing,
    HebeiMap: HebeiMap
  }
}
</script>

<style lang="scss" scoped>

.container {
  padding: 0;
  margin: 0;
  height: 100%;
  width: 100%;

  background-color: $bigScreenBackgroundColor;
  
  display: grid;
  grid-template-columns: repeat(16, 1fr);
  grid-template-rows: repeat(9, 1fr);

  .hebei{
    grid-column-start: 2;
    grid-column-end: 9;
    grid-row-start: 1;
    grid-row-end: 10;

    margin-top: 40px;
  }

  .ring{
    grid-column-start: 9;
    grid-column-end: 17;
    grid-row-start: 5;
    grid-row-end: 10;
  }

  .multi-pie{
    grid-column-start: 9;
    grid-column-end: 17;
    grid-row-start: 2;
    grid-row-end: 5;

    z-index: 10;
  }

}


</style>