<template>
  <div :id="id" class="chart" :style="style"></div>
</template>

<script>

import { getIdentificationData } from '@/api/dashboard'

export default {
  props: {
    title: {
      type: String,
      default: ""
    },
    id:{
      type: String
    },
  },
  data() {
    return {
      style:{
        width: "500px",
        height: "250px"
      },
      XData: [],
      YData: []
    }
  },
  mounted() {
    getIdentificationData().then(result => {
      const identification_trend = result.data.data.identification_trend
      this.XData = identification_trend.category
      this.YData = identification_trend.data
      this.render()
    })
    
  },
  methods: {
    render() {
      let chart = this.$echarts.init(document.getElementById(this.id));
      const option = {
        title: {
          text: this.title
        },
        xAxis: {
          type: 'category',
          data: this.XData
        },
        yAxis: {
          type: 'value'
        },
        tooltip: {
          trigger: 'axis'
        },
        series: [
          {
            data: this.YData,
            type: 'line'
          }
        ]
      };

      chart.setOption(option)
    }
  }
}

</script>

<style lang="scss" scoped>

.chart{
  background-color: #fff;
  padding-top: 10px;
  padding-left: 10px;
  margin-left: 20px;
  margin-bottom: 20px;
}

</style>