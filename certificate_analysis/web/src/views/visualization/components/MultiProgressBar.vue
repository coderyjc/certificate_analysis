<template>
  <div id="multi-progress-bar" class="multi-progress-bar" :style="style"></div>
</template>

<script>
export default {
  data() {
    return {
      style: {
        width: "800px",
        height: "300px"
      }
    }
  },
  mounted() {
    this.render()
  },
  methods: {
    render() {
      let chart = this.$echarts.init(document.getElementById("multi-progress-bar"));


      let category = [
        {
          name: '教育学成绩',
          value: 56
        },
        {
          name: "教育心理学成绩",
          value: 65
        },
        {
          name: "职业道德修养和高等教育法规成绩",
          value: 72
        },
      ];
      let yName = []; // y轴名称
      let bgData = []; // 最大值用作背景显示的数据
      let maxValue = 120; //最大值
      category.forEach(element => {
        yName.push(element.name);
        bgData.push({
          name: element.name,
          value: maxValue,
          type: element.type,
        });
      });
      const option = {
        backgroundColor: '#000000',
        xAxis: {
          max: maxValue,
          splitLine: {
            show: false
          },
          axisLine: {
            show: false
          },
          axisLabel: {
            show: false
          },
          axisTick: {
            show: false
          }
        },
        grid: {
          left: 80,
          top: 20,
          right: 80,
          bottom: 20
        },
        yAxis: [
          { // 每条图形上面的文字
            inverse: false,
            data: yName,
            axisLabel: {
              padding: [0, 0, 45, 0],
              inside: true,
              textStyle: {
                fontSize: 20,
                fontWeight: 400,
                color: '#B1C3DD',
                align: 'left',
              },
              formatter: '{value}',
              rich: {
                a: {
                  color: 'transparent',
                  lineHeight: 20,
                  fontSize: 14,
                  shadowColor: 'rgba(0, 0, 0, 1)',
                  shadowBlur: 10,
                },
              },
            },
            splitLine: {
              show: false,
            },
            axisTick: {
              show: false,
            },
            axisLine: {
              show: false,
            },
          },
          { // y轴最右侧的文字
            axisTick: 'none',
            axisLine: 'none',
            type: 'category',
            axisLabel: {
              margin: 10,
              textStyle: {
                color: '#6DE8FA',
                fontSize: '14',
              }
            },
            data: category,
          }],
        series: [
          {
            // 背景样式
            name: '背景',
            type: "bar",
            barWidth: 18,
            stack: '总量',
            barGap: '-100%',
            symbol: 'fixed',
            symbolRepeat: 'repeat',
            legendHoverLink: false,
            itemStyle: {
              normal: {
                color: 'rgba(153, 153, 153, 0.23)'
              }
            },
            data: bgData,
            animation: false, //关闭动画
            z: 0
          },
          {
            name: 'info',
            // 内（显示的内容）
            type: "bar",
            barGap: '-100%',
            barWidth: 18,
            legendHoverLink: false,
            silent: true,
            itemStyle: {
              normal: {
                color: function (params) {
                  console.log(params.dataIndex)
                  var color;
                  if (params.dataIndex % 2 != 0) {
                    color = {
                      type: "linear",
                      x: 0,
                      y: 0,
                      x2: 1,
                      y2: 0,
                      colorStops: [{
                        offset: 0,
                        color: 'rgba(46,85,185,0.5)' // 0% 处的颜色
                      },
                      {
                        offset: 1,
                        color: '#317fff' // 100% 处的颜色
                      }
                      ]
                    }
                  } else {
                    color = {
                      type: "linear",
                      x: 0,
                      y: 0,
                      x2: 1,
                      y2: 0,
                      colorStops: [{
                        offset: 0,
                        color: 'rgba(136,68,68,0.5)' // 0% 处的颜色
                      },
                      {
                        offset: 1,
                        color: '#ff7171' // 100% 处的颜色
                      }
                      ]
                    }
                  }
                  return color;
                },
              }
            },
            data: category,
            z: 1,
            animationEasing: "elasticOut"
          },
          {
            // 分隔
            type: "pictorialBar",
            itemStyle: {
              normal: {
                color: 'rgba(0,0,0,1)'
              }
            },
            symbolRepeat: "fixed",
            symbolMargin: 80,
            symbol: "rect",
            symbolClip: true,
            symbolSize: [5, 21],
            symbolPosition: "start",
            symbolOffset: [0, 0],
            symbolBoundingData: maxValue,
            data: bgData,
            animation: false, //关闭动画
            z: 2
          }
        ]
      };


      chart.setOption(option)
    }
  }
}
</script>

<style scoped></style>