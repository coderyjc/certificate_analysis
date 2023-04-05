<template>
  <div id="circle-ring" class="circle-ring" :style="style"></div>
</template>

<script>
export default {
  data() {
    return {
      style: {
        width: "500px",
        height: "400px"
      }
    }
  },
  mounted() {
    this.render()
  },
  methods: {
    render() {
      let chart = this.$echarts.init(document.getElementById("circle-ring"));
      var trafficWay = [
        {
          name: '高校',
          value: 20,
        },
        {
          name: '小学',
          value: 20,
        },
        {
          name: '初中',
          value: 20,
        },
        {
          name: '高中',
          value: 20,
        },
        { name: '幼儿园', value: 20 },
        { name: '中职', value: 20 },
      ];

      var data = [];
      var color = ['#fd566a', '#9787ff', '#fdb36a', '#fdd56a', '#6da7ff', '#63e1f2', '#ff3000'];
      for (var i = 0; i < trafficWay.length; i++) {
        data.push(
          {
            value: trafficWay[i].value,
            name: trafficWay[i].name,
            itemStyle: {
              normal: {
                borderWidth: 5,
                shadowBlur: 20,
                borderColor: color[i],
                shadowColor: color[i],
              },
            },
          },
          {
            value: 2,
            name: '',
            itemStyle: {
              normal: {
                label: {
                  show: false,
                },
                labelLine: {
                  show: false,
                },
                color: 'rgba(0, 0, 0, 0)',
                borderColor: 'rgba(0, 0, 0, 0)',
                borderWidth: 0,
              },
            },
          }
        );
      }
      var seriesOption = [
        {
          name: '',
          type: 'pie',
          clockWise: false,
          radius: [105, 109],
          hoverAnimation: false,
          itemStyle: {
            normal: {
              label: {
                show: true,
                position: 'outside',

                formatter: function (params) {
                  var percent = 0;
                  var total = 0;
                  for (var i = 0; i < trafficWay.length; i++) {
                    total += trafficWay[i].value;
                  }
                  percent = ((params.value / total) * 100).toFixed(0);
                  if (params.name !== '') {
                    return params.name + '\t' + percent + '%';
                  } else {
                    return '';
                  }
                },
              },
              labelLine: {
                length: 10,
                length2: 20,
                show: true,
                color: '#00ffff',
              },
            },
          },
          data: data,
        },
      ];
      const option = {
        backgroundColor: '#021124',
        color: color,
        title: [{
          text: '资格种类',
          top: '44%',
          textAlign: 'center',
          left: '49.50%',
          backgroundColor: '#163253',
          borderRadius: 100,
          textStyle: {
            color: '#fff',
            fontSize: 20,
            fontWeight: '400',
          },
        }, {
          text: '水环境监测站',
          top: '49%',
          textAlign: 'center',
          left: '49.50%',
          textStyle: {
            color: '#fff',
            fontSize: 20,
            fontWeight: '400',
          },
        }, {
          text: '9',
          top: '53%',
          textAlign: 'center',
          left: '48%',
          textStyle: {
            color: '#f6ea2f',
            fontSize: 25,
            fontWeight: '800',
            fontStyle: 'italic'
          },
        }, {
          text: '个',
          top: '53.5%',
          textAlign: 'center',
          left: '50.5%',
          textStyle: {
            color: '#fff',
            fontSize: 16,
            fontWeight: '400',
          },
        }],
        tooltip: {
          show: false,
        },

        toolbox: {
          show: false,
        },
        series: seriesOption,
      };

      chart.setOption(option)
    }
  }
}
</script>

<style scoped></style>