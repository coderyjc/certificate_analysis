<template>
  <div :id="id" class="chart" :style="style"></div>
</template>

<script>

export default {
  props: {
    type: {
      type: String
    },
    title: {
      type: String,
      default: ""
    },
    id:{
      type: String
    },
    XData:{
      type: Array,
      default: () => {
        return ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
      }
    },
    YData:{
      type: Array,
      default: () =>{
        return [150, 230, 224, 218, 135, 147, 260]
      }
    }
  },
  mounted() {
    this.render()
  },
  data() {
    return {
      style:{
        width: "500px",
        height: "250px"
      }
    }
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