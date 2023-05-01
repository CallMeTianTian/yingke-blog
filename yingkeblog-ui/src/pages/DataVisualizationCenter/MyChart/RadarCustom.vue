<template>
  <div style="height: 550px;width: 650px;float: left;" id="MyRadarCustom"></div>
</template>

<script>

export default {
  name: "RadarCustom",
  data(){
    return{
    
    }
  },
  mounted() {
    this.echartsInitRadarCustom()
  },
  methods:{
    echartsInitRadarCustom(){
      this.$echarts.init(document.getElementById('MyRadarCustom')).setOption({
        title: {
          text: '博客数量分析'
        },
        tooltip: {
          show: true,
          backgroundColor: 'rgba(255,255,255,0.8)',
        },
        legend: {
          data: ['Allocated Budget', 'Actual Spending']
        },
        radar: {
          // shape: 'circle',
          indicator: [
            { name: 'Java', max: 8 },
            { name: '算法', max: 6 },
            { name: '随笔', max: 5 },
            { name: 'Vue', max: 2 },
            { name: 'Css', max: 2 },
            { name: 'Javascript', max: 2 },
            { name: '解题', max: 2 }
          ]
        },
        series: [
          {
            name: '学习占比分析',
            type: 'radar',
            data: [
              {
                value: [6, 5, 3, 1, 0, 0,0],
                name: '截至目前'
              }
            ]
          }
        ]
      })
    }
  }
}
</script>

<style scoped>

</style>