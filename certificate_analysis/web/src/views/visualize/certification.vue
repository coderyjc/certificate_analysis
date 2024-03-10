<template>
  <div class="container">
    <div class="head">
      <el-form :inline="true" :model="form" class="demo-form-inline">
        <el-form-item
          label="面试年份"
          v-if="!form.statisticItem.some(e => e == '面试年份')"
        >
          <el-select v-model="form.interviewYear" placeholder="选择年份">
            <el-option
              v-for="(item, index) in formItem.interviewYear"
              :label="item + '年'"
              :value="item"
              :key="index"
            />
          </el-select>
        </el-form-item>
        <el-form-item
          label="面试年份区间"
          v-if="form.statisticItem.some(e => e == '面试年份')"
        >
          <el-select
            v-model="form.interviewStartYear"
            placeholder="选择起始年份(包含)"
          >
            <el-option
              v-for="(item, index) in formItem.interviewYear"
              :label="item + '年'"
              :value="item"
              :key="index"
            />
          </el-select>
          <el-select
            v-model="form.interviewEndYear"
            placeholder="选择截止年份(包含)"
          >
            <el-option
              v-for="(item, index) in formItem.interviewYear"
              :label="item + '年'"
              :value="item"
              :key="index"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="统计项(有序)">
          <el-checkbox-group v-model="form.statisticItem" size="medium">
            <el-checkbox label="面试年份" border />
            <el-checkbox label="有效期" border />
            <el-checkbox label="专业" border />
            <el-checkbox label="性别" border />
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="图表类型">
          <el-select v-model="form.chartType" placeholder="图表类型">
            <el-option label="饼图" value="pie" />
            <el-option label="柱状图" value="bar" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button @click="reset">重置</el-button>
          <el-button type="primary" @click="onSubmit">生成图表</el-button>
          <el-button type="primary" @click="exportResult">
            导出表格到Excel
          </el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="left">
      <DataTable :columns="columns" :tableData="tableData"></DataTable>
    </div>
    <div class="right">
      <div
        ref="echarts"
        v-if="tableData.length < 30 || tableData == null"
        id="test-chart"
        class="id"
        :style="style"
      ></div>
      <div v-if="tableData.length >= 30" class="cannot-display" :style="style">
        数据过多，图片无法显示
      </div>
    </div>
  </div>
</template>

<script>
import { ElMessage } from 'element-plus'
import { dateFormatYMD } from '@/utils/timeUtil'
import {
  listInterviewYear,
  statisticCertification,
  exportStatisticCertification,
} from '@/api/query/certification'

import DataTable from './components/DataTable.vue'

