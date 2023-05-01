<template>
  <div>
    <h3>我的收藏</h3>
    <br>
    <div class="card mb-3" style="cursor: pointer" v-for="mc in myCollects" :key="mc.blogId">
      <div class="card-body">
        <h5 class="card-title" @click="gotoBlog(mc.blogId)">✨{{mc.title}}</h5>
        <el-tag>📰 {{mc.label}}</el-tag>
        <el-tag type="success">❤️ {{mc.star}}</el-tag>
        <el-tag type="info">💫 {{mc.collection}}</el-tag>
        <a @click="gotoUserInfo(mc.uid)" class="card-link mx-3">作者: {{mc.username}}</a>
      </div>
    </div>
  </div>
</template>

<script>
import {getMyCollects} from "@/api/user/collect";

export default {
  name: "MyCollect",
  data() {
    return {
      myCollects:[]
    }
  },
  created() {
    getMyCollects().then((res) => {
      this.myCollects = res.data.data
    })
  },
  methods:{
    gotoUserInfo(uid){
      console.log(uid)
      let routeData = this.$router.resolve({
        path: "/user",
        query: {
          uid
        }
      });
      window.open(routeData.href, '_blank');
    },
    gotoBlog(blogId) {
      let routeData = this.$router.resolve({
        path: "/showcase-the-article",
        query: {
          blogId
        }
      });
      window.open(routeData.href, '_blank');
    }
  }
}
</script>

<style scoped>

</style>