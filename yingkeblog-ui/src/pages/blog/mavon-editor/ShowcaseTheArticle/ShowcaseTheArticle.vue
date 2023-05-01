<template>
  <div>
    <div class="alert alert-success mb-5" role="alert">
      <h3 class="alert-heading">{{ blogData.title }}</h3>
      作者: <a @click="goto(blogData.uid)" style="cursor:pointer;" class="alert-link">{{ blogData.username }}</a>
      <el-button v-show="isFollow" plain icon="el-icon-success" style="padding: 10px;margin-left: 15px" size="mini"
                 @click="myFollow">已关注
      </el-button>
      <el-button v-show="!isFollow" plain icon="el-icon-circle-plus" style="padding: 10px;margin-left: 15px" size="mini"
                 @click="myFollow">关注
      </el-button>
      <hr>
      <p class="mb-0">
        
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-clock mb-1"
             viewBox="0 0 16 16">
          <path d="M8 3.5a.5.5 0 0 0-1 0V9a.5.5 0 0 0 .252.434l3.5 2a.5.5 0 0 0 .496-.868L8 8.71V3.5z"/>
          <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm7-8A7 7 0 1 1 1 8a7 7 0 0 1 14 0z"/>
        </svg>
        <!--2023-03-28 11:45:00-->
        {{ blogData.releaseTime }}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        
        <!--label图标-->
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-card-list mb-1"
             viewBox="0 0 16 16">
          <path
            d="M14.5 3a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h13zm-13-1A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-13z"/>
          <path
            d="M5 8a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7A.5.5 0 0 1 5 8zm0-2.5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5zm0 5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5zm-1-5a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0zM4 8a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0zm0 2.5a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0z"/>
        </svg>
        {{ blogData.label }}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-eye mb-1"
             viewBox="0 0 16 16">
          <path
            d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z"/>
          <path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z"/>
        </svg>
        {{ blogData.watch }}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        
        <br>
      <div style="cursor:pointer;display: inline" @click="isStar = !isStar;star()" class="mt-2">
        <button v-if="!isStar" class="base-button">
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
               class="bi bi-star mb-1"
               viewBox="0 0 16 16">
            <path
              d="M2.866 14.85c-.078.444.36.791.746.593l4.39-2.256 4.389 2.256c.386.198.824-.149.746-.592l-.83-4.73 3.522-3.356c.33-.314.16-.888-.282-.95l-4.898-.696L8.465.792a.513.513 0 0 0-.927 0L5.354 5.12l-4.898.696c-.441.062-.612.636-.283.95l3.523 3.356-.83 4.73zm4.905-2.767-3.686 1.894.694-3.957a.565.565 0 0 0-.163-.505L1.71 6.745l4.052-.576a.525.525 0 0 0 .393-.288L8 2.223l1.847 3.658a.525.525 0 0 0 .393.288l4.052.575-2.906 2.77a.565.565 0 0 0-.163.506l.694 3.957-3.686-1.894a.503.503 0 0 0-.461 0z"/>
          </svg>
          {{ blogData.star }}
        </button>
        
        <button v-if="isStar" class="base-button">
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
               class="bi bi-star-fill mb-1" viewBox="0 0 16 16">
            <path
              d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"/>
          </svg>
          {{ blogData.star }}
        </button>
      </div>
      
      <!--评论图标-->
      <div @click="drawer = true;" style="float: left;cursor:pointer;">
        <button class="base-button">
          
          <svg v-if="!drawer" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
               class="bi bi-chat-dots mb-1" viewBox="0 0 16 16">
            <path
              d="M5 8a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
            <path
              d="m2.165 15.803.02-.004c1.83-.363 2.948-.842 3.468-1.105A9.06 9.06 0 0 0 8 15c4.418 0 8-3.134 8-7s-3.582-7-8-7-8 3.134-8 7c0 1.76.743 3.37 1.97 4.6a10.437 10.437 0 0 1-.524 2.318l-.003.011a10.722 10.722 0 0 1-.244.637c-.079.186.074.394.273.362a21.673 21.673 0 0 0 .693-.125zm.8-3.108a1 1 0 0 0-.287-.801C1.618 10.83 1 9.468 1 8c0-3.192 3.004-6 7-6s7 2.808 7 6c0 3.193-3.004 6-7 6a8.06 8.06 0 0 1-2.088-.272 1 1 0 0 0-.711.074c-.387.196-1.24.57-2.634.893a10.97 10.97 0 0 0 .398-2z"/>
          </svg>
          
          <svg v-if="drawer" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
               class="bi bi-chat-dots-fill mb-1" viewBox="0 0 16 16">
            <path
              d="M16 8c0 3.866-3.582 7-8 7a9.06 9.06 0 0 1-2.347-.306c-.584.296-1.925.864-4.181 1.234-.2.032-.352-.176-.273-.362.354-.836.674-1.95.77-2.966C.744 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7zM5 8a1 1 0 1 0-2 0 1 1 0 0 0 2 0zm4 0a1 1 0 1 0-2 0 1 1 0 0 0 2 0zm3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
          </svg>
          {{ UserComments.length }}
        </button>
      
      </div>
      <!--收藏图标-->
      <button class="base-button" @click="isCollect = !isCollect;addCollect()">
        <svg v-if="!isCollect" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
             class="bi bi-bookmark-star mb-1" viewBox="0 0 16 16">
          <path
            d="M7.84 4.1a.178.178 0 0 1 .32 0l.634 1.285a.178.178 0 0 0 .134.098l1.42.206c.145.021.204.2.098.303L9.42 6.993a.178.178 0 0 0-.051.158l.242 1.414a.178.178 0 0 1-.258.187l-1.27-.668a.178.178 0 0 0-.165 0l-1.27.668a.178.178 0 0 1-.257-.187l.242-1.414a.178.178 0 0 0-.05-.158l-1.03-1.001a.178.178 0 0 1 .098-.303l1.42-.206a.178.178 0 0 0 .134-.098L7.84 4.1z"/>
          <path
            d="M2 2a2 2 0 0 1 2-2h8a2 2 0 0 1 2 2v13.5a.5.5 0 0 1-.777.416L8 13.101l-5.223 2.815A.5.5 0 0 1 2 15.5V2zm2-1a1 1 0 0 0-1 1v12.566l4.723-2.482a.5.5 0 0 1 .554 0L13 14.566V2a1 1 0 0 0-1-1H4z"/>
        </svg>
        
        <svg v-if="isCollect" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
             class="bi bi-bookmark-star-fill mb-1" viewBox="0 0 16 16">
          <path fill-rule="evenodd"
                d="M2 15.5V2a2 2 0 0 1 2-2h8a2 2 0 0 1 2 2v13.5a.5.5 0 0 1-.74.439L8 13.069l-5.26 2.87A.5.5 0 0 1 2 15.5zM8.16 4.1a.178.178 0 0 0-.32 0l-.634 1.285a.178.178 0 0 1-.134.098l-1.42.206a.178.178 0 0 0-.098.303L6.58 6.993c.042.041.061.1.051.158L6.39 8.565a.178.178 0 0 0 .258.187l1.27-.668a.178.178 0 0 1 .165 0l1.27.668a.178.178 0 0 0 .257-.187L9.368 7.15a.178.178 0 0 1 .05-.158l1.028-1.001a.178.178 0 0 0-.098-.303l-1.42-.206a.178.178 0 0 1-.134-.098L8.16 4.1z"/>
        </svg>
        {{ blogData.collection }}
      </button>
      
      <!--  分享的图标-->
      <button class="base-button" @click="isShare = !isShare;copy()">
        <svg v-if="!isShare" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
             class="bi bi-share mb-1"
             viewBox="0 0 16 16">
          <path
            d="M13.5 1a1.5 1.5 0 1 0 0 3 1.5 1.5 0 0 0 0-3zM11 2.5a2.5 2.5 0 1 1 .603 1.628l-6.718 3.12a2.499 2.499 0 0 1 0 1.504l6.718 3.12a2.5 2.5 0 1 1-.488.876l-6.718-3.12a2.5 2.5 0 1 1 0-3.256l6.718-3.12A2.5 2.5 0 0 1 11 2.5zm-8.5 4a1.5 1.5 0 1 0 0 3 1.5 1.5 0 0 0 0-3zm11 5.5a1.5 1.5 0 1 0 0 3 1.5 1.5 0 0 0 0-3z"/>
        </svg>
        
        <svg v-if="isShare" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
             class="bi bi-share-fill mb-1" viewBox="0 0 16 16">
          <path
            d="M11 2.5a2.5 2.5 0 1 1 .603 1.628l-6.718 3.12a2.499 2.499 0 0 1 0 1.504l6.718 3.12a2.5 2.5 0 1 1-.488.876l-6.718-3.12a2.5 2.5 0 1 1 0-3.256l6.718-3.12A2.5 2.5 0 0 1 11 2.5z"/>
        </svg>
        分享
      </button>
    
    </div>
    <div class="ueditor">
      <mavon-editor
        class="markdown-body"
        v-highlight ref="md"
        codeStyle="github"
        :ishljs="true"
        v-html="codeDataHtml">
      </mavon-editor>
    </div>
    
    <!--用户评论的功能-->
    <el-drawer
      :modal="false"
      size="40%"
      :before-close="handleClose"
      :wrapperClosable="false"
      :title="commentCount"
      :visible.sync="drawer"
      :direction="direction">
      <hr>
      <div style="margin-top: 10px;">
        
        <img width="40" height="40"
             style="float: left;margin-left: 10px;margin-top: 5px;"
             src="https://my-web-oss.oss-cn-beijing.aliyuncs.com/daf57086-8471-4867-b5b0-bfb9698eba58.png"
             class="rounded" alt="loginUserImg">
        
        <el-input
          type="textarea"
          style="float: left;width: 480px;margin-left: 10px;"
          :autosize="{ minRows: 4, maxRows: 10}"
          placeholder="欢迎您高质量的评论"
          maxlength="1000"
          show-word-limit
          v-model="content">
        </el-input>
      </div>
      <div style="clear: both"></div>
      
      <el-button style="margin-left: 480px;margin-top: 10px;" size="mini" @click="submit">评论</el-button>
      
      <!--评论者的信息: 用户头像地址 用户名 用户评论的时间 用户评论的内容 https://my-web-oss.oss-cn-beijing.aliyuncs.com/10-23041G64Q2.jpg -->
      <div v-for="UserComment in UserComments" :key="UserComment.id" class="alert alert-secondary"
           style="margin-right: 10px;margin-left: 10px;margin-top: 10px;padding: 8px"
           role="alert">
        <div>
          <!--用户头像-->
          <img width="35" height="35"
               style="float: left;cursor: pointer"
               @click="gotoUserInfo(UserComment.uid)"
               :src="UserComment.headurl"
               class="rounded" alt="loginUserImg">
          &nbsp;&nbsp;&nbsp; {{ UserComment.username }}
          <!--评论的日期-->
          <h6 style="float: right;">{{ String(timestampToTime(UserComment.releaseTime)) }}</h6>
        </div>
        <div style="clear: both"></div>
        <hr>
        <!--评论的内容-->
        <p>{{ UserComment.content }}</p>
      </div>
      
    </el-drawer>
  
  </div>
