<template>
  <div class="container">
    <UpdateWrittenStatistic @visibilityChange="handleDialogVisible" :dialogFormVisible="dialogVisible">
    </UpdateWrittenStatistic>
    <div class="form">
      <el-form ref="table" :inline="true" :model="form" class="demo-form-inline">
        <el-form-item label="考试时间区间">
          <el-select v-model="form.start" placeholder="选择起始考试时间(包含)">
            <el-option v-for="(item, index) in formItem.years" :label="item + '年'" :value="item" :key="index" />
          </el-select>
          <el-select v-model="form.end" placeholder="选择截止考试时间(包含)">
            <el-option v-for="(item, index) in formItem.years" :label="item + '年'" :value="item" :key="index" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button @click="reset">重置</el-button>
          <el-button type="primary" @click="onSubmit">生成图表</el-button>
          <el-button type="primary" @click="handleDialogVisible">添加/修改及格分</el-button>
          <el-button type="primary" @click="reverse">转换坐标轴</el-button>
          <el-button type="primary" @click="exportResult">导出表格到Excel</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="table">
      <DataTable :columns="columns" :height="350" :tableData="tableData"></DataTable>
    </div>
    <div class="chart">
      <div ref="echarts" class="id" :style="style"></div>
    </div>
  </div>
</template>

<script>
import { ElMessage } from 'element-plus'

import { listExamYear } from '@/api/query/written'

import { listWrittenStatistic, exportStatisticWrittenScore } from '@/api/query/writtenStatistic'
import DataTable from './components/DataTable.vue'
import UpdateWrittenStatistic from './components/UpdateWrittenStatistic.vue'
import { reverse } from 'lodash'

