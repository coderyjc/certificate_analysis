<template>
  <el-table
    :data="tableData"
    stripe
    :border="border"
    :height="height"
    :key="key"
  >
    <el-table-column
      v-for="item in columns"
      :key="item.label"
      v-bind="item"
      :label="item.label ? item.label : ''"
    >
    </el-table-column>
  </el-table>
</template>

<script>  
export default {
  props:{
    height:{
      type: Number,
      default: 550
    },
    columns: {
      type: Array,
      default: () => {
        return [
          {
            label: '日期',
            prop: 'date',
          },
          {
            label: '数量',
            prop: 'name',
          }
        ]
      }
    },
    tableData: {
      type: Array,
      default: () => {
        return [
          {
            date: 'Mon',
            name: '150',
          },
          {
            date: 'Tue',
            name: '230',
          },
          {
            date: 'Wed',
            name: '224',
          },
          {
            date: 'Thu',
            name: '218',
          },
          {
            date: 'Fri',
            name: '135',
          },
          {
            date: 'Sat',
            name: '147',
          },
          {
            date: 'Sun',
            name: '260',
          }
        ]
      }
    }    
  },
  data(){
    return {
      
    }
  },
  methods: {


  }
}
</script>

<style scoped>

</style>