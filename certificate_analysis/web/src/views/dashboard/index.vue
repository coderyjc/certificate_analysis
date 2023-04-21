<template>
<div class="container">
  <div class="head">
    <h1>数据展板</h1>
  </div>
  <div class="left">
    <div class="box">饼图：专业类别、性别、考试类型</div>
    <div class="box"></div>
    <div class="box"></div>
  </div>
  <div class="center">
    <div class="digit-board">
      <div class="digit">229690</div>
      <div class="info">认定信息数目</div>
    </div>
    <div class="map">
      地图
    </div>
  </div>
  <div class="right">
    <div class="box">最高学位柱状图</div>
    <div class="box">资格种类柱状图</div>
    <div class="box">所学专业词云</div>
  </div>
</div>
</template>

<script>

export default {
  components:{
  }


}
</script>

<style lang="scss" scoped>

*{
  box-sizing: border-box;
  padding: 0; margin: 0;
  text-decoration: 0;
}

@font-face{
	font-family: electronicFont;
	src:url(./assets/font/DS-DIGIT.TTF)
}

.container{
  width: 100%;
  height: 100%;

  background: url('./assets/images/bg.jpg') center center no-repeat;

  display: grid;
  grid-template-columns: 10;
  grid-template-rows: 10;
  
  color: #fff;

  .head{
    grid-column-start: 1;
    grid-column-end: 11;
    grid-row-start: 1;
    grid-row-end: 2;
    background: url(./assets/images/head_bg.png) center center no-repeat;
    display: flex;
    justify-content: center;
    align-items: center;

    h1{
      position: relative;
      bottom: 10px;
      color: #fff;
      font-weight: 700;
      font-size: 30px;
      letter-spacing: 5px;
    }
  }
  
  .box{
    border: 1px solid rgba(25,186,139,.17); 
    background: rgba(255,255,255,.04) url(./assets/images/line.png);
    background-size: 100% auto;
    position: relative;

    margin-bottom: 15px;

  }

  .left{
    grid-column-start: 1;
    grid-column-end: 4;
    grid-row-start: 2;
    grid-row-end: 11;


  }
  
  .right{
    grid-column-start: 8;
    grid-column-end: 11;
    grid-row-start: 2;
    grid-row-end: 11;


  }
  .center{
    grid-column-start: 4;
    grid-column-end: 8;
    grid-row-start: 2;
    grid-row-end: 11;

    .digit-board{
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      margin: 0 15px;      
      margin-bottom: 5px;
      padding: 2px;
      border: 1px solid rgba(25,186,139,.17); 
      background: rgba(255,255,255,.04) url(./assets/images/line.png); 

      .digit{
        font-family: electronicFont;
        color: #ffeb7b;
        font-size: 45px;
      }

      .info{
        color: #fff;
        font-size: 15px;
      }
    }
  }
  


}
</style>