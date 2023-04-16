<template>
  <div class="board-item-container">
    <LinechartBoardItemIdentification id="identification-trend" :title="'参加认定人数变化趋势'"></LinechartBoardItemIdentification>
    <LinechartBoardItemWrittenScore id="written-trend" :title="'笔试人数变化趋势'"></LinechartBoardItemWrittenScore>
    <LinechartBoardItemInterviewScore id="interview-trend" :title="'面试人数变化趋势'"></LinechartBoardItemInterviewScore>
    <LinechartBoardItemCertification id="certification-trend" :title="'获得证书人数变化趋势'"></LinechartBoardItemCertification>
  </div>
</template>

<script>

import LinechartBoardItemCertification from './LinechartBoardItem/LinechartBoardItemCertification.vue';
import LinechartBoardItemIdentification from './LinechartBoardItem/LinechartBoardItemIdentification.vue';
import LinechartBoardItemInterviewScore from './LinechartBoardItem/LinechartBoardItemInterviewScore.vue';
import LinechartBoardItemWrittenScore from './LinechartBoardItem/LinechartBoardItemWrittenScore.vue';

export default {
  components: {
    LinechartBoardItemCertification,
    LinechartBoardItemIdentification,
    LinechartBoardItemInterviewScore,
    LinechartBoardItemWrittenScore,
  },
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