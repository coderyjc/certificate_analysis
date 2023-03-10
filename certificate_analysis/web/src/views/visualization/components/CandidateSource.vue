<template>
  <div id="candidate-source" class="candidate-source" :style="style"></div>
</template>

<script>
import * as geoJson from '../data/data-1528971808162-BkOXf61WX.json'

export default {
  props: {
    style:{
      type: Object,
      default: () => { 
        return {
          width: "1200px",
          height: "780px"
        }
      }
    }
  },
  mounted() {
    this.render()
  },
  methods: {
    render() {

      let chart = this.$echarts.init(document.getElementById("candidate-source"));

      //geoCoordMap把所有可能出现的城市加到数组里面
      var geoCoordMap = {
        '台湾': [121.5135, 25.0308],
        '黑龙江': [127.9688, 45.368],
        '内蒙古': [110.3467, 41.4899],
        "吉林": [125.8154, 44.2584],
        '北京': [116.4551, 40.2539],
        "辽宁": [123.1238, 42.1216],
        "河北": [114.4995, 38.1006],
        "天津": [117.4219, 39.4189],
        "山西": [112.3352, 37.9413],
        "陕西": [109.1162, 34.2004],
        "甘肃": [103.5901, 36.3043],
        "宁夏": [106.3586, 38.1775],
        "青海": [101.4038, 36.8207],
        "新疆": [87.9236, 43.5883],
        "西藏": [91.11, 29.97],
        "四川": [103.9526, 30.7617],
        "重庆": [108.384366, 30.439702],
        "山东": [117.1582, 36.8701],
        "河南": [113.4668, 34.6234],
        "江苏": [118.8062, 31.9208],
        "安徽": [117.29, 32.0581],
        "湖北": [114.3896, 30.6628],
        "浙江": [119.5313, 29.8773],
        "福建": [119.4543, 25.9222],
        "江西": [116.0046, 28.6633],
        "湖南": [113.0823, 28.2568],
        "贵州": [106.6992, 26.7682],
        "云南": [102.9199, 25.4663],
        "广东": [113.12244, 23.009505],
        "广西": [108.479, 23.1152],
        "海南": [110.3893, 19.8516],
        '上海': [121.4648, 31.2891],
      };

      //2015数据
      var d1 = {
        '安徽': 12.92,
        '北京': 7.96,
        '福建': 13.9,
        '甘肃': 12.36,
        '广东': 11.12,
        '广西': 14.05,
        '贵州': 13,
        '海南': 14.57,
        '河北': 11.35,
        '河南': 12.7,
        '黑龙江': 6,
        '湖北': 10.74,
        '湖南': 13.58,
        '吉林': 5.87,
        '江苏': 9.05,
        '江西': 13.2,
        '辽宁': 6.17,
        '内蒙古': 7.72,
        '宁夏': 12.62,
        '青海': 14.72,
        '山东': 12.55,
        '山西': 9.98,
        '陕西': 10.1,
        '上海': 7.52,
        '四川': 10.3,
        '天津': 5.84,
        '西藏': 15.75,
        '新疆': 15.59,
        '云南': 12.88,
        '浙江': 10.52,
        '重庆': 11.05,
      };
      //2016数据
      var d2 = {
        '安徽': 13.02,
        '北京': 9.32,
        '福建': 14.5,
        '甘肃': 12.18,
        '广东': 11.85,
        '广西': 13.82,
        '贵州': 13.43,
        '海南': 14.57,
        '河北': 12.42,
        '河南': 13.26,
        '黑龙江': 6.12,
        '湖北': 12.04,
        '湖南': 13.57,
        '吉林': 5.55,
        '江苏': 9.76,
        '江西': 13.45,
        '辽宁': 6.6,
        '内蒙古': 9.03,
        '宁夏': 13.69,
        '青海': 14.7,
        '山东': 17.89,
        '山西': 10.29,
        '陕西': 10.64,
        '上海': 9,
        '四川': 10.48,
        '天津': 7.37,
        '西藏': 15.79,
        '新疆': 15.34,
        '云南': 13.16,
        '浙江': 11.22,
        '重庆': 11.77,


      };
      //2017数据
      var d3 = {
        '安徽': 14.07,
        '北京': 9.06,
        '福建': 15,
        '甘肃': 12.54,
        '广东': 13.68,
        '广西': 15.14,
        '贵州': 13.98,
        '海南': 14.73,
        '河北': 13.2,
        '河南': 12.95,
        '黑龙江': 6.22,
        '湖北': 12.6,
        '湖南': 13.27,
        '吉林': 6.76,
        '江苏': 9.71,
        '江西': 13.79,
        '辽宁': 6.49,
        '内蒙古': 9.47,
        '宁夏': 13.44,
        '青海': 14.42,
        '山东': 17.54,
        '山西': 11.06,
        '陕西': 11.11,
        '上海': 8.1,
        '四川': 11.26,
        '天津': 7.65,
        '西藏': 16,
        '新疆': 15.88,
        '云南': 13.53,
        '浙江': 11.92,
        '重庆': 11.18,


      };
      //2018数据
      var d4 = {
        '安徽': 12.41,
        '北京': 8.24,
        '福建': 13.2,
        '甘肃': 11.07,
        '广东': 12.79,
        '广西': 14.12,
        '贵州': 13.9,
        '海南': 14.48,
        '河北': 11.26,
        '河南': 11.72,
        '黑龙江': 5.98,
        '湖北': 11.54,
        '湖南': 12.19,
        '吉林': 6.62,
        '江苏': 9.32,
        '江西': 13.43,
        '辽宁': 6.39,
        '内蒙古': 8.35,
        '宁夏': 13.32,
        '青海': 14.31,
        '山东': 13.26,
        '山西': 9.63,
        '陕西': 10.67,
        '上海': 7.2,
        '四川': 11.05,
        '天津': 6.67,
        '西藏': 15.22,
        '新疆': 10.69,
        '云南': 13.19,
        '浙江': 11.02,
        '重庆': 11.02,


      };
     
      var colors = [
        [" #7ea4df", "#df9446", "#8b4044", "#D4bf9a", "#adbfbc", "#b3aba3", "#FFC809", "#FB95D5", "#BDA29A", "#6E7074", "#546570", "#C4CCD3"],
        ["#37A2DA", "#67E0E3", "#32C5E9", "#9FE6B8", "#FFDB5C", "#FF9F7F", "#FB7293", "#E062AE", "#E690D1", "#E7BCF3", "#9D96F5", "#8378EA", "#8378EA"],
        ["#DD6B66", "#759AA0", "#E69D87", "#8DC1A9", "#EA7E53", "#EEDD78", "#73A373", "#73B9BC", "#7289AB", "#91CA8C", "#F49F42"],
      ];
      var colorIndex = 0;

      var year = ["2015", "2016", "2017", "2018"];
      var mapData = [
        [],
        [],
        [],
        [],
      ];

      /*柱子Y名称*/
      var categoryData = [];
      var barData = [];

      for (var key in geoCoordMap) {
        mapData[0].push({
          "year": '2015年',
          "name": key,
          "value": d1[key],
        });
        mapData[1].push({
          "year": '2016年',
          "name": key,
          "value": d2[key],
        });
        mapData[2].push({
          "year": '2017年',
          "name": key,
          "value": d3[key],
        });
        mapData[3].push({
          "year": '2018年',
          "name": key,
          "value": d4[key],
        });
      }

      for (var i = 0; i < mapData.length; i++) {
        mapData[i].sort(function sortNumber(a, b) {
          return a.value - b.value
        });
        barData.push([]);
        categoryData.push([]);
        for (var j = 0; j < mapData[i].length; j++) {
          barData[i].push(mapData[i][j].value);
          categoryData[i].push(mapData[i][j].name);
        }
      }

      this.$echarts.registerMap('china', geoJson);
      var convertData = function (data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
          var geoCoord = geoCoordMap[data[i].name];
          if (geoCoord) {
            res.push({
              name: data[i].name,
              value: geoCoord.concat(data[i].value)
            });
          }
        }
        return res;
      };

      const optionXyMap01 = {
        timeline: {
          data: year,
          axisType: 'category',
          autoPlay: true,
          playInterval: 3000,
          left: '10%',
          right: '10%',
          bottom: '3%',
          width: '80%',
          label: {
            normal: {
              textStyle: {
                color: '#ddd'
              }
            },
            emphasis: {
              textStyle: {
                color: '#fff'
              }
            }
          },
          symbolSize: 10,
          lineStyle: {
            color: '#555'
          },
          checkpointStyle: {
            borderColor: '#777',
            borderWidth: 2
          },
          controlStyle: {
            showNextBtn: true,
            showPrevBtn: true,
            normal: {
              color: '#666',
              borderColor: '#666'
            },
            emphasis: {
              color: '#aaa',
              borderColor: '#aaa'
            }
          },

        },
        baseOption: {
          animation: true,
          animationDuration: 1000,
          animationEasing: 'cubicInOut',
          animationDurationUpdate: 1000,
          animationEasingUpdate: 'cubicInOut',
          grid: {
            right: '1%',
            top: '15%',
            bottom: '10%',
            width: '20%'
          },
          tooltip: {
            trigger: 'axis', // hover触发器
            axisPointer: { // 坐标轴指示器，坐标轴触发有效
              type: 'shadow', // 默认为直线，可选为：'line' | 'shadow'
              shadowStyle: {
                color: 'rgba(150,150,150,0.1)' //hover颜色
              }
            }
          },
          geo: {
            show: true,
            map: 'china',
            roam: false,
            zoom: 1,
            center: [113.83531246, 34.0267395887],
            label: {
              emphasis: {
                show: false
              }
            },
            itemStyle: {
              normal: {
                borderColor: 'rgba(147, 235, 248, 1)',
                borderWidth: 1,
                areaColor: {
                  type: 'radial',
                  x: 0.5,
                  y: 0.5,
                  r: 0.8,
                  colorStops: [{
                    offset: 0,
                    color: 'rgba(147, 235, 248, 0)' // 0% 处的颜色
                  }, {
                    offset: 1,
                    color: 'rgba(147, 235, 248, .2)' // 100% 处的颜色
                  }],
                  globalCoord: false // 缺省为 false
                },
                shadowColor: 'rgba(128, 217, 248, 1)',
                // shadowColor: 'rgba(255, 255, 255, 1)',
                shadowOffsetX: -2,
                shadowOffsetY: 2,
                shadowBlur: 10
              },
              emphasis: {
                areaColor: '#389BB7',
                borderWidth: 0
              }
            }
          },
        },
        options: []

      };

      for (var n = 0; n < year.length; n++) {
        optionXyMap01.options.push({
          backgroundColor: '#021124',
          title:
            [{
              text: '河北省教师资格证考生来源',
              textStyle: {
                color: '#e4bfad',
                fontfamily: "sans-serif",
                fontSize: 26
              },
              subtext: '数据来源：教师资格证报名系统',
              left: '5%',
              top: '5%',
              subtextStyle: {
                color: '#e4bfad',
                fontSize: 12,
                fontWeight: 'normal'
              },
            },
            {
              id: 'statistic',
              text: year[n] + "年考生来源",
              left: '75%',
              top: '5%',
              textStyle: {
                color: '#e4bfad',
                fontSize: 16
              }
            }
            ],
          xAxis: {
            type: 'value',
            scale: true,
            position: 'top',
            min: 3,
            max: 18,
            boundaryGap: false,
            splitLine: {
              show: false
            },
            axisLine: {
              show: false
            },
            axisTick: {
              show: false
            },
            axisLabel: {
              margin: 1,
              textStyle: {
                color: '#e4bfad'
              }
            },
          },
          yAxis: {
            type: 'category',
            //  name: 'TOP',
            nameGap: 16,
            axisLine: {
              show: true,
              lineStyle: {
                color: '#e4bfad'
              }
            },
            axisTick: {
              show: false,
              lineStyle: {
                color: '#e4bfad'
              }
            },
            axisLabel: {
              interval: 0,
              textStyle: {
                color: '#e4bfad'
              }
            },
            data: categoryData[n]
          },

          series: [
            //地图
            {
              type: 'map',
              map: 'china',
              geoIndex: 0,
              aspectScale: 0.75, //长宽比
              showLegendSymbol: false, // 存在legend时显示
              label: {
                normal: {
                  show: false
                },
                emphasis: {
                  show: false,
                  textStyle: {
                    color: '#fff'
                  }
                }
              },
              roam: true,
              itemStyle: {
                normal: {
                  areaColor: '#031525',
                  borderColor: '#e4bfad',
                },
                emphasis: {
                  areaColor: '#2B91B7'
                }
              },
              animation: false,
              data: mapData
            },
            //地图中闪烁的点
            {
              //  name: 'Top 5',
              type: 'effectScatter',
              coordinateSystem: 'geo',
              data: convertData(mapData[n].sort(function (a, b) {
                return b.value - a.value;
              }).slice(0, 40)),
              symbolSize: function (val) {
                return val[2] * 3;
              },
              showEffectOn: 'render',
              rippleEffect: {
                brushType: 'stroke'
              },
              hoverAnimation: true,
              label: {
                normal: {
                  formatter: '{b}',
                  position: 'right',
                  show: true
                }
              },
              itemStyle: {
                normal: {
                  color: colors[colorIndex][n],
                  shadowBlur: 10,
                  shadowColor: colors[colorIndex][n]
                }
              },
              zlevel: 1
            },
            //柱状图
            {
              zlevel: 1.5,
              type: 'bar',
              symbol: 'none',
              itemStyle: {
                normal: {
                  color: colors[colorIndex][n]
                }
              },
              data: barData[n]
            }
          ]
        })
      }
      chart.setOption(optionXyMap01);
    }
  }
}
</script>

<style scoped>

</style>