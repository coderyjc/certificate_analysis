<template>
  <div class="container">
    <div class="head">
      <el-form :inline="true" :model="form">
        <el-form-item label="认定批次" v-if="!form.statisticItem.some(e => e == '认定批次')">
          <el-select v-model="form.affirmBatch" placeholder="选择年份">
            <el-option v-for="(item, index) in formItem.affirmBatchs" :label="item" :value="item" :key="index"/>
          </el-select>
        </el-form-item>
        <el-form-item label="认定批次区间" v-if="form.statisticItem.some(e => e == '认定批次')">
          <el-select v-model="form.affirmBatchStart" placeholder="选择起始时间(包含)">
            <el-option v-for="(item, index) in formItem.affirmBatchs" :label="item" :value="item" :key="index"/>
          </el-select>
          <el-select v-model="form.affirmBatchEnd" placeholder="选择截止时间(包含)">
            <el-option v-for="(item, index) in formItem.affirmBatchs" :label="item" :value="item" :key="index"/>
          </el-select>
        </el-form-item>
        <el-form-item label="统计项(有序)">
          <el-checkbox-group v-model="form.statisticItem" size="medium">
            <el-checkbox label="认定批次" border />
            <el-checkbox label="性别" border />
            <el-checkbox label="专业类别" border />
            <el-checkbox label="毕业学校" border />
            <el-checkbox label="所学专业" border />
            <el-checkbox label="最高学历" border />
            <el-checkbox label="资格种类" border />
            <el-checkbox label="最高学位" border />
            <el-checkbox label="确认点" border />
            <el-checkbox label="认定机构" border />
            <el-checkbox label="考试类型" border />
            <el-checkbox label="机构类型" border />
            <el-checkbox label="任教学科" border />
            <el-checkbox label="市" border />
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
          <el-button type="primary" @click="exportResult">导出表格到Excel</el-button>
        </el-form-item>
      </el-form>
    </div> 
    <div class="left">
      <DataTable :columns="columns" :tableData="tableData"></DataTable>
    </div>
    <div class="right">
      <div ref="echarts" v-if="tableData.length < 30 || tableData == null" id="test-chart" class="id" :style="style"></div>
      <div v-if="tableData.length >= 30" class="cannot-display" :style="style">数据过多，图片无法显示</div>
    </div>
  </div>
</template>

<script>

import { ElMessage } from 'element-plus'
import { listAffirmBatch, statisticIdentification, exportStatisticIdentification } from '@/api/query/identification'

import DataTable from './components/DataTable.vue'