</template>

<script>

import {marked} from "marked";
import 'mavon-editor/dist/markdown/github-markdown.min.css'
import {getBlogByBlogId, isDidStar, likeBlogByBlogId} from '@/api/blog/blogApi'
import {isFollow, follow} from '@/api/user/follow'
import {addComment, getCommentsByBlogId, isComment} from "@/api/blog/comment";
import {timestampToTime} from "@/utils/DateFormatting";
import {addCollect} from "@/api/blog/collect";


export default {
  name: "ShowcaseTheArticle",
  data() {
    return {
      blogId: '',
      content: '', // 当前登录用户写的评论
      UserComments: [], // 存放用户的评论
      codeDataHtml: '',
      codeDataMarkDown: '',
      isFollow: false,
      blogData: {},
      drawer: false, // 是否展开评论
      
      direction: 'rtl',
      isStar: false,
      isCollect: false,
      isShare: false,
      
    }
  },
  computed: {
    commentCount() {
      return '评论  ' + this.UserComments.length
    },
    shareContent() {
      return '我分享了一篇文章 "' + this.blogData.title + '" ' + location.href + ' 快来看'
    }
  },
  created() {
    // 从路径中获取的博客的blogId再发送请求给后端 后端根据blogId来查询文章
    this.blogId = this.$route.query.blogId
    getBlogByBlogId(this.blogId).then((res) => {
      this.blogData = res.data.data
      
      // 若没有查询到数据则跳转到404页面
      if (res.data.data === null) {
        this.$router.replace('/error_404')
      }
      // 查询当前用户是否已经关注过作者了并修改isFollow的值
      isFollow(this.blogData.uid).then((res2) => {
        if (res2.data.data) {
          this.isFollow = true
        }
      })
      this.codeDataMarkDown = res.data.data.context
      this.markdownChangeHtml()
    })
    
    // 查询当前用户是否已经点过赞了
    isDidStar(this.blogId).then((res) => {
      this.isStar = res.data.code === 200;
    })
    
    isComment(this.blogId).then((res) => {
      this.isCollect = res.data.data
    })
    
    // 获取评论信息
    this.getComments()
  },
  
  methods: {
    timestampToTime(timestamp) {
      return timestampToTime(timestamp)
    },
    
    
    handleClose() {
      this.drawer = false
      console.log("评论关闭了")
    },
    // 将markdown转化为html展示给用户
    markdownChangeHtml() {
      this.codeDataHtml = marked(this.codeDataMarkDown)
    },
    
    star() {
      likeBlogByBlogId(this.$route.query.blogId).then(res => {
        if (res.data.code === 200) {
          console.log("点赞成功");
          if (this.isStar) {
            this.blogData.star++;
          } else {
            this.blogData.star--;
          }
        } else {
          this.$message.error("点赞失败")
        }
      })
      
    },
    
    myFollow() {
      follow(this.blogData.uid, !this.isFollow).then((res) => {
        if (res.data.code) {
          this.isFollow = !this.isFollow
        }
      })
    },
    
    goto(uid) {
      console.log(uid)
      let routeData = this.$router.resolve({
        path: "/user",
        query: {
          uid
        }
      });
      window.open(routeData.href, '_blank');
    },
    
    addCollect() {
      addCollect(this.blogId, this.isCollect).then((res) => {
        if (res.data.code === 200) {
          if (this.isCollect) {
            this.blogData.collection++;
          } else {
            this.blogData.collection--;
          }
        } else {
          this.$message.error("收藏失败")
        }
      })
    },
    
    // 获取评论信息
    getComments() {
      getCommentsByBlogId(this.blogId).then((res) => {
        this.UserComments = res.data.data
      })
    },
    
    // 提交用户评论
    submit() {
      // 判断内容是否为空
      if (this.content === '') {
        this.$message({
          message: '警告',
          type: '评论信息不能为空!'
        });
      }
      let content = this.content
      let blogId = this.blogId
      
      let formData = {
        content,
        blogId
      }
      addComment(formData).then((res) => {
        if (res.data.code === 200) {
          this.$message({
            message: '评论成功',
            type: 'success'
          });
          setTimeout(() => {
            // 重新获取评论的信息
            this.getComments();
          }, 300)
          // 清空评论
          this.content = ''
          
        } else {
          this.$message({
            message: '评论失败',
            type: 'error'
          });
        }
      })
    },
    gotoUserInfo(uid) {
      console.log(uid)
      let routeData = this.$router.resolve({
        path: "/user",
        query: {
          uid
        }
      });
      window.open(routeData.href, '_blank');
    },
    copy() {
      //创建input标签
      let input = document.createElement('input')
      //将input的值设置为需要复制的内容
      input.value = this.shareContent
      //添加input标签
      document.body.appendChild(input)
      //选中input标签
      input.select()
      //执行复制
      document.execCommand('copy')
      //成功提示信息
      this.$notify({
        title: '复制成功',
        message: '赶快分享给你的好友吧!',
        type: 'success',
      });
      //移除input标签
      document.body.removeChild(input)
    },
  },
}
</script>

<style scoped>

.bg-light {
  padding: 20px;
  border-radius: 10px;
}

.base-button {
  padding: 0 15px;
  margin-left: 10px;
  margin-top: 10px;
  background-color: #c6ecda;
  border-radius: 15px;
  margin-right: 10px;
  border: 1px solid slategray;
}

.base-button:hover {
  background-color: #a7deac;
}

</style>