<template>
  <div id="multipie-basic" class="multipie-basic" :style="style"></div>
</template>

<script>
export default {
  data() {
    return {
      style: {
        width: "500px",
        height: "250px"
      }
    }
  },
  mounted() {
    this.render()
  },
  methods: {
    render() {
      let chart = this.$echarts.init(document.getElementById("multipie-basic"));

      var data = [
        {
          name: '国考',
          value: 100,
          num: 1234,
        },
        {
          name: '师范类专业',
          value: 50,
          num: 5678,
        },
        {
          name: '省级机构',
          value: 49,
          num: 9012,
        },
      ];

      var titleArr = [], seriesArr = [];
      var colors = [
        ['#5B8FF9', '#E9EEF4'],
        ['#5AD8A6', '#E9EEF4'],
        ['#FFFF80', '#E9EEF4'],
      ];
      data.forEach(function (item, index) {
        titleArr.push({
          text: item.num + '\n' + item.name,
          left: index * 25 + 10 + '%',
          top: '75%',
          textAlign: 'center',
          textStyle: {
            fontWeight: 'normal',
            fontSize: '16',
            color: '#fff',
            textAlign: 'center',
          },
        });
        seriesArr.push({
          name: item.name,
          type: 'pie',
          clockWise: false,
          radius: [60, 70],
          itemStyle: {
            normal: {
              color: colors[index][0],
              shadowColor: colors[index][0],
              shadowBlur: 0,
              label: {
                show: false,
              },
              labelLine: {
                show: false,
              },
            },
          },
          hoverAnimation: false,
          center: [index * 25 + 10 + '%', '40%'],
          data: [
            {
              value: item.value,
              label: {
                normal: {
                  formatter: function (params) {
                    return params.value + '%';
                  },
                  position: 'center',
                  show: true,
                  textStyle: {
                    fontSize: '20',
                    fontWeight: 'bold',
                    color: '#fff',
                  },
                },
              },
            },
            {
              value: 100 - item.value,
              name: 'invisible',
              itemStyle: {
                normal: {
                  color: colors[index][1],
                },
                emphasis: {
                  color: colors[index][1],
                },
              },
            },
          ],
        });
      });

      const option = {
        backgroundColor: '#021124',
        title: titleArr,
        series: seriesArr,
      };

      chart.setOption(option)
    }
  }
}
</script>

<style scoped></style>