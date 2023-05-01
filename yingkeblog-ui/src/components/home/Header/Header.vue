<template>
  <div style="height: 6rem;">
    <header class="p-3 mb-3 border-bottom">
      <div class="container">
        <div class="d-flex">
          <a href="#/main" class="d-flex mb-2 mb-lg-0 text-dark text-decoration-none">
            <img src="./images/开发.png" alt="1" style="width: 45px;height: 45px; margin-right: 10px">
          </a>
          <a href="#" class="me-5 animate__animated animate__bounce text-black fs-5 pt-1">应科博客</a>
          <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
            
            <li>
              <router-link
                to="/main" style="color: black;text-decoration: none" active-class="active">首页
              </router-link>
            </li>
            
            <li>
              <router-link
                to="/study-list" style="color: black;text-decoration: none" active-class="active">学习
              </router-link>
            </li>
            

            <li>
              <router-link
                to="/blog" class="a_" active-class="active">博客
              </router-link>
            </li>
            
            
            <li>
              <router-link
                to="/my-video" class="a_" active-class="active">娱乐
              </router-link>
            </li>

          </ul>
          
          <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" role="search">
            
            <input type="search"
                   class="form-control"
                   placeholder="Search..."
                   v-show="$route.path !== '/blog/user-article' && $route.path !== '/search-result'"
                   aria-label="Search"
                   v-model="ssInfo"
                   @keydown.enter="search">
            
            <el-col :span="24" v-show="$route.path === '/blog/user-article' || $route.path === '/search-result'">
              <el-autocomplete
                class="inline-input mx-4"
                v-model="ssInfo"
                prefix-icon="el-icon-search"
                :fetch-suggestions="querySearch"
                placeholder="请输入文章标题"
                :trigger-on-focus="false"
                @select="handleSelect"
                @keydown.enter.native="searchBlog"
              ></el-autocomplete>
            </el-col>
          </form>
          <ul>
            <li><a href="#" class="nav-link mt-2 link-dark" style="margin-left: 20px;margin-right: 18px">
              <router-link
                to="/data-center" style="color: black;text-decoration: none" active-class="active">数据可视化中心
              </router-link>
            </a></li>
          </ul>
          
          <div class="dropdown text-end">
            <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" data-bs-toggle="dropdown"
               aria-expanded="false">
              <!--用户头像展示区域(从vuex中取出) src = userInfo.headUrl -->
              <!--如果headurl为空值 => 默认展示图片 -->
              <img :src="headurl || defaultUrl" alt="mdo"
                   width="32" height="32" class="rounded-circle mt-2">
            </a>
            
            <ul class="dropdown-menu text-small">
              <router-link to="/user-space" style="color: black;text-decoration: none">
                <li><a class="dropdown-item" href="#">
                  个人中心
                  <span class="iconfont icon-geren"></span></a>
                </li>
              </router-link>
              
              <li>
                <hr class="dropdown-divider">
              </li>
              
              <li><a class="dropdown-item" href="#" @click="logout">退出 <span class="iconfont icon-tuichu"></span></a>
              </li>
            </ul>
          </div>
        
        
        </div>
      </div>
    </header>
  </div>
</template>

<script>
import 'animate.css'
import './bundle'
import './icon/iconfont.css'
import {mapState} from "vuex";
import {loginOut} from '@/api/user/user'
import axios from "axios";

export default {
  name: "my-header",
  components: {},
  data() {
    return {
      ssInfo: '',
      headurl: '',
      defaultUrl: require('@/assets/img/defaultUserUrl.png')
    }
  },
  created() {
    let userInfo = JSON.parse(localStorage.getItem("userInfo"));
    this.headurl = userInfo.headurl
  },
  methods: {
    logout() {
      loginOut().then(res => {
        if (res.data.code === 200) {
          this.$notify({
            title: '退出成功',
            message: '请重新登录',
            type: 'success'
          });
        }
        // 跳转到登录页面
        this.$router.replace('/login')
        // 讲localStorage中的东西都删除
        localStorage.removeItem("userInfo")
        localStorage.removeItem("TTtoken")
      })
    },
    
    
    search() {
      window.open(`https://www.baidu.com/s?ie=UTF-8&wd=${this.ssInfo}`)
      this.ssInfo = ''
    },
    
    querySearch(queryString, cb) {
      let restaurants = this.restaurants;
      let results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    createFilter(queryString) {
      return (restaurant) => {
        return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
      };
    },
    loadAll() {
      return [
        {"value": "json-server比mook还简单的数据模拟神器(一分钟上手)", "blogId": "1"},
        {"value": "vue项目页面跳转的常用方法", "blogId": "2"},
        {"value": "二分查找基本使用", "blogId": "3"},
        {"value": "插值查找与斐波那契查找", "blogId": "4"},
        {"value": "分块查找", "blogId": "5"},
        {"value": "哈希查找", "blogId": "6"},
      ];
    },
    handleSelect(item) {
      console.log(item.blogId);
      this.$router.replace('/search-result')
    },
    
    searchBlog() {
      this.$router.replace({
        path: "/search-result",
        query: {
          keyWord: this.ssInfo
        }
      });
      
      this.ssInfo = ''
    }
  },
  computed: {},
  mounted() {
    this.restaurants = this.loadAll();
  }
}
</script>

<style scoped>

.active {
  padding-bottom: 3px;
  border-bottom: 2px solid #3b7bff;
}

.nav li {
  margin-top: 8px;
  padding-right: 18px;
}

header {
  box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.08);
}

.a_ {
  color: black;
  text-decoration: none
}

.nav li::after {
  content: '';
  transform-origin: right;
  transform: scaleX(0);
  transition: transform .5s;
}

.nav li:hover::after {
  transform-origin: left;
  transform: scaleX(1);
}


a {
  text-decoration: none;
}

ul {
  margin: 0;
  padding: 0
}

li {
  margin: 0;
  padding: 0;
  list-style: none;
}
</style>