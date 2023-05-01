<template>
  <div style="float: left">
    
    <el-menu default-active="1-4-1"
             class="el-menu-vertical-demo"
             :collapse="isCollapse" style="border: #cbc7c7 2px  solid">
      
      <router-link :to="{name:'user-article'}" class="text-none">
      <el-menu-item index="1">
        <i class="el-icon-reading"></i>
      </el-menu-item>
    </router-link>
      
      <router-link :to="{name:'write-article'}">
        <el-menu-item index="2">
          <i class="el-icon-tickets"></i>
        </el-menu-item>
      </router-link>
    
    </el-menu>
  
  
  </div>
</template>

<script>
export default {
  name: "NavMenu",
  data() {
    return {
      isCollapse: true
    };
  },
  methods: {
  
  }
}
</script>

<style scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
</style>