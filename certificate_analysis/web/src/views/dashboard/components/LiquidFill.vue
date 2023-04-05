<template>
  <div :id="id" class="liquid-fill" :style="{ width: '200px', height: '200px' }"></div>
</template>

<script>
import 'echarts-liquidfill'

export default {
  props: {
    id: String,
    title: String
  },
  mounted() {
    this.renderLiquid()
  },
  methods: {
    renderLiquid() {

      let liquidFillChart = this.$echarts.init(document.getElementById(this.id));

      let compositeScore = 0.68;

      const option = {
        backgroundColor: "#021124",
        graphic: [
          {
            type: "group",
            left: "center",
            top: "58%",
            children: [
              {
                type: "text",
                z: 100,
                left: "10",
                top: "middle",
                style: {
                  fill: "#fff",
                  text: this.title,
                  font: "16px PingFangSC-Regular",
                },
              },
            ],
          },
        ],
        series: [
          {
            type: "liquidFill", //水位图
            radius: "75%", //显示比例
            center: ["50%", "50%"], //中心点
            data: [compositeScore], // data个数代表波浪数
            color: ["rgba(56, 194, 198, 0.5)"], //波浪颜色
            backgroundStyle: {
              color: {
                type: "linearGradient",
                x: 0,
                y: 0,
                x2: 0,
                y2: 1,
                colorStops: [
                  {
                    offset: 1,
                    color: "rgba(17,65,86,0.64)",
                  },
                  {
                    offset: 0.51,
                    color: "rgba(32,70,84,0)",
                  },
                  {
                    offset: 0,
                    color: "rgba(99,254,254,0.35)",
                  },
                ],
                globalCoord: false,
              },
            },
            label: {
              //标签设置
              position: ["50%", "50%"],
              formatter: compositeScore * 100 + "%", //显示文本
              fontSize: "30px",
              fontFamily: "DINAlternate-Bold",
              fontWeight: "bold",
              color: "#fff",
            },
            outline: {
              show: true,
              borderDistance: 5,
              itemStyle: {
                borderColor: "#1B5151",
                borderWidth: 1,
              },
            },
          },
        ],
      };


      liquidFillChart.setOption(option)
    }
  }
}
</script>

<style scoped></style>