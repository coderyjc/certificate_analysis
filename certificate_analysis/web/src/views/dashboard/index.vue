<template>
  <div class="mainbox">
    <ul>
      <li>
        <div class="boxall">
          <div class="alltitle">最高学位</div>
          <div class="allnav" ref="echart1" id="echart1"></div>
        </div>
        <div class="boxall">
          <div class="alltitle">最高学历</div>
          <div class="allnav" ref="echart2" id="echart2"></div>
        </div>
        <div class="boxall">
          <div style="height:100%; width: 100%;">
            <div class="sy" id="fb1"></div>
            <div class="sy" id="fb2"></div>
            <div class="sy" id="fb3"></div>
          </div>
        </div>
      </li>
      <li>
        <div class="bar">
          <ul class="barbox">
            <li class="counter">{{ total_interview }}</li>
            <li class="text">面试信息总数</li>
          </ul>
          <ul class="barbox">
            <li class="counter">{{ total_identification }}</li>
            <li class="text">认定信息总数</li>
          </ul>
          <ul class="barbox">
            <li class="counter">{{ total_written }}</li>
            <li class="text">笔试信息总数</li>
          </ul>
        </div>
        <div class="map">
          <div class="map4" id="map_1"></div>
        </div>
      </li>
      <li>
        <div class="boxall">
          <div class="alltitle">2018年面试等级</div>
          <div class="allnav" id="echart4"></div>
        </div>
        <div class="boxall">
          <div class="alltitle">资格种类</div>
          <div class="allnav" id="echart5"></div>
        </div>
        <div class="boxall">
          <div class="alltitle">任教学科</div>
          <div class="allnav" id="echart6"></div>
        </div>
      </li>
    </ul>
  </div>
  <div class="back"></div>
</template>

<script>

import * as geoJson from '@/views/dashboard/data/hebei.json'
import 'echarts-wordcloud'

import { statisticIdentification, listProvinceCount } from '@/api/query/identification'
import { statisticInterviewScore } from '@/api/query/interview'
import { getDashboardTotalData } from '@/api/dashboard'

