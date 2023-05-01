<template>
  <div>
    <br>
    <div style="height:400px;width: auto;" id="MyWordCloud"></div>
  </div>
</template>


<script>
import "echarts-wordcloud/dist/echarts-wordcloud";
import "echarts-wordcloud/dist/echarts-wordcloud.min";
import {getCourseWordCountByUid} from '@/api/course/course'

export default {
  name: "MyWordCloud",
  data() {
    return {
      uid: '',
      keyWordData: [],
      // 模拟数据
      defaultData: [{'name': '莫得数据', 'value': 666666}]
    };
  },
  mounted() {
    let that = this
    
    setTimeout(() => {
      that.showEeharts();
    }, 1500)
  },
  
  created() {
    let userInfo = JSON.parse(localStorage.getItem("userInfo"));
    this.uid = userInfo.uid
    getCourseWordCountByUid(this.uid).then(res => {
      this.keyWordData = res.data.data
    })
  },
  
  methods: {
    showEeharts() {
      this.$echarts.init(document.getElementById('MyWordCloud')).setOption({
        title: {
          text: '学习关键词分析',//标题
          x: 'top',
          textStyle: {
            fontSize: 30
          }
        },
        backgroundColor: '#F7F7F7',
        tooltip: {
          show: true
        },
        series: [{
          name: '学习词频统计',//数据提示窗标题
          type: 'wordCloud',
          shape: 'circle',
          sizeRange: [30, 60],//画布范围，如果设置太大会出现少词（溢出屏幕）
          rotationRange: [-45, 90],//数据翻转范围
          //shape: 'circle',
          textPadding: 0,
          layoutAnimation: true,
          drawOutOfBound: true,
          autoSize: {
            enable: true,
            minSize: 6
          },
          textStyle: {
            fontFamily: 'sans-serif',
            fontWeight: 'bold',
            
            color: function () {
              return 'rgb(' + [
                Math.round(Math.random() * 160),
                Math.round(Math.random() * 160),
                Math.round(Math.random() * 160)
              ].join(',') + ')';
            },
            
            emphasis: {
              shadowBlur: 10,
              shadowColor: '#333'
            }
          },
          data: this.keyWordData || this.defaultData
        }]
      });
    },
  },
};
</script>
<style lang="less">

</style>