export default {
  components: {
    DataTable,
    UpdateWrittenStatistic
  },
  data() {
    return {
      dialogVisible: false,
      formItem: {
        years: []
      },
      columns: [],
      tableData: [],
      form: {
        start: '2016',
        end: '2018',
      },
      chartData: {
        series: [],
        source: []
      },
      style: {
        width: '1200px',
        height: '500px',
      },
      map: {
        '时间': 'year',
        '教育学平均成绩': 'educationAverageScore',
        '教育学通过率': 'educationPassRate',
        '教育学及格分': 'educationPassScore',
        '教育心理学平均成绩': 'psychologyAverageScore',
        '教育心理学通过率': 'psychologyPassRate',
        '教育心理学及格分': 'psychologyPassScore',
        '职业道德修养和高等教育法规平均分数': 'ethicAverageScore',
        '职业道德和高等教育法规通过率': 'ethicPassRate',
        '职业道德和高等教育法规及格分': 'ethicPassScore',
        '总通过率': 'passRate',
      },
      map_R: {
        'year': '时间',
        'educationAverageScore': '教育学平均成绩',
        'educationPassRate': '教育学通过率',
        'educationPassScore': '教育学及格分',
        'psychologyAverageScore': '教育心理学平均成绩',
        'psychologyPassRate': '教育心理学通过率',
        'psychologyPassScore': '教育心理学及格分',
        'ethicAverageScore': '职业道德修养和高等教育法规平均分数',
        'ethicPassRate': '职业道德和高等教育法规通过率',
        'ethicPassScore': '职业道德和高等教育法规及格分',
        'passRate': '总通过率',
      }
    }
  },
  created() {
    listExamYear().then(res => {
      this.formItem.years = res.data.data
    })
  },
  mounted() {
    this.onSubmit()
  },
  methods: {
    reset() {
      this.form.start = ''
      this.form.end = ''
    },
    onSubmit() {

      // 表格校验
      if (this.form.start == '' || this.form.end == '') {
        ElMessage({
          message: '年份不能为空',
          type: 'warning',
        })
        return
      } else if (this.form.start > this.form.end) {
        ElMessage({
          message: '年份填写错误',
          type: 'warning',
        })
        return
      }

      // 生成表格中的表头
      const statisticItem = ['时间', '教育学平均成绩', '教育学通过率', '教育学及格分', '教育心理学平均成绩', '教育心理学通过率', '教育心理学及格分', '职业道德修养和高等教育法规平均分数', '职业道德和高等教育法规通过率', '职业道德和高等教育法规及格分', '总通过率']
      this.columns = []
      statisticItem.forEach(e => {
        this.columns.push({
          label: e,
          prop: this.map[e],
          width: 160,
          sortable: true,
        })
      })

      // 请求数据
      listWrittenStatistic(this.form.start, this.form.end).then(res => {
        this.tableData = []
        res.data.data.forEach(e => {
          this.tableData.push({
            'year': e.year,
            'educationAverageScore': e.educationAverageScore,
            'educationPassRate': e.educationPassRate,
            'educationPassScore': e.educationPassScore,
            'psychologyAverageScore': e.psychologyAverageScore,
            'psychologyPassRate': e.psychologyPassRate,
            'psychologyPassScore': e.psychologyPassScore,
            'ethicAverageScore': e.ethicAverageScore,
            'ethicPassRate': e.ethicPassRate,
            'ethicPassScore': e.ethicPassScore,
            'passRate': e.passRate,
          })
        })

        // 按照年份渲染柱状图
        this.chartData.source = []
        this.chartData.series = []

        if (this.tableData.length > 0) {
          for (let key in this.tableData[0]) {
            this.chartData.source.push([this.map_R[key]])
          }

          this.tableData.forEach(e => {
            this.chartData.series.push({ type: 'bar' })
            this.chartData.source[0].push(String(e.year))
            this.chartData.source[1].push(e.educationAverageScore)
            this.chartData.source[2].push(e.educationPassRate)
            this.chartData.source[3].push(e.educationPassScore)
            this.chartData.source[4].push(e.psychologyAverageScore)
            this.chartData.source[5].push(e.psychologyPassRate)
            this.chartData.source[6].push(e.psychologyPassScore)
            this.chartData.source[7].push(e.ethicAverageScore)
            this.chartData.source[8].push(e.ethicPassRate)
            this.chartData.source[9].push(e.ethicPassScore)
            this.chartData.source[10].push(e.passRate)
          })
          this.render()
        }
      })

    },
    generateTitle() {
      let title = ''
      // 时间
      this.form.start == this.form.end ? title += this.form.start + '年' : title += this.form.start + '-' + this.form.end + '年'
      title += '-'
      // 选项
      // 图标类型
      title += '笔试成绩合格率柱状图'
      return title
    },
    exportResult() {
      // 表格校验
      if (this.form.start == '' || this.form.end == '') {
        ElMessage({
          message: '年份不能为空',
          type: 'warning',
        })
        return
      } else if (this.form.start > this.form.end) {
        ElMessage({
          message: '年份填写错误',
          type: 'warning',
        })
        return
      }

      const url = exportStatisticWrittenScore(this.form.start, this.form.end)
      ElMessage({
        message: "正在开始下载",
        type: 'success'
      })

      window.open(url, "_blank")
    },
    reverse() {
      if (this.chartData.source == 0) {
        ElMessage({
          message: '请先获取数据',
          type: 'warning'
        })
        return
      }

      const series = []
      const source = []

      for (let i = 0; i < this.chartData.source.length - 1; i++) {
        series.push({ type: 'bar' })
      }

      for (let i = 0; i < this.chartData.source[0].length; i++) {
        let temp = []
        for (let j = 0; j < this.chartData.source.length; j++) {
          temp.push(this.chartData.source[j][i])
        }
        source.push(temp)
      }
      this.chartData.source = source
      this.chartData.series = series
      this.render()
    },
    render() {

      // 渲染柱状图
      let chart = this.$echarts.init(this.$refs.echarts);
      const option = {
        title: {
          text: this.generateTitle(),
        },
        legend: {},
        tooltip: {},
        dataset: {
          source: this.chartData.source
        },
        toolbox: {
          feature: {
            saveAsImage: {
              title: "下载到本地"
            }
          }
        },
        xAxis: { type: 'category' },
        yAxis: {},
        series: this.chartData.series
      };
      chart.setOption(option, {
        notMerge: true
      })
    },
    handleDialogVisible() {
      this.dialogVisible = !this.dialogVisible
    }
  }


}
</script>

<style lang="scss" scoped>
.container {
  padding: 10px;
  display: flex;
  flex-direction: column;

  .form {
    background-color: #fff;
    border-radius: 5px;
    box-shadow: $globalShadow;
    padding: 15px 20px;
    margin-bottom: 10px;

  }

  .table {
    padding: 5px;
    background-color: #fff;
    border-radius: 5px;
    box-shadow: $globalShadow;
    max-width: 100%;
  }

  .chart {
    margin-top: 10px;
    background-color: #fff;
    border-radius: 5px;
    box-shadow: $globalShadow;
    max-width: 100%;
  }


  .cannot-display {
    text-align: center;
    font-size: 20px;
  }

}
</style>