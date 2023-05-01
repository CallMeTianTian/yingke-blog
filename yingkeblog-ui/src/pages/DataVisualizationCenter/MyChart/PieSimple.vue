<template>
  <div id="MyPieSimple" style="float: right;width: 600px;height: 500px;"></div>
</template>

<script>
export default {
  name: "PieSimple",
  data(){
    return {
    
    }
  },
  mounted() {
    this.initPieSimple()
  },
  methods:{
    initPieSimple(){
      this.$echarts.init(document.getElementById('MyPieSimple')).setOption({
        title: {
          text: '发布博客占比',
          subtext: '截至目前',
          left: 'center'
        },
        tooltip: {
          trigger: 'item',
          backgroundColor: 'rgba(255,255,255,0.8)',
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: '文章标签',
            type: 'pie',
            radius: '50%',
            data: [
              { name: 'Java', value: 8 },
              { name: '算法', value: 6 },
              { name: '随笔', value: 5 },
              { name: 'Vue', value: 2 },
              { name: 'Css', value: 2 },
              { name: 'Javascript', value: 1 },
              { name: '解题', value: 0 }
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      })
    }
  }
}
</script>

<style scoped>

</style>