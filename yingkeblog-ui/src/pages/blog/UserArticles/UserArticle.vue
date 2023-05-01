<template>
  <div class="container">
    
    <div class="container-people bg-light" style="position: relative">
      <!--float: right-->
      <el-card shadow="hover" class="box-card"
               style="width: 230px;height: 540px;margin-right: 20px;position: absolute;top: 20px;right: 0">
        <i class="el-icon-menu"></i>
        <h6 style="display: inline;margin-left: 10px;cursor: pointer" @click="getAll">文章分类</h6>
        <ul style="padding: 0;margin: 0">
          
          <li @click="GetBlogByLabel('java')">
            <el-tag class="mb-1 mt-1">Java</el-tag>
            <span class="badge text-bg-secondary count mt-2">{{ labelList[0].java }}</span>
          </li>
          
          <li @click="GetBlogByLabel('algorithm')">
            <el-tag type="success" class="mb-1 mt-1">Algorithm</el-tag>
            <span class="badge text-bg-secondary count">{{ labelList[0].algorithm }}</span>
          </li>
          
          <li @click="GetBlogByLabel('essay')">
            <el-tag type="info" class="mb-1 mt-1">Essay</el-tag>
            <span class="badge text-bg-secondary count">{{ labelList[0].essay }}</span>
          </li>
          
          <li @click="GetBlogByLabel('vue')">
            <el-tag type="warning" class="mb-1 mt-1">Vue</el-tag>
            <span class="badge text-bg-secondary count">{{ labelList[0].vue }}</span>
          </li>
          
          <li @click="GetBlogByLabel('css')">
            <el-tag type="danger" class="mb-1 mt-1">Css</el-tag>
            <span class="badge text-bg-secondary count">{{ labelList[0].css }}</span>
          </li>
          
          <li @click="GetBlogByLabel('python')">
            <el-tag type="warning" class="mb-1 mt-1">Python</el-tag>
            <span class="badge text-bg-secondary count">{{ labelList[0].python }}</span>
          </li>
          
          <li @click="GetBlogByLabel('javascript')">
            <el-tag type="success" class="mb-1 mt-1">JavaScript</el-tag>
            <span class="badge text-bg-secondary count">{{ labelList[0].javascript }}</span>
          </li>
  
          <li @click="GetBlogByLabel('javascript')">
            <el-tag type="success" class="mb-1 mt-1">解题</el-tag>
            <span class="badge text-bg-secondary count">{{ labelList[0].javascript }}</span>
          </li>
        </ul>
      </el-card>
      
      
      <div class="alert alert-dark mt-3" role="alert" style="width: 900px;margin-left: 30px;"
           v-for="(blog, index) in blog_list"
           :key="index">
        <a @click="goto(blog.blogId)" target="_blank" style="cursor:pointer">
          <h5>{{ blog.title }}</h5>
          <div>
            👨‍💻 作者 {{ blog.username }}
            📅 {{ blog.releaseTime }}
            📂 {{ blog.label }}
          </div>
        </a>
      </div>
      
      <!--分页-->
      <div class="block" v-show="isPagination" style="margin-left: 300px;">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pagination.page"
          :page-sizes="[6, 8, 10, 15]"
          :page-size="pagination.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="pageTotal">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import {getAllBlog, GetBlogByLabel, getBlogLabelCount} from '@/api/blog/blogApi'

export default {
  name: "UserArticle",
  data() {
    return {
      // 模拟数据 => 实际上接收到数据直接覆盖假的数据
      blog_list: [
        {
          "blogId": 1,
          "username": "TianTianAdmin",
          "title": "json-server比mook还简单的数据模拟神器(一分钟上手)",
          "label": "java",
          "releaseTime": "2023-03-26 10:23:29",
        },
        {
          "blogId": 2,
          "username": "TianTianAdmin",
          "title": "vue项目页面跳转的常用方法",
          "label": "vue",
          "releaseTime": "2023-03-28 16:43:07",
        },
        {
          "blogId": 3,
          "username": "TianTianAdmin",
          "title": "二分查找基本使用",
          "label": "算法",
          "releaseTime": "2023-03-28 16:50:08",
        },
        {
          "blogId": 4,
          "username": "TianTianAdmin",
          "title": "插值查找与斐波那契查找",
          "label": "算法",
        },
        {
          "blogId": 5,
          "username": "TianTianAdmin",
          "title": "分块查找",
          "label": "算法",
          "releaseTime": "2023-03-28 16:52:24",
        },
        {
          "blogId": 6,
          "username": "TianTianAdmin",
          "title": "哈希查找",
          "label": "算法",
          "releaseTime": "2023-03-28 16:54:25",
        }
      ],
      labelList: [
        /*        {
                java: 6,
                algorithm: 4,
                essay: 0,
                vue: 1,
                css: 0,
                javascript: 0,
                python: 0
              }*/
      ],
      isPagination: true,
      pageTotal: 0, //总记录数
      pagination: {//分页相关模型数据
        page: 1, //当前页码
        pageSize: 6, // 每页要展示多少条数据 默认为6条
      },
    }
  },
  created() {
    this.getAll()
    this.getLabelCount()
  },
  methods: {
    handleCurrentChange(val) {
      this.pagination.page = val
      this.getAll()
    },
    
    handleSizeChange(val) {
      this.pagination.pageSize = val
      this.getAll()
    },
    goto(blogId) {
      let routeData = this.$router.resolve({
        path: "/showcase-the-article",
        query: {
          blogId
        }
      });
      window.open(routeData.href, '_blank');
    },
    
    getAll() {
      this.isPagination = true
      getAllBlog(this.pagination).then(res => {
        this.blog_list = res.data.data
        this.pageTotal = parseInt(res.data.message)
      })
    },
    getLabelCount() {
      getBlogLabelCount().then((res) => {
        this.labelList = res.data.data
      })
    },
    
    GetBlogByLabel(label) {
      this.isPagination = false
      GetBlogByLabel(label).then((res) => {
        this.blog_list = res.data.data
      })
    }
    
  }
}
</script>

<style scoped>
a {
  text-decoration: none;
  color: black;
}

.container-people {
  color: #969696;
  padding: 10px;
  width: 1250px;
  border-radius: 20px;
}

li {
  cursor: pointer;
  margin-bottom: 20px;
  border-radius: 15px;
  list-style: none;
}

li:hover {
  background-color: rgb(241, 242, 243);
}

.count {
  float: right;
  margin-top: 6px;
}

.center {
  margin-left: 330px;
}
</style>