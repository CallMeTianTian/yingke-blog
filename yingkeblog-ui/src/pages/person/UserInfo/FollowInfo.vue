<template>
  <div class="container">
    
    <img :src="headurl" style="cursor:pointer;margin-top: 5px;margin-left: 25px" width="140" height="140"
         class="img-thumbnail"
         alt="MyFollowImg">
    
    <div style="position: absolute;left: 160px;margin-top: 10px;">
      <el-button v-show="isFollow" plain icon="el-icon-success" :size="'small'" type="primary"
                 @click="myFollow">已关注
      </el-button>
      
      <el-button v-show="!isFollow" plain icon="el-icon-circle-plus" :size="'small'" type="primary"
                 @click="myFollow">关注
      </el-button>
    </div>
    
    <el-descriptions class="margin-top"
                     style="float: right;"
                     :title="userinfo.username + '的用户信息'"
                     :column="2"
                     :contentStyle="CS" :label-style="LS"
                     border>
      
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-user"></i>
          用户名
        </template>
        {{ userinfo.username }}
      </el-descriptions-item>
      
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-mobile-phone"></i>
          邮箱号
        </template>
        {{ email }}
      </el-descriptions-item>
      
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-help"></i>
          身份
        </template>
        {{ userinfo.identity }}
      </el-descriptions-item>
      
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-sunrise-1"></i>
          生日
        </template>
        <div class="block">
          <span class="demonstration"></span>
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
               class="bi bi-calendar-check-fill mb-1" viewBox="0 0 16 16">
            <path
              d="M4 .5a.5.5 0 0 0-1 0V1H2a2 2 0 0 0-2 2v1h16V3a2 2 0 0 0-2-2h-1V.5a.5.5 0 0 0-1 0V1H4V.5zM16 14V5H0v9a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2zm-5.146-5.146-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 0 1 .708-.708L7.5 10.793l2.646-2.647a.5.5 0 0 1 .708.708z"/>
          </svg>
          &nbsp;{{ userinfo.birthday }}
        </div>
      </el-descriptions-item>
      
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-tickets"></i>
          擅长的语言
        </template>
        <div>
          <el-tag size="small" v-for="(item,index) in userinfo.label" :key="index">{{ item }}</el-tag>
        </div>
      </el-descriptions-item>
      
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-office-building"></i>
          公司/学校
        </template>
        {{ userinfo.schoolOrCorporation }}
      </el-descriptions-item>
    
    
    </el-descriptions>
    
    
    <div class="alert alert-success" style="margin-top: 70px;" role="alert">
      <h4 class="alert-heading">个人描述</h4>
      <hr>
      <p class="mb-0" v-html="userinfo.description" v-model="userinfo.description"></p>
    </div>
    
    <!--个人文章和学习情况-->
    <div class="alert alert-primary mt-3" role="alert">
      <h4 class="alert-heading">热门文章</h4>
      <hr>
      <!--遍历popularArticles 依次取得blogId uid title watch star collection-->
      <div class="alert alert-light" role="alert" style="zoom: 88%">
        <el-empty description="暂时莫得" v-if="popularArticles.length === 0"></el-empty>
        <ul>
          <li v-for="popular in popularArticles" :key="popular.blogId" @click="goto(popular.blogId)"
              style="cursor:pointer">
            <a href="#" class="alert-link">{{ popular.title }}</a>
            <span style="float: right">
              📅{{ popular.releaseTime }}&nbsp;&nbsp;
              👁️‍🗨️️‍{{ popular.watch }}&nbsp;&nbsp;&nbsp;&nbsp;
              👍{{ popular.star }}&nbsp;&nbsp;&nbsp;
              💎{{ popular.collection }}
            </span>
          </li>
        </ul>
      
      </div>
    </div>
    
    <div class="alert alert-dark" role="alert">
      <h4 class="alert-heading">学习情况</h4>
      <hr>
      <table class="table table-striped table-hover">
        <thead>
        <tr>
          <th scope="col" width="50"></th>
          <th scope="col" width="250">课程名称</th>
          <th scope="col" width="160">学习进度</th>
          <th scope="col" width="250">最近一次学习的时间</th>
          <th scope="col" width="60">查看</th>
        </tr>
        </thead>
        
        <tbody>
        
        <tr v-for="(cp,index) in classAndProgressList">
          <th scope="row">{{ index + 1 }}</th>
          <td>{{ cp.className }}</td>
          <td>{{ cp.progress }}</td>
          <td>{{ cp.modifyTime }}</td>
          <td><a :href="cp.url" target="_blank">GO</a></td>
        </tr>
        </tbody>
      </table>
      <div class="alert alert-light" v-show="classAndProgressList.length === 0">
        <el-empty description="暂时莫得"></el-empty>
      </div>
    </div>
  
  </div>
