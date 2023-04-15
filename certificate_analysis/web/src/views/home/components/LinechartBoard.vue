<template>
  <div class="board-item-container">
    <LinechartBoardItem id="identification-trend" :name="'identification'" :title="'参加认定人数变化趋势'"></LinechartBoardItem>
    <LinechartBoardItem id="written-trend" :name="'written'" :title="'笔试人数变化趋势'"></LinechartBoardItem>
    <LinechartBoardItem id="interview-trend" :name="'interview'" :title="'面试人数变化趋势'"></LinechartBoardItem>
    <LinechartBoardItem id="certification-trend" :name="'certification'" :title="'获得证书人数变化趋势'"></LinechartBoardItem>
  </div>
</template>

<script>

import LinechartBoardItem from './LinechartBoardItem.vue'

export default {
  components: {
    LinechartBoardItem
  },
  data(){
    return {

    }
  },
  mounted(){

  },
  methods:{

  }
}

</script>

<style lang="scss" scoped>

.board-item-container {
  display: grid;
  grid-template-columns: 2;
  grid-template-rows: 2;  


  #identification-trend{
    grid-column-start: 1;
    grid-column-end: 2;
    grid-row-start: 1;
    grid-row-end: 2;
  }

  #written-trend{
    grid-column-start: 2;
    grid-column-end: 3;
    grid-row-start: 1;
    grid-row-end: 2;
  }

  #interview-trend{
    grid-column-start: 1;
    grid-column-end: 2;
    grid-row-start: 2;
    grid-row-end: 3;
  }

  #certification-trend{
    grid-column-start: 2;
    grid-column-end: 3;
    grid-row-start: 2;
    grid-row-end: 3;
  }  
}



</style>