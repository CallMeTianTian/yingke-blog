<template>
  <div class="container">
    <el-tabs v-model="activeName" type="border-card" @tab-click="handleClick">
      <el-tab-pane label="学习数据可视化" name="1">
        <keep-alive>
          <study-chart/>
        </keep-alive>
      </el-tab-pane>
      
      <el-tab-pane label="博客数据可视化" name="2">
        <keep-alive>
          <blog-chart/>
        </keep-alive>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import BlogChart from "@/pages/DataVisualizationCenter/BlogChart.vue";
import StudyChart from "@/pages/DataVisualizationCenter/StudyChart.vue";

export default {
  name: "DataCenter",
  data() {
    return {
      activeName: '1',
    };
  },
  components: {
    BlogChart,
    StudyChart
  },
  methods: {
    handleClick(tab) {
      switch (tab.index) {
        case '0': {
          this.$router.replace('/data-center/study-chart')
        }
          break
        case '1': {
          this.$router.replace('/data-center/blog-chart')
        }
          break
      }
    }
  }
}
</script>

<style scoped>

</style>