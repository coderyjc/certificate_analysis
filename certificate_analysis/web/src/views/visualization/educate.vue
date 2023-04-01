<template>
  <div class="container">
    <!-- 考生来源地图 -->
    <candidate-source 
      class="candidate-source"
      :style="{height:'100%'}"
      ></candidate-source>

    <!-- 学位 -->
    <irregular-pyramid 
      id="degree" 
      class="degree"></irregular-pyramid>

    <!-- 专业词云图 -->
    <word-cloud
      class="word-cloud"></word-cloud>
      
  </div>
</template>

<script>
import IrregularPyramid from "./components/IrregularPyramid.vue"
import CandidateSource from './components/CandidateSource.vue'
import WordCloud from "./components/WordCloud.vue"

export default {
  components: {
    IrregularPyramid: IrregularPyramid,
    CandidateSource: CandidateSource,
    WordCloud: WordCloud
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

  .candidate-source{
    grid-column-start: 1;
    grid-column-end: 13;
    grid-row-start: 1;
    grid-row-end: 10;
  }

  .degree{
    grid-column-start: 13;
    grid-column-end: 17;
    grid-row-end: 5;
    grid-row-start: 1;
  }

  .word-cloud{
    margin-top: 20px;

    grid-column-start: 13;
    grid-column-end: 17;
    grid-row-start: 5;
    grid-row-end: 10;
  }


}

</style>