export default {
  components:{
    DataTable,
  },
  data() {
    return {
      formItem: {
        affirmBatchs: [],
      },
      loading: false,
      form: {
        affirmBatch: '',
        affirmBatchStart: '',
        affirmBatchEnd: '',
        statisticItem: [],
        chartType: 'bar',
      },
      // 转换后的项目
      statisticItem:[],
      // 统计图的标题
      chartTitle: '',
      // 请求得到的原始数据，需要先处理然后进行渲染
      rawData: [], 
      // 列名
      columns: [],
      // 表格中的数据，一般来说只需要渲染一次
      tableData: [],
      style: {
        width: "850px",
        height: "550px"
      },
    }
  },
  created(){
    listAffirmBatch().then(res => {
      this.formItem.affirmBatchs = res.data.data
    })
  },
  mounted() {
    this.form.affirmBatch = '2016年秋季'
    this.form.statisticItem = ['资格种类']
    this.onSubmit()
  },
  methods: {
    reset() {
      this.form.affirmBatch = ''
      this.form.affirmBatchStart = ''
      this.form.affirmBatchEnd = ''
      this.form.statisticItem = []
    },
    validateForm(form){
      // 没有选择元素的时候
      if(form.statisticItem.length == 0) {
        ElMessage({
          message: '请先选择项目',
          type: 'error'
        })
        return false
      }
      // 有2个以上的元素的时候
      if(form.statisticItem.length > 2) {
        ElMessage({
          message: '筛选项目过多',
          type: 'error'
        })
        return false
      }
      // 没有统计多个年份对比
      if(form.affirmBatch == '' && !form.statisticItem.some(e => e == '认定批次')){
        ElMessage({
          message: '请先填写完整表单',
          type: 'error'
        })
        return false
      }
      // 统计了多个年份对比
      if(form.statisticItem.some(e => e == '认定批次')) {
        // 起始和终止的年份不能为空，且开始的年份不能小于结束的年份
        if(form.affirmBatchStart == '' || form.affirmBatchEnd == '' || form.affirmBatchStart > form.affirmBatchEnd){
          ElMessage({
            message: '认定批次填写有误',
            type: 'error'
          })
          return false
        }
        // 年份不能作为单独的统计项
        if(form.statisticItem.length != 2){
          ElMessage({
            message: '年份不能作为单独的统计项',
            type: 'error'
          })
          return false
        }
        form.affirmBatch = ''
      }
      return true
    },
    onSubmit() {

      // 表格校验
      if(!this.validateForm(this.form)) return

      // 生成本次的标题
      this.chartTitle = this.generateTitle()

      // 整理label, 方便在数据库中进行查找
      const map = {
        '认定批次': 'affirmBatch',
        '性别': 'gender',
        '专业类别': 'majorType',
        '毕业学校': 'graduationSchool',
        '所学专业': 'major',
        '最高学历': 'highestEducationBackground',
        '资格种类': 'qualificationType',
        '最高学位': 'highestDegree',
        '确认点': 'confirmAddress',
        '认定机构': 'affirmInstitution',
        '考试类型': 'examType',
        '机构类型': 'organizationType',
        '任教学科': 'subject',
        '市': 'city',
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
      
      statisticIdentification({
        affirmBatch: this.form.affirmBatch,
        affirmBatchStart: this.form.affirmBatchStart,
        affirmBatchEnd: this.form.affirmBatchEnd,
        statisticItem: this.statisticItem
      }).then(res => {

        // 保存原始数据
        this.rawData = res.data.data
        
        // 把01转换成男和女
        if(this.rawData[0].gender == 0 || this.rawData[0].gender == 1){
          // 筛选了性别这一列
          this.rawData.forEach(ele => {
            ele.gender = ele.gender ? "男" : "女"
          })
        }
        
        // 生成表格数据
        this.tableData = []
        this.rawData.forEach(e => {
          const temp = {}
          if(e.affirmBatch != null) temp['affirmBatch'] = e.affirmBatch
          if(e.gender != null) temp['gender'] = e.gender
          if(e.majorType != null) temp['majorType'] = e.majorType
          if(e.graduationSchool != null) temp['graduationSchool'] = e.graduationSchool
          if(e.major != null) temp['major'] = e.major
          if(e.highestEducationBackground != null) temp['highestEducationBackground'] = e.highestEducationBackground
          if(e.qualificationType != null) temp['qualificationType'] = e.qualificationType
          if(e.highestDegree != null) temp['highestDegree'] = e.highestDegree
          if(e.confirmAddress != null) temp['confirmAddress'] = e.confirmAddress
          if(e.affirmInstitution != null) temp['affirmInstitution'] = e.affirmInstitution
          if(e.examType != null) temp['examType'] = e.examType
          if(e.organizationType != null) temp['organizationType'] = e.organizationType
          if(e.subject != null) temp['subject'] = e.subject
          if(e.city != null) temp['city'] = e.city
          temp['count'] = e.count
          this.tableData.push(temp)
        })

        // 单个柱状图 -- 选择了某一年份的一项统计
        if(this.form.chartType == 'bar' && this.form.affirmBatch != ''){
          this.renderBar()
        }
        // 多个柱状图
        if((this.form.chartType == 'bar' && this.form.affirmBatch == '') || this.form.statisticItem.length == 2){
          this.renderMultiBar()
        }
        // 饼图
        if(this.form.chartType == 'pie' && this.form.affirmBatch != ''){
          this.renderPie()
        }

        // 多个饼图
        if(this.form.chartType == 'pie' && this.form.affirmBatch == ''){
          ElMessage({
            message: "暂不支持",
            type: "warning"
          })
          this.form.chartType == 'bar'
        }
      })

    },
    generateTitle(){
      let title = ''
      // 时间
      this.form.affirmBatch != '' ? title += this.form.affirmBatch : title += this.form.affirmBatchStart + '-' + this.form.affirmBatchEnd
      title += '-'
      // 选项
      this.form.statisticItem.forEach(e => {
        title +=  e + '-'
      })
      // 图标类型
      this.form.chartType == 'pie' ? title += '饼图' : title += '柱状图'
      return title
    },
    exportResult() {
      if(!this.validateForm(this.form)) return

      const url = exportStatisticIdentification({
        affirmBatch: this.form.affirmBatch,
        affirmBatchStart: this.form.affirmBatchStart,
        affirmBatchEnd: this.form.affirmBatchEnd,
        statisticItem: this.statisticItem
      })
      ElMessage({
        message: "正在开始下载",
        type: 'success'
      })

      window.open(url,"_blank")
    },
    handleCheckboxGoupChange(){
      // 删除所有英文和未定义的
      var pattern = new RegExp("[a-zA-Z]+")
      this.form.statisticItem = this.form.statisticItem.filter(e => {
        const rst = e.match(pattern) || e == undefined || e == null
        return !rst
      })
    },
    renderPie(){
      // 整合数据
      const data = []
      this.tableData.map(ele => {
        let _name;
        let _value;
        for(const [key, value] of Object.entries(ele)){
          if(key == 'count') _value = value;
          else _name = value
        }

        data.push({
          name: _name,
          value: _value
        })
      })

      // 渲染饼图
      let chart = this.$echarts.init(this.$refs.echarts);

      const option = {
        title: {
          text: this.chartTitle,
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left',
          top: '20px'
        },
        toolbox: {
          feature: {
            saveAsImage: {
              title: "下载到本地"
            }
          }
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
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      };
      chart.setOption(option, {
        notMerge: true
      })
    },
    renderBar(){
      // 整合数据
      const XData = []
      const YData = []
      this.tableData.map(ele => {
        for(const [key, value] of Object.entries(ele)){
          if(key == 'count') YData.push(value);
          else XData.push(value)
        }
      })

      // 渲染柱状图
      let chart = this.$echarts.init(this.$refs.echarts);
      const option = {
        title: {
          text: this.chartTitle,
        },
        xAxis: {
          type: 'category',
          data: XData
        },
        yAxis: {
          type: 'value'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        toolbox: {
          feature: {
            saveAsImage: {
              title: "下载到本地"
            }
          }
        },
        series: [
          {
            data: YData,
            type: 'bar'
          }
        ]
      };
      chart.setOption(option, {
        notMerge: true
      })
    },
    renderMultiBar(){
      // 渲染多层柱状图
      // 直接把tableData整理rawData
      let rawData = []
      let hasDate = this.statisticItem.some(e => e == 'examDate')
      let column2_name = Object.keys(this.tableData[0]).filter(e => e != 'examDate' && e != 'count')
      if(hasDate) {
        this.tableData.forEach(e => {
          rawData.push({
            column1: e['examDate'],
            column2: e[column2_name],
            count: e['count']
          })
        })
      } else {
        this.tableData.forEach(e => {
          rawData.push({
            column1: e[this.statisticItem[0]],
            column2: e[this.statisticItem[1]],
            count: e['count']
          })
        })
      }
      // 整合数据
      const series = []
      // column1一般来说是date
      const column1 = ['item']
      const column2 = []
      rawData.map(ele => {
        if(!column1.some(x => x == ele.column1)) {
          column1.push(ele.column1)
          series.push({type: 'bar'})
        }
        if(!column2.some(x => x.some(y => y == ele.column2)))
          column2.push([ele.column2, ele.count])
        else{
          column2.forEach(e => {
            if(e.some(x => x == ele.column2)) e.push(ele.count)
          })
        }
      })
      const dataset = [column1, ...column2]

      // 渲染柱状图
      let chart = this.$echarts.init(this.$refs.echarts);
      const option = {
        title: {
          text: this.chartTitle,
        },
        legend: {
          orient: 'horizontal',
          right: '30px',
        },
        tooltip: {},
        dataset: {
          source: dataset
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
        series: series
      };
      chart.setOption(option, {
        notMerge: true
      })
    },
    // 将vue的对象转化为json对象输出
    log(message){
      console.log(JSON.parse(JSON.stringify(message)))
    }
  }
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

  .cannot-display{
    text-align: center;
    font-size: 20px;
  }
}
</style>