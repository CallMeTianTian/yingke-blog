<template>
  <div>
    <div style="background-color: #cadbe7;border-radius: 10px;width: 300px;">
      <h3 style="text-align: center">博客数据可视化</h3>
    </div>
    <br>
    <template>
      <div>
        <el-row :gutter="20">
          <el-col :span="6">
            <div>
              <el-statistic group-separator="," :precision="2" :value="value2" :title="title"></el-statistic>
            </div>
          </el-col>
          <el-col :span="6">
            <div>
              <el-statistic title="男女比">
                <template slot="formatter"> 456/2</template>
              </el-statistic>
            </div>
          </el-col>
          <el-col :span="6">
            <div>
              <el-statistic group-separator="," :precision="2" decimal-separator="." :value="value1" :title="title">
                <template slot="prefix">
                  <i class="el-icon-s-flag" style="color: red"></i>
                </template>
                <template slot="suffix">
                  <i class="el-icon-s-flag" style="color: blue"></i>
                </template>
              </el-statistic>
            </div>
          </el-col>
          <el-col :span="6">
            <div>
              <el-statistic :value="like ? 521 : 520" title="Feedback">
                <template slot="suffix">
              <span @click="like = !like" class="like">
                <i class="el-icon-star-on" style="color:red" v-show="!!like"></i>
                <i class="el-icon-star-off" v-show="!like"></i>
              </span>
                </template>
              </el-statistic>
            </div>
          </el-col>
        </el-row>
      </div>
    </template>
    <br>
    <my-calendar-heatmap/>
    <div style="width: auto;height: 400px" id="MyBarMain">
    </div>
    <br>
    <div style="width: 700px;height: 400px;float: right;margin-right: 30px" id="main3">
    </div>
    <div style="width: 400px;height: 400px;float: left" id="main2">
    </div>
    <div style="clear: both"></div>
    <br>
  
  
  </div>
</template>

<script>
import MyCalendarHeatmap from "@/pages/DataVisualizationCenter/MyChart/CalendarHeatmap.vue";

export default {
  name: "BlogChart",
  data() {
    return {
      like: true,
      value1: 4154.564,
      value2: 2222,
      title: '今年观看数量的增长',
    }
  },
  components: {
    MyCalendarHeatmap,
    
  },
  mounted() {
    // 在通过mounted调用即可
    this.echartsInitPie();
    this.echartsInitLine();
    this.echartsInitBar();
  },
  methods: {
    //初始化echarts
    echartsInitBar() {
      this.$echarts.init(document.getElementById('MyBarMain')).setOption({
        color: ['#3398DB'],
        xAxis: {
          type: 'category',
          data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
          type: 'value'
        },
        tooltip: {
          trigger: 'item',
          backgroundColor: 'rgba(255,255,255,0.8)',
        },
        series: [{
          data: [120, 200, 150, 80, 70, 110, 130],
          type: 'bar',
          showBackground: true,
          backgroundStyle: {
            color: 'rgb(215,213,213)'
          }
        }]
      })
    },
    echartsInitPie() {
      this.$echarts.init(document.getElementById('main2')).setOption({
        tooltip: {
          trigger: 'item',
        },
        legend: {
          top: '5%',
          left: 'center'
        },
        series: [
          {
            name: '文章类型&数量',
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: '#fff',
              borderWidth: 2
            },
            label: {
              show: false,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: 40,
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data: [
              {value: 1048, name: 'Java'},
              {value: 735, name: '算法'},
              {value: 580, name: '随笔'},
              {value: 484, name: 'Javascript'},
              {value: 300, name: 'python'}
            ]
          }
        ]
      })
    },
    echartsInitLine() {
      this.$echarts.init(document.getElementById('main3')).setOption({
        title: {
          text: '观看人数'
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ['Java', '算法', '随笔', 'JavaScript', 'Python']
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: ['2月', '3月', '4月', '5月', '6月', '7月', '8月']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            name: 'Java',
            type: 'line',
            stack: 'Total',
            data: [120, 132, 101, 134, 90, 230, 210]
          },
          {
            name: '算法',
            type: 'line',
            stack: 'Total',
            data: [220, 182, 191, 234, 290, 330, 310]
          },
          {
            name: '随笔',
            type: 'line',
            stack: 'Total',
            data: [150, 232, 201, 154, 190, 330, 410]
          },
          {
            name: 'JavaScript',
            type: 'line',
            stack: 'Total',
            data: [320, 332, 301, 334, 390, 330, 320]
          },
          {
            name: 'Python',
            type: 'line',
            stack: 'Total',
            data: [820, 932, 901, 934, 1290, 1330, 1320]
          }
        ]
      })
    }
  }
}
</script>

<style scoped>
.like {
  cursor: pointer;
  font-size: 25px;
  display: inline-block;
}
</style>