export default {
  components: {
  },
  data(){
    return {
      total_identification: "------",
      total_written: "------",
      total_interview: "------",
    }
  },
  mounted() {
    getDashboardTotalData().then(res => {
      this.total_written = res.data.data.total_written
      this.total_interview = res.data.data.total_interview
      this.total_identification = res.data.data.total_identification
    })
    this.echarts_1()
    this.echarts_2()
    this.echarts_4()
    this.echarts_31()
    this.echarts_32()
    this.echarts_33()
    this.echarts_5()
    this.echarts_6()
    this.map()
  },
  methods: {
    echarts_1() { // 最高学位

      statisticIdentification({
        "affirmBatch": "2016年秋季",
        "affirmBatchStart": "",
        "affirmBatchEnd": "",
        "statisticItem": ["highestDegree"]
      }).then(res => {
        const x_data = []
        const y_data = []
        res.data.data.forEach(e => {
          x_data.push(e.highestDegree)
          y_data.push(e.count)
        })

        // 基于准备好的dom，初始化echarts实例
        var myChart = this.$echarts.init(this.$refs.echart1)

        const option = {
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'shadow',
            },
          },
          grid: {
            left: '0%',
            top: '10px',
            right: '0%',
            bottom: '4%',
            containLabel: true,
          },
          xAxis: [
            {
              type: 'category',
              data: x_data,
              axisLine: {
                show: true,
                lineStyle: {
                  color: 'rgba(255,255,255,.1)',
                  width: 1,
                  type: 'solid',
                },
              },

              axisTick: {
                show: false,
              },
              axisLabel: {
                interval: 0,
                // rotate:50,
                show: true,
                splitNumber: 15,
                textStyle: {
                  color: 'rgba(255,255,255,.6)',
                  fontSize: '12',
                },
              },
            },
          ],
          yAxis: [
            {
              type: 'value',
              axisLabel: {
                //formatter: '{value} %'
                show: true,
                textStyle: {
                  color: 'rgba(255,255,255,.6)',
                  fontSize: '12',
                },
              },
              axisTick: {
                show: false,
              },
              axisLine: {
                show: true,
                lineStyle: {
                  color: 'rgba(255,255,255,.1	)',
                  width: 1,
                  type: 'solid',
                },
              },
              splitLine: {
                lineStyle: {
                  color: 'rgba(255,255,255,.1)',
                },
              },
            },
          ],
          series: [
            {
              type: 'bar',
              data: y_data,
              barWidth: '35%', //柱子宽度
              itemStyle: {
                normal: {
                  color: '#2f89cf',
                  opacity: 1,
                  barBorderRadius: 5,
                },
              },
            },
          ],
        }

        myChart.setOption(option)
      })

      // 使用刚指定的配置项和数据显示图表。
      // window.addEventListener('resize', function () {
      //   myChart.resize()
      // })
    },
    echarts_2() { // 最高学历

      statisticIdentification({
        "affirmBatch": "2016年秋季",
        "affirmBatchStart": "",
        "affirmBatchEnd": "",
        "statisticItem": ["highestEducationBackground"]
      }).then(res => {
        const x_data = []
        const y_data = []
        res.data.data.forEach(e => {
          x_data.push(e.highestEducationBackground)
          y_data.push(e.count)
        })

        // 基于准备好的dom，初始化echarts实例
        var myChart = this.$echarts.init(this.$refs.echart2)

        const option = {
          tooltip: {
            trigger: 'axis',
            axisPointer: { type: 'shadow' },
          },
          grid: {
            left: '0%',
            top: '10px',
            right: '0%',
            bottom: '4%',
            containLabel: true,
          },
          xAxis: [
            {
              type: 'category',
              data: x_data,
              axisLine: {
                show: true,
                lineStyle: {
                  color: 'rgba(255,255,255,.1)',
                  width: 1,
                  type: 'solid',
                },
              },

              axisTick: {
                show: false,
              },
              axisLabel: {
                interval: 0,
                // rotate:50,
                show: true,
                splitNumber: 15,
                textStyle: {
                  color: 'rgba(255,255,255,.6)',
                  fontSize: '12',
                },
              },
            },
          ],
          yAxis: [
            {
              type: 'value',
              axisLabel: {
                //formatter: '{value} %'
                show: true,
                textStyle: {
                  color: 'rgba(255,255,255,.6)',
                  fontSize: '12',
                },
              },
              axisTick: {
                show: false,
              },
              axisLine: {
                show: true,
                lineStyle: {
                  color: 'rgba(255,255,255,.1	)',
                  width: 1,
                  type: 'solid',
                },
              },
              splitLine: {
                lineStyle: {
                  color: 'rgba(255,255,255,.1)',
                },
              },
            },
          ],
          series: [
            {
              type: 'bar',
              data: y_data,
              barWidth: '35%', //柱子宽度
              // barGap: 1, //柱子之间间距
              itemStyle: {
                normal: {
                  color: '#27d08a',
                  opacity: 1,
                  barBorderRadius: 5,
                },
              },
            },
          ],
        }

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option)
      })



      // window.addEventListener('resize', function () {
      //   myChart.resize()
      // })
    },
    echarts_31() { // 性别

      statisticIdentification({
        "affirmBatch": "2016年秋季",
        "affirmBatchStart": "",
        "affirmBatchEnd": "",
        "statisticItem": ["gender"]
      }).then(res => {
        const label = []
        const data = []
        res.data.data.forEach(e => {
          label.push(e.gender == 0 ? "女" : "男")
          data.push({
            name: e.gender == 0 ? "女" : "男",
            value: e.count
          })
        })

        // 基于准备好的dom，初始化echarts实例
        var myChart = this.$echarts.init(document.getElementById('fb1'))
        const option = {
          title: [
            {
              text: '性别分布',
              left: 'center',
              textStyle: {
                color: '#fff',
                fontSize: '16',
              },
            },
          ],
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b}: {c} ({d}%)',
            position: function (p) {
              //其中p为当前鼠标的位置
              return [p[0] + 10, p[1] - 10]
            },
          },
          legend: {
            top: '70%',
            itemWidth: 10,
            itemHeight: 10,
            data: label,
            textStyle: {
              color: 'rgba(255,255,255,.5)',
              fontSize: '12',
            },
          },
          series: [
            {
              name: '性别分布',
              type: 'pie',
              center: ['50%', '42%'],
              radius: ['40%', '60%'],
              color: [
                '#065aab',
                '#06f0ab',
              ],
              label: { show: false },
              labelLine: { show: false },
              data: data,
            },
          ],
        }

        myChart.setOption(option)
      })



      // window.addEventListener('resize', function () {
      //   myChart.resize()
      // })
    },
    echarts_32() { // 专业类别

      statisticIdentification({
        "affirmBatch": "2016年秋季",
        "affirmBatchStart": "",
        "affirmBatchEnd": "",
        "statisticItem": ["majorType"]
      }).then(res => {
        const label = []
        const data = []
        res.data.data.forEach(e => {
          label.push(e.majorType)
          data.push({
            name: e.majorType,
            value: e.count
          })
        })

        // 基于准备好的dom，初始化echarts实例
        var myChart = this.$echarts.init(document.getElementById('fb2'))
        const option = {
          title: [
            {
              text: '专业类别',
              left: 'center',
              textStyle: {
                color: '#fff',
                fontSize: '16',
              },
            },
          ],
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b}: {c} ({d}%)',
            position: function (p) {
              //其中p为当前鼠标的位置
              return [p[0] + 10, p[1] - 10]
            },
          },
          legend: {
            top: '70%',
            itemWidth: 10,
            itemHeight: 10,
            data: label,
            textStyle: {
              color: 'rgba(255,255,255,.5)',
              fontSize: '12',
            },
          },
          series: [
            {
              name: '专业类别',
              type: 'pie',
              center: ['50%', '42%'],
              radius: ['40%', '60%'],
              color: [
                '#065aab',
                '#06f0ab',
              ],
              label: { show: false },
              labelLine: { show: false },
              data: data,
            },
          ],
        }

        myChart.setOption(option)


      })


      // window.addEventListener('resize', function () {
      //   myChart.resize()
      // })
    },
    echarts_33() { // 考试类型
      // 基于准备好的dom，初始化echarts实例

      statisticIdentification({
        "affirmBatch": "2016年秋季",
        "affirmBatchStart": "",
        "affirmBatchEnd": "",
        "statisticItem": ["examType"]
      }).then(res => {
        const label = []
        const data = []
        res.data.data.forEach(e => {
          label.push(e.examType)
          data.push({
            name: e.examType,
            value: e.count
          })
        })


        var myChart = this.$echarts.init(document.getElementById('fb3'))
        const option = {
          title: [
            {
              text: '考试类型',
              left: 'center',
              textStyle: {
                color: '#fff',
                fontSize: '16',
              },
            },
          ],
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b}: {c} ({d}%)',
            position: function (p) {
              //其中p为当前鼠标的位置
              return [p[0] + 10, p[1] - 10]
            },
          },
          legend: {
            top: '70%',
            itemWidth: 10,
            itemHeight: 10,
            data: label,
            textStyle: {
              color: 'rgba(255,255,255,.5)',
              fontSize: '12',
            },
          },
          series: [
            {
              name: '考试类型',
              type: 'pie',
              center: ['50%', '42%'],
              radius: ['40%', '60%'],
              color: [
                '#065aab',
                '#06f0ab',
              ],
              label: { show: false },
              labelLine: { show: false },
              data: data,
            },
          ],
        }

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option)
      })



      // window.addEventListener('resize', function () {
      //   myChart.resize()
      // })
    },
    echarts_5() { // 资格种类

      statisticIdentification({
        "affirmBatch": "2016年秋季",
        "affirmBatchStart": "",
        "affirmBatchEnd": "",
        "statisticItem": ["qualificationType"]
      }).then(res => {
        const x_data = []
        const y_data = []
        res.data.data.forEach(e => {
          x_data.push(e.qualificationType)
          y_data.push(e.count)
        })

        // 基于准备好的dom，初始化echarts实例
        var myChart = this.$echarts.init(document.getElementById('echart5'))

        const option = {
          //  backgroundColor: '#00265f',
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'shadow',
            },
          },

          grid: {
            left: '0%',
            top: '10px',
            right: '0%',
            bottom: '2%',
            containLabel: true,
          },
          xAxis: [
            {
              type: 'category',
              data: x_data,
              axisLine: {
                show: true,
                lineStyle: {
                  color: 'rgba(255,255,255,.1)',
                  width: 1,
                  type: 'solid',
                },
              },

              axisTick: {
                show: false,
              },
              axisLabel: {
                interval: 0,
                // rotate:50,
                show: true,
                splitNumber: 15,
                textStyle: {
                  color: 'rgba(255,255,255,.6)',
                  fontSize: '12',
                },
              },
            },
          ],
          yAxis: [
            {
              type: 'value',
              axisLabel: {
                show: true,
                textStyle: {
                  color: 'rgba(255,255,255,.6)',
                  fontSize: '12',
                },
              },
              axisTick: {
                show: false,
              },
              axisLine: {
                show: true,
                lineStyle: {
                  color: 'rgba(255,255,255,.1	)',
                  width: 1,
                  type: 'solid',
                },
              },
              splitLine: {
                lineStyle: {
                  color: 'rgba(255,255,255,.1)',
                },
              },
            },
          ],
          series: [
            {
              type: 'bar',
              data: y_data,
              barWidth: '35%', //柱子宽度
              itemStyle: {
                normal: {
                  color: '#2f89cf',
                  opacity: 1,
                  barBorderRadius: 5,
                },
              },
            },
          ],
        }

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option)
      })

      // window.addEventListener('resize', function () {
      //   myChart.resize()
      // })
    },
    echarts_4() { // 面试成绩

      statisticInterviewScore({
        "year": "2018",
        "startYear": "",
        "endYear": "",
        "statisticItem": ["level"]
      }).then(res => {

        const label = []
        const data = []

        res.data.data.forEach(e => {
          label.push(e.level)
          data.push({
            name: e.level,
            value: e.count
          })
        })

        const myChart = this.$echarts.init(document.getElementById('echart4'))
        const option = {
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b}: {c} ({d}%)',
            position: function (p) {
              //其中p为当前鼠标的位置
              return [p[0] + 10, p[1] - 10]
            },
          },
          legend: {
            top: '70%',
            itemWidth: 10,
            itemHeight: 10,
            data: label,
            textStyle: {
              color: 'rgba(255,255,255,.5)',
              fontSize: '12',
            },
          },
          series: [
            {
              name: '面试成绩',
              type: 'pie',
              center: ['50%', '42%'],
              radius: ['40%', '60%'],
              color: [
                '#06f0ab',
                '#0f78d6',
                '#0fa0d6',
                '#0f78d6',
                '#065aab',
              ],
              label: { show: false },
              labelLine: { show: false },
              data: data,
            },
          ],
        }

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option)

      })


      // window.addEventListener('resize', function () {
      //   myChart.resize()
      // })
    },
    echarts_6() { // 任教学科（词云图）
      // 基于准备好的dom，初始化echarts实例
      var myChart = this.$echarts.init(document.getElementById('echart6'))

      statisticIdentification({
        "affirmBatch": "2016年秋季",
        "affirmBatchStart": "",
        "affirmBatchEnd": "",
        "statisticItem": ["subject"]
      }).then(res => {
        res.data.data.sort((a, b) => { return b.count - a.count });
        res.data.data.splice(50)

        const list = []
        res.data.data.forEach(e => {
          list.push({
            name: e.subject,
            value: e.count
          })
        })

        const option = {
          tooltip: {
            show: true,
            borderColor: '#fe9a8bb3',
            borderWidth: 1,
            padding: [5, 5, 5, 5],
            confine: true,
            backgroundColor: 'rgba(255, 255, 255, .9)',
            textStyle: {
              color: 'hotpink',
              lineHeight: 22
            },
            extraCssText: 'box-shadow: 0 4px 20px -4px rgba(199, 206, 215, .7);border-radius: 4px;'
          },
          series: [
            {
              type: 'wordCloud',
              // The shape of the "cloud" to draw. Can be any polar equation represented as a
              // callback function, or a keyword present. Available presents are circle (default),
              // cardioid (apple or heart shape curve, the most known polar equation), diamond (
              // alias of square), triangle-forward, triangle, (alias of triangle-upright, pentagon, and star.

              shape: 'pentagon',

              // A silhouette image which the white area will be excluded from drawing texts.
              // The shape option will continue to apply as the shape of the cloud to grow.

              // Folllowing left/top/width/height/right/bottom are used for positioning the word cloud
              // Default to be put in the center and has 75% x 80% size.

              left: 'center',
              top: 'center',
              width: '100%',
              height: '100%',
              right: null,
              bottom: null,

              // Text size range which the value in data will be mapped to.
              // Default to have minimum 12px and maximum 60px size.

              sizeRange: [10, 50],

              // Text rotation range and step in degree. Text will be rotated randomly in range [-90, 90] by rotationStep 45

              rotationRange: [0, 0],
              rotationStep: 0,

              // size of the grid in pixels for marking the availability of the canvas
              // the larger the grid size, the bigger the gap between words.

              gridSize: 18,

              // set to true to allow word being draw partly outside of the canvas.
              // Allow word bigger than the size of the canvas to be drawn
              drawOutOfBound: false,

              // If perform layout animation.
              // NOTE disable it will lead to UI blocking when there is lots of words.
              layoutAnimation: true,

              // Global text style
              textStyle: {
                fontFamily: 'PingFangSC-Semibold',
                fontWeight: 600,
                color: function (params) {
                  // let colors = ['#fe9a8bb3', '#fe9a8bb3', '#fe9a8b03', '#9E87FFb3', '#9E87FFb3', '#9E87FFb3', '#fe9a8bb3', '#fe9a8bb3', '#fe9a8bb3', '#73DDFF', '#27d08a']
                  let colors = ['#fe9a8b', '#fe9a8b', '#fe9a8b', '#9E87FF', '#9E87FF', '#9E87FF', '#fe9a8b', '#fe9a8b', '#fe9a8b', '#73DDFF', '#27d08a']
                  return colors[parseInt(Math.random() * 10)];
                },
              },
              emphasis: {
                focus: 'none',
              },

              // Data is an array. Each array item must have name and value property.
              data: list,
            },
          ],
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option)

      })


      // window.addEventListener('resize', function () {
      //   myChart.resize()
      // })
    },
    map() {
      // 基于准备好的dom，初始化echarts实例
      const myChart = this.$echarts.init(document.getElementById('map_1'));

      this.$echarts.registerMap('hebei', geoJson);

      listProvinceCount().then(res => {
        const data = res.data.data

        const geoCoordMap = {
          '石家庄市': [114.444982, 38.133034],
          '唐山市': [118.343325, 39.71703],
          '秦皇岛市': [119.193332, 40.088346],
          '邯郸市': [114.548854, 36.553496],
          '邢台市': [114.822676, 37.213788],
          '保定市': [115.177642, 39.025137],
          '张家口市': [115.038685, 40.874645],
          '承德市': [117.551533, 41.356206],
          '沧州市': [116.771341, 38.270959],
          '廊坊市': [116.540212, 39.111215],
          '衡水市': [115.828761, 37.7648],
        }
        const max = 50000, min = 0; // todo 
        const maxSize4Pin = 100, minSize4Pin = 20;

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
        const option = {
          // backgroundColor: '#021124',
          title: {
            text: '河北省教资报名分布',
            x: 'center',
            textStyle: {
              color: '#ccc'
            }
          },
          tooltip: {
            trigger: 'item',
            formatter: function (params) {
              if (typeof (params.value)[2] == "undefined") {
                return params.name + ' : ' + params.value;
              } else {
                return params.name + ' : ' + params.value[2];
              }
            }
          },
          visualMap: {
            show: false,
            min: 0,
            max: 30000,
            left: 'left',
            top: 'bottom',
            text: ['高', '低'], // 文本，默认为数值文本
            calculable: true,
            seriesIndex: [1],
            inRange: {
              color: ['#294fff', '#fa3333'] // 浅蓝
            }
          },
          geo: {
            show: true,
            map: 'hebei',
            label: {
              normal: {
                show: false
              },
              emphasis: {
                show: false,
              }
            },
            roam: true,
            itemStyle: {
              normal: {
                areaColor: '#1D346F',
                borderWidth: 0,
                shadowColor: '#23074d',
                shadowBlur: 20,
                shadowOffsetX: -2,
                shadowOffsetY: 5,
              },
              emphasis: {
                areaColor: '#2a333d',
              },
            },
          },
          series: [
            {
              type: 'map',
              map: 'hebei',
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
                  areaColor: '#1D346F',
                  borderColor: '#D79D3D',
                },
                emphasis: {
                  areaColor: '#0f2c70',
                },
              },
              animation: false,
              data: data
            },
            {
              name: '点',
              type: 'scatter',
              coordinateSystem: 'geo',
              symbol: 'pin',
              symbolSize: function (val) {
                var a = (maxSize4Pin - minSize4Pin) / (max - min);
                var b = minSize4Pin - a * min;
                b = maxSize4Pin - a * max;
                return a * val[2] + b;
              },
              label: {
                normal: {
                  show: true,
                  textStyle: {
                    color: '#fff',
                    fontSize: 9,
                  },
                  formatter(value) {
                    return value.data.value[2]
                  }
                }
              },
              itemStyle: {
                normal: {
                  areaColor: '#1D346F',
                  borderColor: '#D79D3D',
                },
                emphasis: {
                  areaColor: '#0f2c70',
                },
              },
              data: convertData(data),
              showEffectOn: 'render',
              rippleEffect: {
                brushType: 'stroke'
              },
              hoverAnimation: true,
              zlevel: 6
            },
            {
              name: 'Top 5',
              type: 'effectScatter',
              coordinateSystem: 'geo',
              data: convertData(data.sort(function (a, b) {
                return b.value - a.value;
              }).slice(0, 11)),
              symbolSize: function (val) {
                return val[2] / 1000;
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
                  areaColor: '#1D346F',
                  borderColor: '#D79D3D',
                },
                emphasis: {
                  areaColor: '#0f2c70',
                },
              },
              zlevel: 1
            },

          ]
        };
        myChart.setOption(option);

      })


      // window.addEventListener("resize", function () {
      //   myChart.resize();
      // });
    }
  }
}
</script>

