<template>
  <div class="container">
    <table class="table table-striped table-hover table-bordered">
      <thead>
      <tr>
        <th scope="col"><i class="icon el-icon-s-grid"></i></th>
        <th scope="col" width="300">文章标题</th>
        <th scope="col" width="">作者</th>
        <th scope="col">标签</th>
        <th scope="col">点赞</th>
        <th scope="col">收藏</th>
        <th scope="col">发布时间</th>
      </tr>
      </thead>
      <tbody style="cursor:pointer">
      <tr v-for="(blog, index) in blog_list" :key="index" @click="goto(blog.blogId)">
        <th scope="row">{{ index + 1 }}</th>
        <td>{{ blog.title }}</td>
        <td>{{ blog.username }}</td>
        <td>{{ blog.label }}</td>
        <td>{{ blog.star }}</td>
        <td>{{ blog.collection }}</td>
        <td>{{ blog.releaseTime }}</td>
      </tr>
      </tbody>
    </table>
    
    <div class="alert alert-light" v-show="blog_list.length === 0">
      <el-empty description="没有查询到数据"></el-empty>
    </div>
  </div>
</template>

<script>
import {SearchByKeyWord} from "@/api/blog/blogApi";

export default {
  name: "SearchResultListShow",
  data() {
    return {
      blog_list: []
    }
  },
  created() {
    let keyWord = this.$route.query.keyWord
    console.log(keyWord)
    if (keyWord === '' || keyWord === undefined) {
      return
    }
    this.SearchByKeyWord(keyWord)
  },
  methods: {
    goto(blogId) {
      let routeData = this.$router.resolve({
        path: "/showcase-the-article",
        query: {
          blogId
        }
      });
      window.open(routeData.href, '_blank');
    },
    SearchByKeyWord(keyWord) {
      SearchByKeyWord(keyWord).then((res) => {
        if (res.data.code === 200) {
          console.log(res.data.data)
          if (res.data.data.length === 0) {
            console.log("文章不存在");
          } else {
            this.blog_list = res.data.data
          }
        }
      })
    }
  }
}
</script>

<style scoped>

</style>