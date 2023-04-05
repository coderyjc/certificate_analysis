<template>
  <div :id="id" :class="id" :style="style"></div>
</template>

<script>
export default {
  props: {
    id: String,
    style: {
      type: Object,
      default: () => {
        return {
          width: "400px",
          height: "400px"
        }
      }
    }
  },
  mounted() {
    this.render()
  },
  methods: {
    render() {
      let chart = this.$echarts.init(document.getElementById(this.id));
      var colorList = ['#1bafdb', '#1690c0', '#137db0', '#116aa0', '#0a417d'];
      const option = {
        backgroundColor: '#021124',
        calculable: true,
        color: colorList,
        series: [
          {
            name: '漏斗图',
            type: 'funnel',
            left: '5%',
            right: '35%',
            top: '10%',
            bottom: '10%',
            minSize: '0%',
            maxSize: '100%',
            sort: 'ascending',
            label: {
              show: true,
              formatter: function (params) {
                return params.name + '\n' + params.value + '亿\n' + params.percent + '%';
              },
              rich: {
                a: {
                  color: 'rgba(222,240,255,1)',
                  fontSize: 30
                },
                b: {
                  color: 'rgba(101,166,196,1)',
                  fontSize: 14
                }
              }
            },
            labelLine: {
              length: 100,
              lineStyle: {
                width: 1,
                type: 'solid'
              }
            },
            data: [
              { value: 2.14, name: '1亿以上' },
              { value: 1.35, name: '500万以下' },
              { value: 0.96, name: '1000-5000万' },
              { value: 0.5, name: '5000万-1亿' },
              { value: 0.26, name: '500-1000万' },
            ]
          }
        ]
      };
      chart.setOption(option)
    }
  }
}
</script>

<style scoped></style>