export default {
  components: {
    DataTable,
  },
  data() {
    return {
      formItem: {
        interviewYear: [],
      },
      loading: false,
      form: {
        interviewYear: '',
        interviewStartYear: '',
        interviewEndYear: '',
        statisticItem: [],
        chartType: 'bar',
      },
      // 转换后的项目
      statisticItem: [],
      // 统计图的标题
      chartTitle: '',
      // 请求得到的原始数据，需要先处理然后进行渲染
      rawData: [],
      // 列名
      columns: [],
      // 表格中的数据，一般来说只需要渲染一次
      tableData: [],
      style: {
        width: '850px',
        height: '550px',
      },
    }
  },
  created() {
    listInterviewYear().then(res => {
      this.formItem.interviewYear = res.data.data
    })
  },
  mounted() {
    this.form.interviewYear = '2018'
    this.form.statisticItem = ['性别']
    this.onSubmit()
  },
  methods: {
    reset() {
      this.form.interviewYear = ''
      this.form.interviewStartYear = ''
      this.form.interviewEndYear = ''
      this.form.statisticItem = []
    },
    validateForm(form) {
      // 没有选择元素的时候
      if (form.statisticItem.length == 0) {
        ElMessage({
          message: '请先选择项目',
          type: 'error',
        })
        return false
      }
      // 有2个以上的元素的时候
      if (form.statisticItem.length > 2) {
        ElMessage({
          message: '筛选项目过多',
          type: 'error',
        })
        return false
      }
      // 没有统计多个年份对比
      if (
        form.interviewYear == '' &&
        !form.statisticItem.some(e => e == '面试年份')
      ) {
        ElMessage({
          message: '请先填写完整表单',
          type: 'error',
        })
        return false
      }
      // 统计了多个年份对比
      if (form.statisticItem.some(e => e == '面试年份')) {
        // 起始和终止的年份不能为空，且开始的年份不能小于结束的年份
        if (
          form.interviewStartYear == '' ||
          form.interviewEndYear == '' ||
          form.interviewStartYear > form.interviewEndYear
        ) {
          ElMessage({
            message: '面试年份填写有误',
            type: 'error',
          })
          return false
        }
        // 年份不能作为单独的统计项
        if (form.statisticItem.length != 2) {
          ElMessage({
            message: '年份不能作为单独的统计项',
            type: 'error',
          })
          return false
        }
        form.interviewYear = ''
      }
      return true
    },
    onSubmit() {
      // 表格校验
      if (!this.validateForm(this.form)) return

      // 生成本次的标题
      this.chartTitle = this.generateTitle()

      // 整理label, 方便在数据库中进行查找
      const map = {
        面试年份: 'interviewYear',
        有效期: 'validateDate',
        性别: 'gender',
        专业: 'major',
      }

      // 生成表格中的表头
      this.columns = []
      this.form.statisticItem.forEach(e => {
        this.columns.push({
          label: e,
          prop: map[e],
          width: 150,
          sortable: true,
        })
      })
      this.columns.push({
        label: '数量',
        prop: 'count',
        sortable: true,
      })

      // 将选择的字段变化为数据库中的字段
      this.statisticItem = this.form.statisticItem.map(ele => map[ele])

      statisticCertification({
        interviewYear: this.form.interviewYear,
        interviewStartYear: this.form.interviewStartYear,
        interviewEndYear: this.form.interviewEndYear,
        statisticItem: this.statisticItem,
      }).then(res => {
        // 保存原始数据
        this.rawData = res.data.data

        // 把01转换成男和女
        if (this.rawData[0].gender == 0 || this.rawData[0].gender == 1) {
          // 筛选了性别这一列
          this.rawData.forEach(ele => {
            ele.gender = ele.gender ? '男' : '女'
          })
        }
        // 把有效期转换成日期格式
        if (this.rawData[0].validateDate != null) {
          this.rawData.forEach(ele => {
            ele.validateDate = dateFormatYMD(ele.validateDate)
          })
        }

        // 生成表格数据
        this.tableData = []
        this.rawData.forEach(e => {
          const temp = {}
          if (e.interviewYear != null) temp['interviewYear'] = e.interviewYear
          if (e.validateDate != null) temp['validateDate'] = e.validateDate
          if (e.gender != null) temp['gender'] = e.gender
          if (e.major != null) temp['major'] = e.major
          temp['count'] = e.count
          this.tableData.push(temp)
        })

        // 单个柱状图 -- 选择了某一年份的一项统计
        if (this.form.chartType == 'bar' && this.form.interviewYear != '') {
          this.renderBar()
        }
        // 多个柱状图
        if (
          (this.form.chartType == 'bar' && this.form.interviewYear == '') ||
          this.form.statisticItem.length == 2
        ) {
          this.renderMultiBar()
        }
        // 饼图
        if (this.form.chartType == 'pie' && this.form.interviewYear != '') {
          this.renderPie()
        }

        // 多个饼图
        if (this.form.chartType == 'pie' && this.form.interviewYear == '') {
          ElMessage({
            message: '暂不支持',
            type: 'warning',
          })
          this.form.chartType == 'bar'
        }
      })
    },
    generateTitle() {
      let title = ''
      // 时间
      this.form.interviewYear != ''
        ? (title += this.form.interviewYear + '年')
        : (title +=
            this.form.interviewStartYear +
            '-' +
            this.form.interviewEndYear +
            '年')
      title += '-'
      // 选项
      this.form.statisticItem.forEach(e => {
        title += e + '-'
      })
      // 图标类型
      this.form.chartType == 'pie' ? (title += '饼图') : (title += '柱状图')
      return title
    },
    exportResult() {
      if (!this.validateForm(this.form)) return

      const url = exportStatisticCertification({
        interviewYear: this.form.interviewYear,
        interviewStartYear: this.form.interviewStartYear,
        interviewEndYear: this.form.interviewEndYear,
        statisticItem: this.statisticItem,
      })
      ElMessage({
        message: '正在开始下载',
        type: 'success',
      })

      window.open(url, '_blank')
    },
    handleCheckboxGoupChange() {
      // 删除所有英文和未定义的
      var pattern = new RegExp('[a-zA-Z]+')
      this.form.statisticItem = this.form.statisticItem.filter(e => {
        const rst = e.match(pattern) || e == undefined || e == null
        return !rst
      })
    },
    renderPie() {
      // 整合数据
      const data = []
      this.tableData.map(ele => {
        let _name
        let _value
        for (const [key, value] of Object.entries(ele)) {
          if (key == 'count') _value = value
          else _name = value
        }

        data.push({
          name: _name,
          value: _value,
        })
      })

      // 渲染饼图
      let chart = this.$echarts.init(this.$refs.echarts)

      const option = {
        title: {
          text: this.chartTitle,
        },
        tooltip: {
          trigger: 'item',
        },
        legend: {
          orient: 'vertical',
          left: 'left',
          top: '20px',
        },
        toolbox: {
          feature: {
            saveAsImage: {
              title: '下载到本地',
            },
          },
        },
        series: [
          {
            name: 'Access From',
            type: 'pie',
            radius: '50%',
            data: data,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)',
              },
            },
            label: {
              show: true,
              formatter(param) {
                // correct the percentage
                return (
                  param.name + ' : ' + param.value + ' (' + param.percent + '%)'
                )
              },
            },
          },
        ],
      }
      chart.setOption(option, {
        notMerge: true,
      })
    },
    renderBar() {
      // 整合数据
      const XData = []
      const YData = []
      this.tableData.map(ele => {
        for (const [key, value] of Object.entries(ele)) {
          if (key == 'count') YData.push(value)
          else XData.push(value)
        }
      })

      // 渲染柱状图
      let chart = this.$echarts.init(this.$refs.echarts)
      const option = {
        title: {
          text: this.chartTitle,
        },
        xAxis: {
          type: 'category',
          data: XData,
        },
        yAxis: {
          type: 'value',
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow',
          },
        },
        toolbox: {
          feature: {
            saveAsImage: {
              title: '下载到本地',
            },
          },
        },
        series: [
          {
            data: YData,
            type: 'bar',
            label: {
              show: true,
              position: 'top',
            },
          },
        ],
      }
      chart.setOption(option, {
        notMerge: true,
      })
    },
    renderMultiBar() {
      // 渲染多层柱状图
      // 直接把tableData整理rawData
      let rawData = []
      let hasDate = this.statisticItem.some(e => e == 'examDate')
      let column2_name = Object.keys(this.tableData[0]).filter(
        e => e != 'examDate' && e != 'count'
      )
      if (hasDate) {
        this.tableData.forEach(e => {
          rawData.push({
            column1: e['examDate'],
            column2: e[column2_name],
            count: e['count'],
          })
        })
      } else {
        this.tableData.forEach(e => {
          rawData.push({
            column1: e[this.statisticItem[0]],
            column2: e[this.statisticItem[1]],
            count: e['count'],
          })
        })
      }
      // 整合数据
      const series = []
      // column1一般来说是date
      const column1 = ['item']
      const column2 = []
      rawData.map(ele => {
        if (!column1.some(x => x == ele.column1)) {
          column1.push(ele.column1)
          series.push({
            type: 'bar',
            label: {
              show: true,
              position: 'top',
            },
          })
        }
        if (!column2.some(x => x.some(y => y == ele.column2)))
          column2.push([ele.column2, ele.count])
        else {
          column2.forEach(e => {
            if (e.some(x => x == ele.column2)) e.push(ele.count)
          })
        }
      })
      const dataset = [column1, ...column2]

      // 渲染柱状图
      let chart = this.$echarts.init(this.$refs.echarts)
      const option = {
        title: {
          text: this.chartTitle,
        },
        legend: {
          orient: 'horizontal',
          top: '20px',
          right: '30px',
        },
        tooltip: {},
        dataset: {
          source: dataset,
        },
        toolbox: {
          feature: {
            saveAsImage: {
              title: '下载到本地',
            },
          },
        },
        xAxis: { type: 'category' },
        yAxis: {},
        series: series,
      }
      chart.setOption(option, {
        notMerge: true,
      })
    },
    // 将vue的对象转化为json对象输出
    log(message) {
      console.log(JSON.parse(JSON.stringify(message)))
    },
  },
}
</script>

<style lang="scss" scoped>
.container {
  padding: 10px;
  display: grid;
  grid-template-columns: 10;
  grid-template-rows: 8;

  .head {
    background-color: #fff;
    border-radius: 5px;
    box-shadow: $globalShadow;
    padding: 30px 20px;

    grid-column-start: 1;
    grid-column-end: 11;
    grid-row-start: 1;
    grid-row-end: 3;
  }

  .left {
    margin-top: 10px;
    padding: 5px;
    background-color: #fff;
    border-radius: 5px;
    box-shadow: $globalShadow;
  }

  .right {
    margin-top: 10px;
    margin-left: 10px;
    background-color: #fff;
    border-radius: 5px;
    box-shadow: $globalShadow;
  }

  .cannot-display {
    text-align: center;
    font-size: 20px;
  }
}
</style>