<style lang="scss" scoped>
* {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  padding: 0px;
  margin: 0px;
  color: #222;
  font-family: "微软雅黑";
}

@font-face {
  font-family: electronicFont;
  src: url(@/views/dashboard/assets/font/DS-DIGIT.TTF)
}

img {
  border: none;
  max-width: 100%;
}

.mainbox {
  background: #000d4a url(@/views/dashboard/assets/images/bg.jpg) center top;
  background-size: cover;
  color: #666;
  font-size: 15px;
  height: 100%;

  ul {
    height: 100%;

    li {
      list-style-type: none;
      float: left;
      padding: 0 .1rem;
      width: 30%;
      height: 100%;

      &:nth-child(2) {
        width: 40%;
        padding: 0
      }

      .map {
        position: relative;
        width: 100%;
        height: 72%;
        z-index: 9;

        .map4 {
          width: 200%;
          height: 100%;
          position: relative;
          left: -50%;
          top: 4%;
          z-index: 5;
        }

      }

      .boxall {
        border: 1px solid rgba(25, 186, 139, .17);
        background: rgba(255, 255, 255, .04) url(@/views/dashboard/assets/images/line.png);
        background-size: 100% auto;
        position: relative;
        height: 32%;
        margin-top: 5px;
        padding-top: 15px;
        margin-bottom: .15rem;
        z-index: 10;

        .allnav {
          height: calc(100% - 30px);
        }

        .alltitle {
          font-size: 1rem;
          color: #fff;
          text-align: center;
          line-height: .5rem;
          padding-bottom: 10px;
        }

        .sy {
          float: left;
          width: 33%;
          height: 95%;
          margin-top: .25rem;
        }

      }

      .bar {
        margin-top: 5px;
        background: rgba(101, 132, 226, .1);
        padding: .15rem;
        display: flex;
        flex-direction: row;
        justify-content: center;


        .barbox {
          display: flex;
          flex-direction: column;
          align-items: center;
          justify-content: center;
          width: 30%;

          .counter {
            width: 100%;
            font-family: electronicFont;
            color: rgb(255, 255, 0);
            text-align: center;
            font-size: 40px;
          }

          .text {
            width: 100%;
            color: #fff;
            font-size: 15px;
            text-align: center;
          }

          &:nth-child(2) {
            font-size: 45px;
            border-left: rgb(119, 145, 146) solid 0.5px;
            border-right: rgb(119, 145, 146) solid 0.5px;
          }
        }
      }

    }

  }

}
</style>