</template>

<script>
import axios from "axios";
import {getBaseUserInfoByUid, getUserByUid} from '@/api/user/user'
import {follow, getFollowInfoByUid, isFollow} from '@/api/user/follow'

export default {
  name: "SpaceInfo",
  data() {
    return {
      uid: '',
      email: '',
      headurl: '',
      userinfo: {},
      popularArticles: [],
      classAndProgressList: [],
      dialogFormVisible: false,
      formLabelWidth: '100px',
      isFollow: false,
      inputVisible: false,
      inputValue: '',
      
      CS: {
        'min-width': '450px',   //最小宽度
        'word-break': 'break-all'  //过长时自动换行
      },
      LS: {
        'font-weight': '600',
        'height': '40px',
        'min-width': '110px',
        'word-break': 'keep-all'
      },
    }
  },
  created() {
    let uid = this.$route.query.uid
    console.log(uid)
    this.uid = uid
    // 获取基础用户信息
    this.getUserInfo()
    
    // 根据用户id来(从store中) =>  获取 标题 观看人数 点赞人数 时间
    axios.get(`http://localhost/blog/by-user/${this.uid}`).then((res) => {
      this.popularArticles = res.data.data
    }, (error) => {
      console.log(error.message)
    })
  
    isFollow(uid).then((res2) => {
      if (res2.data.data) {
        this.isFollow = true
      }
    })
    
    // 根据用户id来获取课程的信息
    getFollowInfoByUid(uid).then(res => {
      this.classAndProgressList = res.data.data
    }, err => {
      console.log(err.message)
    })
    
    getUserByUid(uid).then((res) => {
      this.email = res.data.data.email
      this.headurl = res.data.data.headurl
    })
  },
  
  methods: {
    
    getUserInfo() {
      // 获取基础用户信息
      getBaseUserInfoByUid(this.uid).then((res) => {
        this.userinfo = res.data.data
      })
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
    myFollow() {
      follow(this.uid, !this.isFollow).then((res) => {
        if (res.data.code) {
          this.isFollow = !this.isFollow
        }
      })
    },
  },
  
}

</script>

<style lang="css">

::-webkit-scrollbar {
  width: 6px;
}

/*定义滚动条轨道 内阴影+圆角*/
::-webkit-scrollbar-track {
  border-radius: 10px;
  background-color: rgba(0, 0, 0, 0.1);
}

/*定义滑块 内阴影+圆角*/
::-webkit-scrollbar-thumb {
  border-radius: 10px;
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, .3);
  background-color: rgba(0, 0, 0, 0.1);
}

ul {
  padding: 0;
  margin: 0;
}

li {
  margin: 0;
  padding: 0;
  list-style: none;
}

.el-tag + .el-tag {
  margin-left: 10px;
}

.button-new-tag {
  margin-left: 10px;
  height: 32px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
}

.input-new-tag {
  width: 90px;
  margin-left: 10px;
  vertical-align: bottom;
}
</style>

